package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UserStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("1", new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("10", new User("10", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.replace("1", new User("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.replace("10", new User("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }

    @Test
    void whenAddAndFindThenUsernameIsIvan() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("7", "Ivan"));
        Role result = roleStore.findById("7");
        assertThat(result.getRoleName()).isEqualTo("Ivan");
    }

    @Test
    void whenAddAndFindThenUserNineIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        Role result = roleStore.findById("9");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsIvan() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("3", "Ivan"));
        roleStore.add(new Role("3", "John"));
        Role result = roleStore.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceThenUsernameIsJohn() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("4", "Ivan"));
        roleStore.replace("4", new Role("4", "John"));
        Role result = roleStore.findById("4");
        assertThat(result.getRoleName()).isEqualTo("John");
    }

    @Test
    void whenNoReplaceUserIvanThenNoChangeUsername() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("3", "Ivan"));
        roleStore.replace("7", new Role("7", "John"));
        Role result = roleStore.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Ivan");
    }

    @Test
    void whenDeleteUserIvanThenUserIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("5", "Ivan"));
        roleStore.delete("5");
        Role result = roleStore.findById("5");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsIvan() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("7", "Ivan"));
        roleStore.delete("11");
        Role result = roleStore.findById("7");
        assertThat(result.getRoleName()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceNameOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Ivan"));
        boolean rsl = roleStore.replace("1", new Role("1", "John"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNameNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("7", "Ivan"));
        boolean rsl = roleStore.replace("12", new Role("12", "John"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteValueOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("3", "Ivan"));
        boolean rsl = roleStore.delete("3");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteValueNotOkThenFalse() {
        UserStore userStore = new UserStore();
        userStore.add(new User("3", "Ivan"));
        boolean rsl = userStore.delete("4");
        assertThat(rsl).isFalse();
    }
}