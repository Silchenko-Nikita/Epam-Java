package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayThreeTimesIterator implements Iterator<Integer> {
    private final int[] array;
    private int index = 0;
    private int repetition = 0;

    public IntArrayThreeTimesIterator(int[] array) {
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
        repetition++;

        if (repetition == 3) {
            index++;
            repetition = 0;
        }

        return currentElement;
    }
}
