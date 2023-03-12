package ru.job4j.io.csv;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static final String CONSOLE_PRINT = "stdout";

    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        validation(delimiter, path, out);

        try (Scanner scanner = new Scanner(new FileInputStream(Paths.get(path).toFile()), StandardCharsets.UTF_8)) {
            scanner.useDelimiter(";");
            List<String> printList = new ArrayList<>();
            List<Integer> indexColumnList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(delimiter);
                if (indexColumnList.isEmpty()) {
                    indexColumnList = searchColumnIndexes(line, filter.split(","));
                }
                String prepareString = prepareString(line, indexColumnList, delimiter);
                printList.add(prepareString);
            }
            printer(printList, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(String delimiter, String path, String out) {
        if (!";".equals(delimiter) && !",".equals(delimiter)) {
            throw new IllegalArgumentException("Wrong symbol for separation: " + delimiter);
        }

        if (!Paths.get(path).toFile().isFile() || !Paths.get(path).toFile().exists()) {
            throw new IllegalArgumentException("Invalid file path or the file does not exist");
        }
    }

    private static List<Integer> searchColumnIndexes(String[] splitLine, String[] columns) {
        List<Integer> index = new ArrayList<>();
        for (String column : columns) {
            int i = Arrays.asList(splitLine).indexOf(column);
            if (i != -1) {
                index.add(i);
            }
        }
        return index;
    }

    private static String prepareString(String[] line, List<Integer> indexColumnList, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indexColumnList.size(); i++) {
            int index = indexColumnList.get(i);
            if (i == indexColumnList.size() - 1) {
                stringBuilder.append(line[index]);
            } else {
                stringBuilder.append(line[index]).append(delimiter);
            }
        }
        return stringBuilder.toString();
        }

    private static void printer(List<String> printList, String out) {
        if (out.contains("stdout")) {
            printList.forEach(System.out::println);
        } else {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                printList.forEach(printWriter::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

