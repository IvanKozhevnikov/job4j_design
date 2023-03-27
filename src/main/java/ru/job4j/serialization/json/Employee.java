package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {
    private final boolean license;
    private final int experienceAge;
    private final String specialization;
    private final PersonalData personalData;
    private final String[] skills;

    public Employee(boolean license, int experienceAge, String specialization, PersonalData personalData, String... skills) {
        this.license = license;
        this.experienceAge = experienceAge;
        this.specialization = specialization;
        this.personalData = personalData;
        this.skills = skills;
    }

    public boolean isLicense() {
        return license;
    }

    public int getExperienceAge() {
        return experienceAge;
    }

    public String getSpecialization() {
        return specialization;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public String[] getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "license=" + license
                + ", experienceAge=" + experienceAge
                + ", specialization='" + specialization + '\''
                + ", personalData=" + personalData
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }

    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonPersonalData = new JSONObject("{\"email\":\"email@mail.ru\", \"snils\":\"34465466\", \"inn\":\"345656\", \"rights\":\"34465466\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("Spring");
        list.add("SQL");
        list.add("JavaScript");
        list.add("Liquibase");
        JSONArray jsonSkills = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Employee employee = new Employee(true, 2, "programmer",
                new PersonalData("email@mail.ru", "34465466", "345656", "34465466"),
                "java", "Spring", "SQL", "JavaScript", "Liquibase");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("license", employee.isLicense());
        jsonObject.put("experienceAge", employee.getExperienceAge());
        jsonObject.put("specialization", employee.getSpecialization());
        jsonObject.put("personalData", jsonPersonalData);
        jsonObject.put("skills", jsonSkills);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(employee));
    }
}
