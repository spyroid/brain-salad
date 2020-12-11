package com.symetricum.brainsalad.adventofcode.day10;

import com.symetricum.brainsalad.ALL;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AdapterArray extends ALL {

    static Set<Integer> vals = new HashSet<>();
    static Map<Integer, AtomicInteger> counters = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = from("adventofcode/day10/data.txt");

        while (scanner.hasNextInt()) vals.add(scanner.nextInt());
        int m = vals.stream().max(Comparator.comparingInt(o -> o)).get();
        println(m + "");
        vals.add(m + 3);
//        vals.sort(Comparator.comparingInt(value -> value));

        println(find(new int[]{1, 2, 3}) + "");
        println(find(new int[]{2, 3, 1}) + "");
        println(find(new int[]{3, 2, 1}) + "");
    }

    static int find(int[] rates) {
        counters.clear();
        int prev = 0;
        while (true) {
            boolean f = false;
            for (int i : rates) {
                if (vals.contains(prev + i)) {
                    prev += i;
                    if (!counters.containsKey(i)) counters.put(i, new AtomicInteger(0));
                    counters.get(i).getAndDecrement();
                    f = true;
                    break;
                }
            }
            if (!f) break;
        }
        int res = counters.get(1).get() * counters.get(3).get();
        return res;
    }
}
