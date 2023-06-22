package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleSetSetTest {
    @Test
    void when4AddAndAddFirstThenFalse() {
        SimpleSetSet set = new SimpleSetSet();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("four");
        assertThat(set.add("first")).isFalse();
    }
}