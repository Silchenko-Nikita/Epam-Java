package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.ChessPieceImpl;
import org.springframework.context.annotation.Bean;

public class Puzzle01 {
    // Row 8
    @Bean
    public ChessPiece blackRookE8() {
        return new ChessPieceImpl('R', Cell.cell('E', 8));
    }

    // Row 7
    @Bean
    public ChessPiece whiteKingB7() {
        return new ChessPieceImpl('k', Cell.cell('B', 7));
    }

    // Row 6
    @Bean
    public ChessPiece whiteKnightC6() {
        return new ChessPieceImpl('n', Cell.cell('C', 6));
    }

    @Bean
    public ChessPiece blackBishopD6() {
        return new ChessPieceImpl('B', Cell.cell('D', 6));
    }

    @Bean
    public ChessPiece blackPawnF6() {
        return new ChessPieceImpl('P', Cell.cell('F', 6));
    }

    // Row 5
    @Bean
    public ChessPiece blackKingC5() {
        return new ChessPieceImpl('K', Cell.cell('C', 5));
    }

    @Bean
    public ChessPiece blackBishopD5() {
        return new ChessPieceImpl('B', Cell.cell('D', 5));
    }

    // Row 4 empty

    // Row 3
    @Bean
    public ChessPiece blackPawnC3() {
        return new ChessPieceImpl('P', Cell.cell('C', 3));
    }

    // Row 2
    @Bean
    public ChessPiece whitePawnD2() {
        return new ChessPieceImpl('p', Cell.cell('D', 2));
    }

    @Bean
    public ChessPiece whiteBishopE2() {
        return new ChessPieceImpl('b', Cell.cell('E', 2));
    }

    // Row 1 empty
}
