package com.symetricum.brainsalad.adventofcode.day7;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class HandyHaversacks {

    static Scanner scanner = new Scanner(Objects.requireNonNull(HandyHaversacks.class.getClassLoader().getResourceAsStream("adventofcode/day7/data.txt")));
    static Map<String, Bag> bags = new HashMap<>();

    public static void main(String[] args) {

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Bag bag = Bag.parse(line);
            bags.put(bag.id, bag);
        }

        long count = find2(bags.get("shiny gold"), 1) - 1;

        System.out.println("shiny gold count = " + count);
    }

    private static int find(Bag bag, int count) {
        int res = count;
        if (bag.emb.containsKey("shiny gold")) return count + 1;
        for (String id : bag.emb.keySet()) {
            res += find(bags.get(id), count);
        }
        return res;
    }

    private static int find2(Bag bag, int count) {
        int res = count;
        for (String id : bag.emb.keySet()) {
            res += find2(bags.get(id), count) * bag.emb.get(id);
        }
        return res;
    }

    @Data
    static class Bag {
        String id;
        Map<String, Integer> emb = new HashMap<>();

        static Bag parse(String line) {
            Bag bag = new Bag();
            String[] pp1 = line.split("bags contain");

            bag.id = pp1[0].strip();

            if (pp1[1].contains("no other")) return bag;

            String[] pp2 = pp1[1].replaceAll("bags", "").replaceAll("bag", "").replaceAll("\\.", "").split(",");
            for (String p : pp2) {
                int pos = p.strip().indexOf(" ");
                Integer count = Integer.valueOf(p.strip().substring(0, pos));
                String id = p.strip().substring(pos).strip();
                bag.emb.put(id, count);
            }
            return bag;
        }
    }
}
