package ru.job4j.serialization.json;

import java.util.Arrays;

public class Employee {
    private final boolean license;
    private final int experienceAge;
    private final String specialization;
    private final PersonalData personalData;
    private final String[] skills;

    public Employee(boolean license, int experienceAge, String specialization, PersonalData personalData, String[] skills) {
        this.license = license;
        this.experienceAge = experienceAge;
        this.specialization = specialization;
        this.personalData = personalData;
        this.skills = skills;
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
}
