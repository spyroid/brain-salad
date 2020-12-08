package com.symetricum.brainsalad.adventofcode.day4;

import com.symetricum.brainsalad.adventofcode.day1.ReportRepair;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Passports2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(ReportRepair.class.getClassLoader().getResourceAsStream("adventofcode/day4/data2.txt"));

        Map<String, String> data = new HashMap<>();
        int validCount = 0;
        int totalCount = 0;

        List<String> checkFields = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (StringUtils.hasLength(line)) {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    String[] pp = part.split(":");
                    data.put(pp[0], pp[1]);
                }
            } else {

                if (checkFields.stream().map(f -> {
                    try {
                        return validate(data, f);
                    } catch (Exception e) {
                        return false;
                    }
                }).filter(valid -> valid).count() == checkFields.size()) {
                    validCount++;
                }

                data.clear();
                totalCount++;
            }
//            System.out.println(">> " + line);
        }


        System.out.println("total = " + totalCount + " valid = " + validCount);
    }

    static Boolean validate(Map<String, String> data, String key) {
        String value = data.get(key);
        if (value == null) return false;
        if (key.equals("byr")) return validateBYR(value);
        if (key.equals("iyr")) return validateIYR(value);
        if (key.equals("eyr")) return validateEYR(value);
        if (key.equals("hgt")) return validateHGT(value);
        if (key.equals("hcl")) return validateHCL(value);
        if (key.equals("ecl")) return validateECL(value);
        if (key.equals("pid")) return validatePID(value);

        return true;
    }

    private static Boolean validatePID(String value) {
        return value.matches("[0-9]{9}");
    }

    private static Boolean validateECL(String value) {
        return value.matches("(amb|blu|brn|gry|grn|hzl|oth)");
    }

    private static Boolean validateHCL(String value) {
        return value.matches("#[0-9a-f]{6}");
    }

    private static Boolean validateHGT(String value) {
        if (value.endsWith("in")) {
            int v = Integer.parseInt(value.substring(0, value.length() - 2));
            return v >= 59 && v <= 76;
        } else if (value.endsWith("cm")) {
            int v = Integer.parseInt(value.substring(0, value.length() - 2));
            return v >= 150 && v <= 193;
        } else {
            return false;
        }
    }

    private static Boolean validateEYR(String value) {
        int v = Integer.parseInt(value);
        return v >=2020 && v <= 2030;
    }

    private static Boolean validateBYR(String value) {
        int v = Integer.parseInt(value);
        return v >=1920 && v <= 2002;
    }

    private static Boolean validateIYR(String value) {
        int v = Integer.parseInt(value);
        return v >=2010 && v <= 2020;
    }

}
