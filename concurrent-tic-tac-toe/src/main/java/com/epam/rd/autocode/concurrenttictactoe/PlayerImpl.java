package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl implements Player {
    public static boolean isStarted = true;

    final TicTacToe ticTacToe;
    final char mark;
    PlayerStrategy strategy;

    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    private boolean gameIsFinished(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != ' ' && grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
                return true;
            }
            if (grid[0][i] != ' ' && grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                return true;
            }
        }

        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            return true;
        }
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
            return true;
        }

        return false;
    }

    @Override
    public void run() {
        synchronized (ticTacToe) {
            while (!gameIsFinished(ticTacToe.table())) {
                while (ticTacToe.lastMark() == mark) {
                    try {
                        ticTacToe.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (gameIsFinished(ticTacToe.table())) {
                    ticTacToe.notifyAll();
                    return;
                }

                Move move = strategy.computeMove(mark, ticTacToe);
                if (move == null) return;

                try {
                    ticTacToe.setMark(move.row, move.column, mark);
                } catch (IllegalArgumentException e) {
                    continue;
                }

                ticTacToe.notifyAll();
            }
        }
    }
}
