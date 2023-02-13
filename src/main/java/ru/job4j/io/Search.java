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
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validate(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("The launch requires to specify the first parameter and the second parameter");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("The launch requires to specify the second parameter");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("No such directory %s", file.getAbsoluteFile()));
        }
        Path start = Paths.get(args[0]);
        if (search(start, p -> p.toFile().getName().endsWith(args[1])).isEmpty()) {
            throw new IllegalArgumentException(String.format("There are no files with this extension %s", args[1]));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }
}