package com.symetricum.brainsalad.codingame;

public class Abracadabra {

    public static void main(String[] args) {
        add("1", 'a');
        add("001", 'b');
        add("000", 'r');
        add("011", 'c');
        add("010", 'd');

        String res = find("10010001011101010010001");

        System.out.println(res);
        int i = 1;

    }

    static String find(String crap) {
        Node now = root;
        int pos = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < crap.length(); i++) {
            now = crap.charAt(i) == '0' ? now.zero : now.one;
            if (now == null) {
                return "DECODE FAIL AT INDEX " + pos;
            }
            if (now.value > 0) {
                res.append(now.value);
                now = root;
                pos = i;
            }
        }
        if (pos + 1 != crap.length()) return "DECODE FAIL AT INDEX " + (pos + 1);
        return res.toString();
    }

    static Node root = new Node();

    public static void add(String code, char value) {
        Node node = root;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                if (node.zero == null) node.zero = new Node();
                node = node.zero;
            } else {
                if (node.one == null) node.one = new Node();
                node = node.one;
            }
        }
        node.value = value;
    }

    static class Node {
        public char value = 0;
        public Node zero, one;
    }
}
