package com.symetricum.brainsalad.adventofcode.day2;

import java.io.InputStream;
import java.util.Scanner;

public class PasswordValid2 {

    public static void main(String[] args) {

        InputStream in = PasswordValid2.class.getClassLoader().getResourceAsStream("adventofcode/day2/passwords2.txt");
        Scanner scanner = new Scanner(in);

        int validCount = 0;

//        isValid("nnjn", 'n', 2, 3);

        while (scanner.hasNext()) {
            String[] countP = scanner.next().split("-");
            String letter = scanner.next();
            String password = scanner.next();

            if (isValid(password, letter.charAt(0), Integer.valueOf(countP[0]), Integer.valueOf(countP[1]))) validCount++;
        }

        System.out.println("Valid passwords = " + validCount);

    }

    static boolean isValid(String password, char letter, Integer pos1, Integer pos2) {

        boolean res = password.charAt(pos1 - 1) == letter ^ password.charAt(pos2 - 1) == letter;
        System.out.println(password + " [" + pos1 + " " + pos2 + "] " + letter + " - " + res);

        return res;
    }

}
