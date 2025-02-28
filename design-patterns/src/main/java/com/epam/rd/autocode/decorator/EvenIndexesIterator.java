package com.epam.rd.autocode.decorator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIndexesIterator<T> implements Iterator<T> {
    private final EvenIndexesList<T> list;
    private int index;

    public EvenIndexesIterator(EvenIndexesList<T> list) {
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(index++);
    }
}