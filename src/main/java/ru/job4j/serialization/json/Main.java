package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final Employee employee = new Employee(true, 2, "programmer",
                new PersonalData("email&mail.ru", "387647683", "98769869", "0025400"), new String[] {"java", "Spring", "SQL", "JavaScript"});
        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        final String emploeeJson =
                "{"
                        + "\"license\":false,"
                        + "\"experienceAge\":5,"
                        + "\"specialization\":programmer,"
                        + "\"personalData\":"
                        + "{"
                        + "\"email\":email&mail.ru,"
                        + "\"snils\":34465466,"
                        + "\"inn\":345656,"
                        + "\"rights\":34465466"
                        + "},"
                        + "\"skills\":"
                        + "[\"java\",\"Spring\",\"SQL\",\"JavaScript\",\"Liquibase\"]"
                        + "}";
        final Employee emploeeMod = gson.fromJson(emploeeJson, Employee.class);
        System.out.println(gson.toJson(employee));
        System.out.println(emploeeMod);
    }
}