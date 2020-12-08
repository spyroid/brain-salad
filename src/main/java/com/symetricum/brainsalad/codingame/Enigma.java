package com.symetricum.brainsalad.codingame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Slf4j
//@Service
public class Enigma {

    @PostConstruct
    void start() {
        String rotor1 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
        String rotor2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
        String rotor3 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        int startNumber = 7;

        String message = "WEATHERREPORTWINDYTODAY";

//        StringBuilder s1 = new StringBuilder();
//        for (int i = 0; i < message.length(); i++) {
//            s1.append((char) (message.charAt(i) + startNumber + i));
//        }
//        log.info(s1.toString());

        int v = ('Y' - 65 + 22 + startNumber) % ('Z' - 65);
        log.info(v + "");

        AtomicInteger inc = new AtomicInteger();
        String res = message.chars().boxed()
                .map(i -> {
                    int c = i + startNumber + inc.getAndIncrement();
                    if (c > 'Z') c = (c - 65) % ('Z' - 65) + 65;
                    log.info(i + " -> " + c + " " + inc.get());
                    return c;
                })
                .map(c -> rotor1.charAt(c - 65))
                .map(c -> rotor2.charAt(c - 65))
                .map(c -> rotor3.charAt(c - 65))
                .collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString));


        // 65

        int i = 1;

    }

}
