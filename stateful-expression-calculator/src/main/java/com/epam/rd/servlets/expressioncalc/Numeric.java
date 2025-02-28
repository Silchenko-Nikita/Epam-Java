package com.epam.rd.servlets.expressioncalc;

import java.util.Objects;

public class Numeric {
    private int val;

    public Numeric(int val){
        this.val = val;
    }

    public double getVal() {
        return val;
    }

    public Numeric sum(Numeric numeric) {
        return new Numeric(val + numeric.val);
    }

    public Numeric subtract(Numeric numeric) {
        return new Numeric(val - numeric.val);
    }

    public Numeric mult(Numeric numeric) {
        return new Numeric(val * numeric.val);
    }

    public Numeric divide(Numeric numeric) {
        return new Numeric(val / numeric.val);
    }

    @Override
    public String toString() {
        return String.format("%d", val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numeric numeric = (Numeric) o;
        return Double.compare(numeric.val, val) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
