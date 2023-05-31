package ru.job4j.jdbc.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            String[] arrayOfStrings = rd.lines().toArray(String[] :: new);
            for (String arrayOfString : arrayOfStrings) {
                if (arrayOfString.equals(";")) {
                    throw new IllegalArgumentException("Not enough elements in the array");
                }
                String[] stringArray = arrayOfString.split(";");
                if (stringArray.length < 2 || stringArray[0].equals("") || stringArray[1].equals("")) {
                    throw new IllegalArgumentException("Not enough elements in the array");
                }
                User user = new User(stringArray[0], stringArray[1]);
                users.add(user);
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("hibernate.connection.driver_class"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("hibernate.connection.url"),
                cfg.getProperty("hibernate.connection.username"),
                cfg.getProperty("hibernate.connection.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "C:\\Users\\Ivan_Kozhevnikov\\IdeaProjects\\job4j_design\\src\\main\\resources\\dump.txt");
        db.save(db.load());
    }
}