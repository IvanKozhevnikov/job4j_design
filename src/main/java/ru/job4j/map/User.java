package ru.job4j.map;

import java.util.*;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private int birthday;

    public User(String name, int children, int birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(2000, Calendar.APRIL, 17, 11, 5, 0);
        User user1 = new User("Alex", 2, 3);
        User user2 = new User("Alex", 2, 3);
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
        System.out.println();
        System.out.println(map.size());
        System.out.println((hash2 % map.size()) == (hash1 % map.size()));
        System.out.println((hash2 % map.size()));
        System.out.println("________" + (hash1 % map.size()));
        System.out.println(hash1 == hash2);
        System.out.println(hashCode1 >>> 2);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name
                + '\'' + ", children=" + children
                + ", birthday=" + birthday + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && birthday == user.birthday && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}