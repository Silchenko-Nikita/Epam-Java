package com.epam.rd.autocode.decorator;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

public class EvenIndexesList<T> extends AbstractList<T> {
    private final List<T> list;

    public EvenIndexesList(List<T> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return (list.size() + 1) / 2;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(index * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new EvenIndexesIterator<>(this);
    }
}

