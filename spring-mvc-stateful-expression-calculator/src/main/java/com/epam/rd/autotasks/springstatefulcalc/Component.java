package com.epam.rd.autotasks.springstatefulcalc;

public abstract class Component {
    protected String string;
    protected Calculator calculator;

    public Component(String string, Calculator calculator){
        this.string = string;
        this.calculator = calculator;
    }

    public abstract void add(Component c);

    public abstract int countLeafs();
    public abstract void remove(Component c);

    public abstract Numeric calculate();
}