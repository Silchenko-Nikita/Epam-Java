package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {
    char[][] grid;
    char lastMark = '\0';

    public TicTacToeImpl() {
        grid = new char[][]{{' ', ' ', ' '},
                            {' ', ' ', ' '},
                            {' ', ' ', ' '}};
    }

    @Override
    synchronized public void setMark(int x, int y, char mark) {
        if (grid[x][y] != ' ') throw new IllegalArgumentException();

        grid[x][y] = mark;
        lastMark = mark;
        notifyAll();
    }

    @Override
    synchronized public char[][] table() {
        char[][] gridCopy = new char[grid.length][];

        for (int i = 0; i < grid.length; i++) {
            gridCopy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }

        return gridCopy;
    }

    @Override
    synchronized public char lastMark() {
        return lastMark;
    }
}
