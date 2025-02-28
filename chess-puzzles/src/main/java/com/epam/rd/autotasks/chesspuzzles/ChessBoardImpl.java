package com.epam.rd.autotasks.chesspuzzles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardImpl implements ChessBoard {
    private final Map<Cell, Character> board = new HashMap<>();

    public ChessBoardImpl(Collection<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            board.put(piece.getCell(), piece.toChar());
        }
    }

    @Override
    public String state() {
        StringBuilder builder = new StringBuilder();
        for (int row = 8; row >= 1; row--) {
            for (char col = 'A'; col <= 'H'; col++) {
                Cell cell = Cell.cell(col, row);
                builder.append(board.getOrDefault(cell, '.'));
            }
            if (row > 1) {
                builder.append('\n');
            }
        }
        return builder.toString();
    }
}
