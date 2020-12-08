package com.symetricum.brainsalad.adventofcode.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportRepair {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(ReportRepair.class.getClassLoader().getResourceAsStream("adventofcode/day1/reports2.txt"));

        List<Integer> vals = new ArrayList<>();

        while (scanner.hasNext()) {
            Integer v = Integer.valueOf(scanner.next());
            vals.add(v);
        }

        System.out.println(find2(vals));
    }

    static long find2(List<Integer> vals) {
        for (int i = 0; i < vals.size(); i++) {
            for (int j = i; j < vals.size(); j++) {
                for (int k = j; k < vals.size(); k++) {
                    int x = vals.get(i);
                    int y = vals.get(j);
                    int z = vals.get(k);
                    if (x + y + z == 2020) {
                        return x * y * z;
                    }
                }
            }
        }
        return 0;
    }

    static int find(List<Integer> vals) {
        for (int i = 0; i < vals.size(); i++) {
            for (int j = i; j < vals.size(); j++) {
                int x = vals.get(i);
                int y = vals.get(j);
                if (x + y == 2020) {
                    return x * y;
                }
            }
        }
        return 0;
    }
}
