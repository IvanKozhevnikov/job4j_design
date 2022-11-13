package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
    @Test
    void checkEmpt() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad :: parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
    @Test
    void checkEmp() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("= not key"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: = not key does not contain a key");
    }
    @Test
    void checkValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("not value ="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: not value = does not contain a value");
    }
    @Test
    void checkSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("not symbol"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: not symbol does not contain the symbol");
    }
}
