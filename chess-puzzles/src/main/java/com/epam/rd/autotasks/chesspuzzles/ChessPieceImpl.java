package com.epam.rd.autotasks.chesspuzzles;

public class ChessPieceImpl implements ChessPiece {
    private final char type;
    private final Cell cell;

    public ChessPieceImpl(char type, Cell cell) {
        this.type = type;
        this.cell = cell;
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public char toChar() {
        return type;
    }
}