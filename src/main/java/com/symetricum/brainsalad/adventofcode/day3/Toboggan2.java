package com.symetricum.brainsalad.adventofcode.day3;

import com.symetricum.brainsalad.adventofcode.day1.ReportRepair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Toboggan2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(ReportRepair.class.getClassLoader().getResourceAsStream("adventofcode/day3/data2.txt"));

        List<TreeCounter> counters = new ArrayList<>();
        counters.add(new TreeCounter(1, 1));
        counters.add(new TreeCounter(3, 1));
        counters.add(new TreeCounter(5, 1));
        counters.add(new TreeCounter(7, 1));
        counters.add(new TreeCounter(1, 2));

        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(">> " + line);
            counters.forEach(c -> c.step(line));
        }

        AtomicLong res = counters.stream().map(treeCounter -> treeCounter.count)
                .collect(() -> new AtomicLong(1), (a, b) -> a.set(a.get() * b), (a, b) -> {});

        System.out.println("res = " + res.get());
    }

    static class TreeCounter {
        int x = 0, y = 0;
        int stepX, stepY;
        public int count = 0;

        public TreeCounter(int stepX, int stepY) {
            this.stepX = stepX;
            this.stepY = stepY;
        }

        public void step(String str) {
            if (y != 0) {
                char c = str.charAt(x);
//                if (y % stepY == 0) System.out.println("[" + x + ", " + y + "] = " + c);
                if (y % stepY == 0 && c == '#') {
                    count++;
                }
            }
            doStep(str.length());
        }

        private void doStep(int length) {
            y = y + 1;
            if (y % stepY == 0) {
                x = x + stepX;
                x = x >= length ? x - length : x;
            }
        }

    }
}
