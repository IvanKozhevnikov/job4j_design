package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Ivan";
        byte age = 5;
        long inn = 98723496L;
        double weight = 90.5;
        int snils = 47685376;
        float income = 300000000000000.0F;
        short languages = 2;
        boolean married = true;
        char rights = 'B';
        LOG.debug("User info name : {}, age : {}, inn : {}, weight {}, snils {}, income {}, languages {}, married {}, rights {}",
                name, age, inn, weight, snils, income, languages, married, rights);
    }
}