package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines()
                    .filter(e -> !e.startsWith("#") && !e.isEmpty())
                    .filter(this :: validate)
                    .map(e -> e.split("=", 2))
                    .forEach(e -> values.put(e[0], e[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String e) {
        if (!e.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this e: %s does not contain the \"=\" symbol", e));
        }
        if (e.contains(")")) {
            throw new IllegalArgumentException(
                    String.format("this e: %s contain the \")\" symbol", e));
        }
        if ((e.indexOf("=") == e.length() - 1) && e.startsWith("=")) {
            throw new IllegalArgumentException(
                    String.format("this e: %s does not contain the key and value", e));
        }
        if (e.indexOf("=") == e.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this e: %s does not contain the value", e));
        }
        if (e.startsWith("=")) {
            throw new IllegalArgumentException(
                    String.format("this e: %s does not contain the key", e));
        }
        return true;
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out :: add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));

    }
}