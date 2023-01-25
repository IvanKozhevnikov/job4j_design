package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    private final List<String> values = new ArrayList<>();

    public void unavailable(String source, String target) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String e = in.readLine();
            int i = 0;
            while (e != null) {
                if (i == 0 && (e.contains("400") || e.contains("500"))) {
                    str.append(e.split(" ")[1]).append(";");
                    i++;
                }
                if (i == 1 && (e.contains("200") || e.contains("300"))) {
                    str.append(e.split(" ")[1]);
                    i = 0;
                    values.add(str.toString());
                    str.delete(0, str.length());
                }
                e = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(values, target);
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(out :: println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
