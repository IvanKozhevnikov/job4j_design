//package ru.job4j.set;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.Iterator;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class SimpleSetTest {
//
//    @Test
//    void whenAddNonNull() {
//        Set<Integer> set = new SimpleSet<>();
//        assertThat(set.add(1)).isTrue();
//        assertThat(set.contains(1)).isTrue();
//        assertThat(set.add(1)).isFalse();
//    }
//
//    @Test
//    void whenAddNull() {
//        Set<Integer> set = new SimpleSet<>();
//        assertThat(set.add(null)).isTrue();
//        assertThat(set.contains(null)).isTrue();
//        assertThat(set.add(null)).isFalse();
//    }
//
//    @Test
//    void whenHaveElementAndWhenHaveNotElement() {
//        Set<Integer> set = new SimpleSet<>();
//        set.add(7);
//        Iterator<Integer> it = set.iterator();
//        assertThat(it.hasNext()).isTrue();
//        assertThat(it.next()).isEqualTo(7);
//        assertThat(it.hasNext()).isFalse();
//    }
//    @Test
//    void  whenGetIteratorThrice() {
//        Set<Integer> set = new SimpleSet<>();
//        set.add(7);
//        set.add(9);
//        set.add(null);
//        Iterator<Integer> it = set.iterator();
//        assertThat(it.next()).isEqualTo(7);
//        assertThat(it.next()).isEqualTo(9);
//        assertThat(it.next()).isEqualTo(null);
//    }
//}