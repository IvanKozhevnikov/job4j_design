package ru.job4j.jdbc;

import junit.framework.Test;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.Config;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    private Connection connection;

    private Properties properties;
    private static Properties config = new Properties();

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public static void properties() throws IOException {

        InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("/app.properties");

        System.out.println(TableEditor.class.getClassLoader().getResourceAsStream("/app.properties"));
        try (in) {
            System.out.println(in);
            System.out.println(config.getProperty("url"));
            config.load(in);
        }
    }

    private static Connection getConnection() throws Exception {
        System.out.println(config.getProperty("username"));
        String url = config.getProperty("url");
        String login = config.getProperty("username");
        String password = config.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    private void initConnection() {
        connection = null;
    }
    // создает пустую таблицу без столбцов с указанным именем
    public void createTable(String tableName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String createTable = "CREATE TABLE IF NOT EXISTS " + tableName + "(%s, %s)";
                statement.execute(createTable);
            }
        }

    }
    // удаляет таблицу по указанному имени
    public void dropTable(String tableName) {
    }
    // добавляет столбец в таблицу
    public void addColumn(String tableName, String columnName, String type) {
    }
    // удаляет столбец из таблицы
    public void dropColumn(String tableName, String columnName) {
    }
    // переименовывает столбец
    public void renameColumn(String tableName, String columnName, String newColumnName) {
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


    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("/app.properties");
//        TableEditor.properties();
        System.out.println(in);
        Properties config = new Properties();
        String tableName = "demo_table";

                TableEditor tableEditor = new TableEditor(config);
                tableEditor.createTable(tableName);
                System.out.println(tableEditor.getTableScheme(tableName));

    }
}