package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Ivan";
        byte age = 5;
        long inn = 98723496;
        double weight = 90.5;
        int snils = 47685376;
        float income = (float) 300000000000000.0;
        short languages = 2;
        boolean married = true;
        LOG.debug("User info name : {}, age : {}, inn : {}, weight {}, snils {}, income {}, languages {}, married {}",
                name, age, inn, weight, snils, income, languages, married);
    }
}
