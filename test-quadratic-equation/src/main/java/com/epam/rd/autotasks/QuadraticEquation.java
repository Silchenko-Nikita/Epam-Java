package com.epam.rd.autotasks;

public class QuadraticEquation {

    public String solve(double a, double b, double c) {
        if (a == 0.0) {
            throw new IllegalArgumentException();
        }

        double D = b * b - 4 * a * c;

        if (D == 0.0) {
            double x = -b / (2 * a);

            return String.valueOf(x);
        } else if (D < 0) {
            return "no roots";
        }

        double x1 = (-b - Math.sqrt(D)) / (2 * a);
        double x2 = (-b + Math.sqrt(D)) / (2 * a);

        return x1 < x2 ? (String.valueOf(x1) + " " + String.valueOf(x2)) : (String.valueOf(x2) + " " + String.valueOf(x1));
    }
}