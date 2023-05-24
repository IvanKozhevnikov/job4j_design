package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;
    private static Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
                String createTable =  String.format(
                        "CREATE TABLE IF NOT EXISTS %s();",
                        tableName);
                execute(createTable);
    }

    public void dropTable(String tableName) throws Exception {
        String dropTable =  String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        execute(dropTable);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
            String addColumn =  String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            execute(addColumn);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String dropColumn =  String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        execute(dropColumn);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String renameColumn =  String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        execute(renameColumn);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
            try (var statement = connection.createStatement()) {
                var selection = statement.executeQuery(String.format(
                        "SELECT * FROM %s LIMIT 1", tableName
                ));
                var metaData = selection.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    buffer.add(String.format("%-15s|%-15s%n",
                            metaData.getColumnName(i), metaData.getColumnTypeName(i))
                    );
                }
            }
        return buffer.toString();
    }

    private void execute(String sql) throws Exception {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("test_table");
        System.out.println("1. Create table:\n" + tableEditor.getTableScheme("test_table"));
        tableEditor.addColumn("test_table", "age", "int");
        System.out.println("2. Add column:\n" + tableEditor.getTableScheme("test_table"));
        tableEditor.renameColumn("test_table", "age", "new_column");
        System.out.println("3. Rename column:\n" + tableEditor.getTableScheme("test_table"));
        tableEditor.dropColumn("test_table", "new_column");
        System.out.println("4. Drop column:\n" + tableEditor.getTableScheme("test_table"));
        tableEditor.dropTable("test_table");
        System.out.println("5. Drop table");
    }
}