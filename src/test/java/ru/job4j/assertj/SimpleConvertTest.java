package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    String[] arrListSetMap = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    @Test
    void toArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five", "Six", "Seven");
        assertThat(array).hasSize(7)
                .contains("five")
                .contains("four", Index.atIndex(3))
                .doesNotContain("first", Index.atIndex(1))
                .isNotNull();
    }

    @Test
    void toList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> arrList = simpleConvert.toList(arrListSetMap);
        assertThat(arrList).hasSize(9)
                .contains("Three")
                .containsAnyOf("Three", "Four")
                .contains("Seven", Index.atIndex(6))
                .containsSequence("One", "Two", "Three", "Four", "Five")
                .doesNotContain("Hundred")
                .isNotNull();
    }

    @Test
    void toSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet(arrListSetMap);
        assertThat(set).hasSize(9)
                .containsExactlyInAnyOrder(
                        "One", "Eight", "Three", "Four", "Five", "Six", "Seven", "Two", "Nine")
                .contains("Six")
                .doesNotContain("Twelve")
                .isNotNull();

    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(arrListSetMap);
        assertThat(map).hasSize(9)
                .containsOnlyKeys(arrListSetMap)
                .doesNotContainKeys("Fifteen", "Hundred")
                .doesNotContainKey("Twelve")
                .containsKey("Two")
                .doesNotContainEntry("Twelve", 11)
                .containsEntry("Seven", 6)
                .containsOnlyKeys("One", "Eight", "Three", "Four", "Five", "Six", "Seven", "Two", "Nine")
                .containsValues(4, 5)
                .isNotNull();
    }
}