package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        int added = 0;
        Map<Integer, String> cur = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            if (cur.containsKey(user.getId()) && !cur.containsValue(user.getName())) {
                changed++;
            }
            if (!cur.containsKey(user.getId())) {
                deleted++;
            }
                added = current.size() - previous.size() + deleted;
        }
        return new Info(added, changed, deleted);
    }
}