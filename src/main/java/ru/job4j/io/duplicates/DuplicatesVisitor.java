package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> listOfFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        listOfFiles.putIfAbsent(fileProperty, new ArrayList<>());
        listOfFiles.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void printingDuplicateFiles() {
        for (List<Path> element : listOfFiles.values()) {
            if (element.size() > 1) {
                element.forEach(System.out::println);
            }
        }
    }
}