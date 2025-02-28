package com.epam.rd.servlets.expressioncalc;

import java.util.ArrayList;

public class Composite extends Component {
    private ArrayList<Component> children = new ArrayList<Component>();
    Operation operation;

    public Composite(String string, Calculator calculator, Operation operation) {
        super(string, calculator);
        this.operation = operation;
    }

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public int countLeafs() {
        int num = 0;
        for (Component component: children) {
            num += component.countLeafs();
        }
        return num;
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public Numeric calculate() {
        Numeric data;

        switch (operation){
            case MULT:
                data = children.get(0).calculate();
                for (int i = 1; i < children.size(); i++) {
                    data = data.mult(children.get(i).calculate());
                }

                break;
            case DIV:
                data = children.get(0).calculate();
                for (int i = 1; i < children.size(); i++) {
                    data = data.divide(children.get(i).calculate());
                }

                break;
            case ADD:
                data = children.get(0).calculate();
                for (int i = 1; i < children.size(); i++) {
                    data = data.sum(children.get(i).calculate());
                }

                break;
            case SUB:
                data = children.get(0).calculate();
                for (int i = 1; i < children.size(); i++) {
                    data = data.subtract(children.get(i).calculate());
                }

                break;
            case RET:
                return children.get(0).calculate();
            default:
                return null;
        }
        return data;
    }
}