package com.symetricum.brainsalad.adventofcode.day9;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class EncodingError {

    static Scanner scanner = new Scanner(Objects.requireNonNull(EncodingError.class.getClassLoader().getResourceAsStream("adventofcode/day9/data.txt")));
    static Set<Integer> preamble = new HashSet<>();

    public static void main(String[] args) {

        int c = 0;
        while (scanner.hasNextInt()) {
            Integer v = scanner.nextInt();

            if (c++ < 25) {
                preamble.add(v);
            } else {
                if (!isSum(v)) {
                    System.out.println("not a sum = " + v);
                    break;
                }
            }
        }

    }

    static boolean isSum(Integer v) {

        for (Integer p: preamble) {
            if (preamble.contains(v - p)) return true;
        }

        return false;
    }
}
