package com.symetricum.brainsalad.adventofcode.day4;

import com.symetricum.brainsalad.adventofcode.day1.ReportRepair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Passports {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(ReportRepair.class.getClassLoader().getResourceAsStream("adventofcode/day4/data.txt"));

        Map<String, String> data = new HashMap<>();
        int validCount = 0;
        int totalCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (StringUtils.hasLength(line)) {
                String[] parts = line.split(" ");
                for (String part: parts) {
                    String[] pp = part.split(":");
                    data.put(pp[0], pp[1]);
                }
            } else {
                if ( data.containsKey("byr") && data.containsKey("iyr") && data.containsKey("eyr")
                        && data.containsKey("hgt") && data.containsKey("hcl") && data.containsKey("ecl")
                        && data.containsKey("pid") ) {
                    validCount++;
                }
                data.clear();
                totalCount++;
            }
//            System.out.println(">> " + line);
        }


        System.out.println("total = " + totalCount + " valid = " + validCount);
    }

}
