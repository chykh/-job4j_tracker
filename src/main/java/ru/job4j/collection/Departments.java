package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = "";
            for (String el : value.split("/")) {
                 tmp.add(start + el);
                 start += el + "/";
            }
        }
        return new ArrayList<>(tmp);
    }

    public static List<String> sortAsc(List<String> deps) {
        Collections.sort(deps);
        return deps;
    }

    public static List<String> sortDesc(List<String> deps) {
        Collections.sort(deps, new DepDescComp());
        return deps;
    }

}
