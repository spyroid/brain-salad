package com.symetricum.brainsalad.adventofcode.day6;

import com.symetricum.brainsalad.ALL;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomCustoms extends ALL {

    public static void main(String[] args) {
        Scanner scanner = from("adventofcode/day6/data.txt");
        List<Q> qs = new ArrayList<>();
        Q q = new Q();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isBlank()) {
                q.add(line);
            } else {
                qs.add(q);
                println(q.toString());
                q = new Q();
            }
        }

        int sum = qs.stream().mapToInt(q1 -> q1.chars.size()).sum();
        System.out.println("sum  = " + sum);
    }

    @Data
    static class Q {
        Map<Integer, AtomicInteger> chars = new HashMap<>();

        void add(String line) {
            line.chars().forEach(i -> {
                if (!chars.containsKey(i)) chars.put(i, new AtomicInteger(1));
                else chars.get(i).incrementAndGet();
            });
        }
    }


}
