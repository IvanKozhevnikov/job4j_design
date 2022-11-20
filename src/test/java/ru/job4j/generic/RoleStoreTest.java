package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
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