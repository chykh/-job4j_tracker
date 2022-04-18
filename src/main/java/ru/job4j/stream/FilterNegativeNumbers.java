package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNegativeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 14, 25, -6, -1, 0, 18);
        List<Integer> positive = numbers.stream().
                filter(number -> number > 0).
                collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}
