package com.symetricum.brainsalad.codingame;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
public class OneZero {

    @PostConstruct
    void init() {
        System.err.println(find("11101111"));
        System.err.println(find("01"));
        System.err.println(find("00"));
        System.err.println(find("10"));
    }

    private int find(String str) {
        System.err.println(str);
        int res = 0;

        for (int i = 0; i < str.length(); i++) {
            int zeroCount = 0;
            int len = 0;

            for (int j = i; j < str.length(); j++) {
                if (str.charAt(j) == '1') {
                    len++;
                } else {
                    if (zeroCount++ == 0) {
                        len++;
                    } else {
                        break;
                    }
                }
            }
            if (len > res) res = len;
        }
        return res;
    }

}
