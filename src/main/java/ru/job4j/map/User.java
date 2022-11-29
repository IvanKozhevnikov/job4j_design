package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Date birthday;

    public User(String name, int children, Date birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(2000, 7, 17);
        User user1 = new User("Alex", 2, birthday.getTime());
        User user2 = new User("Alex", 2, birthday.getTime());
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("Map" + map);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        System.out.printf("user1 - хэшкод: %s, хэш: %s, бакет: %s", hashCode1, hash1, bucket1);
        System.out.println();
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        System.out.printf("user2 - хэшкод: %s, хэш: %s, бакет: %s", hashCode2, hash2, bucket2);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name
                + '\'' + ", children=" + children
                + ", birthday=" + birthday + '}';
    }
}