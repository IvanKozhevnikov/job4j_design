package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/appOne.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenWithPatternViolationKey() {
        String path = "./data/appTwo.properties";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=org.hibernate.dialect.PostgreSQLDialect does not contain the key");
    }

    @Test
    void whenWithPatternViolationValue() {
        String path = "./data/appThree.properties";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this e: hibernate.connection.url= does not contain the value");
    }

    @Test
    void whenWithPatternViolationKeyAndValue() {
        String path = "./data/appFore.properties";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this e: = does not contain the key and value");
    }

    @Test
    void whenWithPatternViolationBrace() {
        String path = "./data/appFive.properties";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this e: hibernate.connection.driver_class=)org.postgresql.Driver contain the \")\" symbol");
    }

    @Test
    void whenWithPatternViolationEqual() {
        String path = "./data/appSix.properties";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this e: hibernate.dialectorg.hibernate.dialect.PostgreSQLDialect does not contain the \"=\" symbol");
    }

    @Test
    void whenRecognizesTheValue() {
        String path = "./data/appSeven.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=");
    }

    @Test
    void whenRecognizesTheValueOne() {
        String path = "./data/appEight.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=1");
    }
}