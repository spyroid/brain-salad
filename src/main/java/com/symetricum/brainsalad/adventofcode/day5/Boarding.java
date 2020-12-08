package com.symetricum.brainsalad.adventofcode.day5;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Boarding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(Objects.requireNonNull(Boarding.class.getClassLoader().getResourceAsStream("adventofcode/day5/data.txt")));

        List<Seat> seats = new ArrayList<>();
        while (scanner.hasNextLine()) seats.add(Seat.from(scanner.nextLine()));

        seats.sort((s1, s2) -> s2.id - s1.id);
        System.out.println("maxId = " + seats.get(0).id);

        for (int i = 1; i < seats.size(); i++) {
            if (seats.get(i - 1).id - seats.get(i).id == 2) {
                System.out.println(" >> " + seats.get(i - 1).toString());
                System.out.println(" >> " + seats.get(i).toString());
            }
        }
    }

    @Data
    static class Seat {
        int row;
        int column;
        int id;

        static Seat from(String line) {
            Seat seat = new Seat();
            for (int i = 0; i < 7; i ++) if (line.charAt(i) == 'B') seat.row = seat.row | 1 << (7 - i - 1);
            for (int i = 7; i < 10; i ++) if (line.charAt(i) == 'R') seat.column = seat.column | 1 << (10 - i - 1);
            seat.id = seat.row * 8 + seat.column;
            return seat;
        }
    }
}
