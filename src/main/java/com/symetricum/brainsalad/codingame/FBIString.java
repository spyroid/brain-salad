package com.symetricum.brainsalad.codingame;

public class FBIString {

    public static void main(String[] args) {
        String alph = "H_eo: Wrld!";//""abcdefghijklmnopqrstuvwxyz";
        StringBuilder res = new StringBuilder();

        long CODE = 34170657950616L;//35;
        long len = alph.length();
        int i = 0;

        while (CODE >= len ) {
            long a = ((CODE) % len);
            CODE = CODE / len;
            if (i > 0) a = a - 1;
            i++;
            System.err.println("CODE = " + CODE + " a = " + a + " res = " + res.toString());
            res.append(alph.charAt((int) (a < 0 ? len + a : a)));
        }
        res.append(alph.charAt((int) (CODE - 1)));

        System.err.println(res);
    }

}
