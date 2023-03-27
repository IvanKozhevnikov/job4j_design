package ru.job4j.serialization.json;

public class PersonalData {
    public final String email;
    public final String snils;
    public final String inn;
    public final String rights;

    public PersonalData(String email, String snils, String inn, String rights) {
        this.email = email;
        this.snils = snils;
        this.inn = inn;
        this.rights = rights;
    }

    public String getEmail() {
        return email;
    }

    public String getSnils() {
        return snils;
    }

    public String getInn() {
        return inn;
    }

    public String getRights() {
        return rights;
    }

    @Override
    public String toString() {
        return "PersonalData{"
                + "email='" + email + '\''
                + ", snils='" + snils + '\''
                + ", inn='" + inn + '\''
                + ", rights='" + rights + '\''
                + '}';
    }
}

