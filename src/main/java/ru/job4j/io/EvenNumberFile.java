
package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("C:\\Users\\Ivan_Kozhevnikov\\IdeaProjects\\job4j_design\\src\\main\\resource\\even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println("Число " + line + " " + (Integer.parseInt(line) % 2 == 0 ? "чётное" : "нечётное"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}