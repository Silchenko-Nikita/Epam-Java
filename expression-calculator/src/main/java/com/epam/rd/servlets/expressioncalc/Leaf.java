package com.epam.rd.servlets.expressioncalc;

public class Leaf extends Component {
    DataType dataType;

    public Leaf(String string, Calculator calculator, DataType dataType) {
        super(string, calculator);
        this.dataType = dataType;
    }

    @Override
    public int countLeafs() {
        return 1;
    }

    @Override
    public void add(Component c) {
    }

    @Override
    public void remove(Component c) {
    }

    @Override
    public Numeric calculate() {
        switch (dataType){
            case NUMERIC:
                return calculator.calculateNumeric(string);
            case VAR:
                Numeric val = calculator.getNumeric(string);
                if (val == null) {
                    throw new RuntimeException("no such variable");
                }
                return val;
            default:
                return null;
        }
    }
}