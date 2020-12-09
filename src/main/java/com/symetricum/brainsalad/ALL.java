package com.symetricum.brainsalad;

import java.util.Objects;
import java.util.Scanner;

public class ALL {

    public static Scanner from(String file) {
        return new Scanner(Objects.requireNonNull(ALL.class.getClassLoader().getResourceAsStream(file)));
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(String str) {
        System.out.println(str);
    }
}
