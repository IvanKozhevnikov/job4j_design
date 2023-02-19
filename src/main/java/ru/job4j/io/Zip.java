package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zipOutputStream.write(out.readAllBytes());
                    zipOutputStream.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("you must specify the root folder, extension, and folder to host the file");
        }
        Path path = Paths.get(argsName.get("d"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("There is no such source %s", path.toFile()));
        }
        if (!argsName.get("o").contains(".")) {
            throw new IllegalArgumentException("The file name is not specified");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Extension not specified %s", argsName.get("e")));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get("d"));
        List<Path> source = Search.search(start, p -> !p.toFile().getName().endsWith(argsName.get("e")));
        File target = new File(argsName.get("o"));
        zip.packFiles(source, target);
    }
}