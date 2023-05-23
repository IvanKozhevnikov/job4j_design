package ru.job4j.jdbc;

import junit.framework.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class ResourceLoader {
    public static void main(String[] args) {

    URL resourceURL = Test.class.getResource("/app.properties");
    Properties appConfig = new Properties();
    try {
        appConfig.load(resourceURL.openStream());
        System.out.println(appConfig.getProperty("hibernate.connection.url"));
        System.out.println(appConfig.getProperty("version"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
