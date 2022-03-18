package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String str : origin) {
            check.add(str);
        }
        String[] text = duplicateText.split(" ");
        for (String str : text) {
            if (!check.contains(str)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

}
