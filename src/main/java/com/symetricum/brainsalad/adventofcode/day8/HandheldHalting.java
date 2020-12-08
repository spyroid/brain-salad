package com.symetricum.brainsalad.adventofcode.day8;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class HandheldHalting {

    static Scanner scanner = new Scanner(Objects.requireNonNull(HandheldHalting.class.getClassLoader().getResourceAsStream("adventofcode/day8/data.txt")));
    static List<CMD> ops = new ArrayList<>();

    public static void main(String[] args) {
        run1();
    }

    public static void run1() {
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] pp = line.split(" ");
            ops.add(new CMD(OP.valueOf(pp[0].toUpperCase()), Integer.parseInt(pp[1])));
        }


        CPU cpu = new CPU();
        int res = cpu.run(ops);

        System.out.println("res = " + res + " " + cpu.toString());

    }

    @Data
    static class CPU {

        int addr = 0;
        int acc = 0;

        int run(List<CMD> program) {
            while (true) {
                if (addr >= program.size()) {
                    return 0;
                }
                CMD cmd = program.get(addr);
                System.out.println("[" + addr + "] " + cmd.toString());
                if (cmd.counter.incrementAndGet() > 1) return addr;
                if (cmd.op == OP.ACC) acc += cmd.val;
                if (cmd.op == OP.JMP) addr += cmd.val; else addr++;
            }
        }

    }

    public enum OP { ACC, NOP, JMP}

    @Data
    @RequiredArgsConstructor
    static class CMD {
        final OP op;
        AtomicInteger counter = new AtomicInteger();
        final int val;

    }
}
