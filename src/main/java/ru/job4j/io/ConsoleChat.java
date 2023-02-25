package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private static List<String> logFile = new LinkedList<>();
    private static List<String> botResponsesFile = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message = null;
        readPhrases();
        while (!OUT.equals(message)) {
            message = scanner.nextLine();
            logFile.add(message);
            if (STOP.equals(message)) {
                String stop = "The bot is on pause";
                System.out.println(stop);
                logFile.add(stop);
                boolean answer = botStop();
                if (answer) {
                    String botOnline = "The bot is active again";
                    System.out.println(botOnline);
                    logFile.add(botOnline);
                }
                if (!answer) {
                    message = "закончить";
                    logFile.add(message);
                }
            }
            if (!STOP.equals(message) && !OUT.equals(message) && !CONTINUE.equals(message)) {
                String botSays = botResponsesFile.get(randomResponse(botResponsesFile));
                System.out.println(botSays);
                logFile.add(botSays);
            }
        }
        String out = "I`ll be back";
        System.out.println(out);
        logFile.add(out);
        saveLog(logFile);
        System.exit(0);
    }

    private void readPhrases() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(botResponsesFile :: add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean botStop() {
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        boolean mode = true;
        if (OUT.equals(msg)) {
            mode = false;
            msg = "продолжить";
        }
        logFile.add(msg);
        while (!CONTINUE.equals(msg)) {
            msg = scanner.nextLine();
            logFile.add(msg);
        }
        return mode;
    }

    private int randomResponse(List<String> botAnswersList) {
        Random random = new Random();
        return random.nextInt(botAnswersList.size());
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\Users\\Ivan_Kozhevnikov\\IdeaProjects\\job4j_design\\data\\log.txt",
                "C:\\Users\\Ivan_Kozhevnikov\\IdeaProjects\\job4j_design\\data\\bot.txt");
        cc.run();
    }
}