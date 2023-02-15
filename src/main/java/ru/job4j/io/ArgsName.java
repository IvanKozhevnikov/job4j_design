package ru.job4j.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String el : args) {
            verification(el);
            el = debug(el);
            List<String> record;
            record = List.of(el.split("=", 2));
            values.put(record.get(0), record.get(1));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void verification(String string) {
        if (!string.startsWith("-") || !string.contains("=") || !string.matches("-\\S{1,}=\\S{1,}")) {
            throw new IllegalArgumentException();
        }
    }

    private String debug(String string) {
        string = string.substring(1);
        return string;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}