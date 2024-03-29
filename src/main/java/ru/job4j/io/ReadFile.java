package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("C:\\Users\\Ivan_Kozhevnikov\\IdeaProjects\\job4j_design\\src\\main\\resources\\input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}