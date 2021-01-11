package com.symetricum.brainsalad.adventofcode.day25;

public class ComboBreaker {

    public static void main(String[] args) {

        var cardPub = 3469259L;
        var doorPub = 13170438L;
        var loopSize = 0L;

        for (var value = 1L; value != cardPub; loopSize++) value = (7 * value) % 20201227;

        var key = 1L;
        for (var i = 0; i < loopSize; i++) key = (doorPub * key) % 20201227;

        System.out.println(key);
    }
}
