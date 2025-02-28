package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableIterator implements Iterator<String>, Iterable<String> {
    private final String[] columns;
    private final int[] rows;
    private int columnIndex = 0;
    private int rowIndex = 0;

    public TableIterator(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public boolean hasNext() {
        return columnIndex < columns.length && rowIndex < rows.length;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        String currentCell = columns[columnIndex] + rows[rowIndex];

        rowIndex++;
        if (rowIndex >= rows.length) {
            rowIndex = 0;
            columnIndex++;
        }

        return currentCell;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
