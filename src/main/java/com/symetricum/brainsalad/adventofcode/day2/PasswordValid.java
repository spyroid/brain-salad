package com.symetricum.brainsalad.adventofcode.day2;

import java.io.InputStream;
import java.util.Scanner;

public class PasswordValid {

    public static void main(String[] args) {

        InputStream in = PasswordValid.class.getClassLoader().getResourceAsStream("adventofcode/day2/passwords.txt");
        Scanner scanner = new Scanner(in);

        int validCount = 0;

        while (scanner.hasNext()) {
            String[] countP = scanner.next().split("-");
            String letter = scanner.next();
            String password = scanner.next();

            if (isValid(password, letter.charAt(0), Integer.valueOf(countP[0]), Integer.valueOf(countP[1]))) validCount++;
        }

        System.out.println("Valid passwords = " + validCount);

    }

    static boolean isValid(String password, char letter, Integer min, Integer max) {

        long count = password.chars().boxed().filter(i -> letter == i).count();
        boolean res = count >= min && count <= max;
        System.out.println(password + " " + min + " " + max + " " + letter + " - " + res);

        return res;
    }

}
