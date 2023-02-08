package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;


public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("C:/Users/Ivan_Kozhevnikov/IdeaProjects/job4j_design/data");
        validate(start);
        search(start, p -> p.toFile().getName().endsWith(".txt")).forEach(System.out :: println);
    }

    private static void validate(Path start) throws IOException {
        File file = new File("data");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Folder not exist: %s", file.getAbsoluteFile()));
        }
        if (search(start, p -> p.toFile().getName().endsWith(".txt")).isEmpty()) {
            throw new IllegalArgumentException("Not file .txt");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}