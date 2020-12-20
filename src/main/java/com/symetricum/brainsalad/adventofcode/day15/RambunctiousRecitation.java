package com.symetricum.brainsalad.adventofcode.day15;

import com.symetricum.brainsalad.ALL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RambunctiousRecitation extends ALL {

    public static void main(String[] args) {
        System.out.println("res = " + find(List.of(20, 9, 11, 0, 1, 2), 2020));
        System.out.println("res2 = " + find(List.of(20, 9, 11, 0, 1, 2), 30000000));
    }

    private static Integer find(List<Integer> items, int limit) {
        var turns = new ArrayList<>(items);
        var lasts = new HashMap<Integer, Integer>();

        for (int i = 0; i < turns.size() - 1; i++) {
            lasts.put(items.get(i), i);
        }

        while (turns.size() < limit) {
            var last = turns.get(turns.size() - 1);
            var next = lasts.containsKey(last) ? turns.size() - 1 - lasts.get(last) : 0;
            lasts.put(last, turns.size() - 1);
            turns.add(next);
        }
        return turns.get(turns.size() - 1);
    }
}
