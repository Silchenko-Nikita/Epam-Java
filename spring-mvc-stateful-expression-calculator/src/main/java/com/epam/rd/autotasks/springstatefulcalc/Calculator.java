package com.epam.rd.autotasks.springstatefulcalc;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    Map<String, String> quotes = new HashMap<>();
    Map<String, String> varsCache = new HashMap<>();

    private final String identifier = "$";

    private final String numericString = "(-?\\d+)";
    private final Pattern numericPattern = Pattern.compile(numericString);

    private final String variableString = "([a-z])";
    private final Pattern variablePattern = Pattern.compile(variableString);

    private final String quoteMaskString = "\\$\\d+";
    private final Pattern quoteMaskPattern = Pattern.compile(quoteMaskString);

    private final String operandString = String.format("(%s|%s)", numericString, variableString);
    private final Pattern operandPattern = Pattern.compile(operandString);

    private final String plusDelimiter = "\\+";
    private final Pattern plusDelimiterPattern = Pattern.compile(plusDelimiter);

    private final String multDelimiter = "\\*";
    private final Pattern multDelimiterPattern = Pattern.compile(multDelimiter);

    private final String subDelimiter = "-";
    private final Pattern subDelimiterPattern = Pattern.compile(subDelimiter);

    private final String divDelimiter = "/";
    private final Pattern divDelimiterPattern = Pattern.compile(divDelimiter);

    public Numeric calculate(String input, Map<String, String> varsCache){
        this.varsCache = varsCache;

        return buildTree(input).calculate();
    }

    private Component buildTree(String string) throws NullPointerException {
        Component component;
        Matcher plusMatcher = plusDelimiterPattern.matcher(string);
        Matcher subMatcher = subDelimiterPattern.matcher(string);
        Matcher multMatcher = multDelimiterPattern.matcher(string);
        Matcher divMatcher = divDelimiterPattern.matcher(string);

        string = collapseString(string);

        if(plusMatcher.find() && subMatcher.find()){
            if (plusMatcher.start() > subMatcher.start()) {
                component = new Composite(string, this, Operation.ADD);
                String[] operands = plusDelimiterPattern.split(string);

                for (String operand: operands) {
                    component.add(buildTree(operand));
                }
            } else {
                component = new Composite(string, this, Operation.SUB);
                String[] operands = subDelimiterPattern.split(string);

                for (String operand: operands) {
                    component.add(buildTree(operand));
                }
            }

        } else if(plusDelimiterPattern.matcher(string).find()){
            component = new Composite(string, this, Operation.ADD);
            String[] operands = plusDelimiterPattern.split(string);

            for (String operand: operands) {
                component.add(buildTree(operand));
            }

        } else if(subDelimiterPattern.matcher(string).find()){
            component = new Composite(string, this, Operation.SUB);
            String[] operands = subDelimiterPattern.split(string);

            for (String operand: operands) {
                component.add(buildTree(operand));
            }

        } else if(multMatcher.find() && divMatcher.find()){
            if (multMatcher.start() > divMatcher.start()) {
                component = new Composite(string, this, Operation.MULT);
                String[] operands = multDelimiterPattern.split(string);

                for (String operand: operands) {
                    component.add(buildTree(operand));
                }
            } else {
                component = new Composite(string, this, Operation.DIV);
                String[] operands = divDelimiterPattern.split(string);

                for (String operand: operands) {
                    component.add(buildTree(operand));
                }
            }

        } else if(multDelimiterPattern.matcher(string).find()){
            component = new Composite(string, this, Operation.MULT);
            String[] operands = multDelimiterPattern.split(string);

            for (String operand: operands) {
                component.add(buildTree(operand));
            }

        } else if(divDelimiterPattern.matcher(string).find()){
            component = new Composite(string, this, Operation.DIV);
            String[] operands = divDelimiterPattern.split(string);

            for (String operand: operands) {
                component.add(buildTree(operand));
            }
        } else if(quoteMaskPattern.matcher(string).matches()){
            component = new Composite(string, this, Operation.RET);
            component.add(buildTree(quotes.get(string)));

        } else if(variablePattern.matcher(string).matches()){
            component = new Leaf(string, this, DataType.VAR);

        } else if(numericPattern.matcher(string).matches()){
            component = new Leaf(string, this, DataType.NUMERIC);

        } else {
            throw new IllegalArgumentException("invalid input");
        }

        return component;
    }

    public boolean isValidExpression(String input) {
        try {
            buildTree(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    Numeric calculateNumeric(String input){
        return new Numeric(Integer.parseInt(input));
    }

    private String collapseString(String string){
        int lastTopLevelQuoteOpeningInd = 0;
        int openedQuotesNum = 0;

        StringBuilder collapsedStringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);

            if (ch == '('){
                openedQuotesNum++;

                if (openedQuotesNum == 1){
                    lastTopLevelQuoteOpeningInd = i;
                }
            } else if (ch == ')'){
                openedQuotesNum--;

                if (openedQuotesNum == 0){
                    String id = identifier + quotes.size();
                    collapsedStringBuilder.append(id);
                    quotes.put(id, string.substring(lastTopLevelQuoteOpeningInd + 1, i));
                } else if (openedQuotesNum < 0){
                    throw new IllegalArgumentException("invalid input");
                }
            } else if (openedQuotesNum == 0 && ch != ' '){
                collapsedStringBuilder.append(ch);
            }
        }

        if (openedQuotesNum > 0){
            throw new IllegalArgumentException("invalid input");
        }

        return collapsedStringBuilder.toString();
    }

    Numeric getNumeric(String input) {
        if (varsCache.containsKey(input) && varsCache.get(input).matches(variableString)) return getNumeric(varsCache.get(input));

        return new Numeric(Integer.parseInt(varsCache.get(input)));
    }
}