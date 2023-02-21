package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        Pattern pattern1 = Pattern.compile("11");
        String text = "111111";
        Matcher matcher = pattern1.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());

            String str = "123+=-456:/789";
            String[] rsl = str.split("\\D+");
            System.out.println(Arrays.toString(rsl));
    }

        Pattern pattern3 = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text3 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher3 = pattern3.matcher(text3);
        while (matcher3.find()) {
            System.out.println("Найдено совпадение: " + matcher3.group());
    }
            Pattern pattern4 = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
            String text4 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
            Matcher matcher4 = pattern4.matcher(text4);
            while (matcher4.find()) {
                System.out.println("Найдено совпадение: " + matcher4.group());
            }
        Pattern pattern5 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text5 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher5 = pattern5.matcher(text5);
        while (matcher5.find()) {
            System.out.println("Найдено совпадение: " + matcher5.group());
        }
        }
    }
