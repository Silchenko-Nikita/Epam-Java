package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayTwoTimesIterator implements Iterator<Integer> {
    private final int[] array;
    private int index = 0;
    private boolean repeat = false;

    public IntArrayTwoTimesIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int currentElement = array[index];

        if (repeat) {
            index++;
        }
        repeat = !repeat;

        return currentElement;
    }
}
