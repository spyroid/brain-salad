package com.symetricum.brainsalad.codingame;

import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class CodersStrikeBack {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int x = in.nextInt(); // x position of your pod
        int y = in.nextInt(); // y position of your pod
        int nextCheckpointX = in.nextInt(); // x position of the next check point
        int nextCheckpointY = in.nextInt(); // y position of the next check point


        int x1 = x, y1 = y, x2 = nextCheckpointX, y2 = nextCheckpointY;
        double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.err.println("x,y = " + x1 + "," + y1 + " x2,y2 = " + x2 + "," + y2);
        System.err.println("dist = " + dist);

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;
        int dd = dx - dy;

        int xx = x1, yy = y1;

        if (dd * 2 > -dy) {
            xx = xx + ix;
        } else {
            yy = yy + iy;
        }


        int thrust = 100;

        // Edit this line to output the target position
        // and thrust (0 <= thrust <= 100)
        // i.e.: "x y thrust"
        System.out.println(xx + " " + yy + " " + thrust);

    }
}
