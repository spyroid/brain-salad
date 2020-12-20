package com.symetricum.brainsalad.adventofcode.day13;

import com.symetricum.brainsalad.ALL;
import one.util.streamex.MoreCollectors;
import one.util.streamex.StreamEx;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ShuttleSearch extends ALL {

    public static void main(String[] args) {
        Scanner scanner = from("adventofcode/day13/data0.txt");

        int base = scanner.nextInt();
        String aaa = scanner.next();
        List<Long> input = StreamEx.of(aaa.split(",")).map(v -> v.equals("x") ? -1L : parseLong(v)).toList();

        System.out.println("1 = " + part11(base, input));
        System.out.println("2 = " + part2(List.of(17L, -1L, 13L, 19L)));
    }

    static long part11(long base, List<Long> input) {
        return StreamEx.of(input)
                .filter(a -> a > 0)
                .mapToEntry(s -> s, v -> v - base % v)
                .max((o1, o2) -> (int) (o2.getValue() - o1.getValue()))
                .map(m -> m.getKey() * m.getValue()).get();
    }


    static long part22(List<Long> input) {

//        int base = 1068781;

        for (long base = 0; base < Long.MAX_VALUE; base++) {

            int sum = 0;

            for (int i = 0; i < input.size(); i++) {
                long v = input.get(i);
                if (v == -1) {
                    sum++;
                } else {
                    long z = (base + i) % v;
                    if (z == 0) sum++;
                }
            }

            if (sum == input.size()) return base;

        }

        return 0;
    }

    /**
     * @param input
     * @return
     */
    private static long part2(List<Long> input) {
        var times = input;
//                Arrays.stream(input.get(1).split(","))
//                .map(t -> t.equals("x") ? -1 : Long.parseLong(t))
//                .collect(Collectors.toList());
        long start = 0;
        long mul = 0;
        for (int i = 0; i < times.size(); ++i) {
            var v = times.get(i);
            if (v == -1) {
                continue;
            }
            if (start == 0) {
                start = v;
                mul = v;
            } else {
                long b = v;
                var goal = start + i;
                while (b != goal) {
                    var a = goal / v;
                    if (a * v == goal) {
                        break;
                    }
                    b = (a + 1) * v;
                    while (b > goal) {
                        goal += mul;
                    }
                }
                var goal2 = goal + mul;
                while (b != goal2) {
                    var a = goal2 / v;
                    if (a * v == goal2) {
                        break;
                    }
                    b = (a + 1) * v;
                    while (b > goal2) {
                        goal2 += mul;
                    }
                }
                start = goal - i;
                mul = goal2 - goal;
            }
        }
        return start;
    }

    private static int part1(List<String> input) {
        int start = parseInt(input.get(0));
        var times = input.get(1).split(",");
        var best = Integer.MAX_VALUE;
        var result = 0;
        for (String time : times) {
            if (time.equals("x")) {
                continue;
            }
            var t = parseInt(time);
            if (start % t == 0) {
                best = 0;
                result = 0;
                break;
            } else {
                var d = start / t;
                var m = (d + 1) * t - start;
                if (m < best) {
                    best = m;
                    result = best * t;
                }
            }
        }
        return result;
    }
}
