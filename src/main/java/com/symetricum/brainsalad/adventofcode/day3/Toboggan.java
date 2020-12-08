package com.symetricum.brainsalad.adventofcode.day3;

import com.symetricum.brainsalad.adventofcode.day1.ReportRepair;

import java.util.Scanner;

public class Toboggan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(ReportRepair.class.getClassLoader().getResourceAsStream("adventofcode/day3/data.txt"));

        int x = -3;
        int res = 0;

        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(">> " + line);
            x = x + 3;
            if (x == 0) {
                continue;
            }
            if (x >= line.length()) {
                x = x - line.length();
            }
            System.out.println("char [" + x + "] = "+ line.charAt(x));
            System.out.println();

            if (line.charAt(x) == '#') res++;
        }

        System.out.println("res = " + res);
    }
}
