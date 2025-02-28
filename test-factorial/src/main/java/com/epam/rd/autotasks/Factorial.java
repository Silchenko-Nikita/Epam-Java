package com.epam.rd.autotasks;

public class Factorial {
    public String factorial(String n) {
        int intN;

        try {
            intN = Integer.parseInt(n);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        if (intN < 0) {
            throw new IllegalArgumentException();
        }

        int intRes = 1;
        for (int i = 1; i <= intN; i++) {
            intRes *= i;
        }

        return String.valueOf(intRes);
    }
}
