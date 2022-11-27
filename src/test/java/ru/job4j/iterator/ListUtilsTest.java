package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 7, 3, 4, 9, 11));
        Predicate<Integer> number = x -> x >= 4 && x <= 8;
        ListUtils.removeIf(input, number);
        assertThat(input).hasSize(5).containsSequence(2, 1, 3, 9, 11);
    }

    @Test
    public void whenAddReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(9, 2, 6, 5, 4, 3, 0));
        Predicate<Integer> number = x -> x % 2 != 0;
        ListUtils.replaceIf(input, number, 10);
        assertThat(input).hasSize(7).containsSequence(10, 2, 6, 10, 4, 10, 0);
    }

    @Test
    public void whenRemoveAllContain() {
        List<Integer> input = new ArrayList<>(Arrays.asList(9, 1, 7, 11, 4, 24, 0));
        List<Integer> remove = new ArrayList<>(Arrays.asList(1, 11, 24));
        ListUtils.removeAll(input, remove);
        assertThat(input).hasSize(4).containsSequence(9, 7, 4, 0);
    }
}