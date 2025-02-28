package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.ChessPieceImpl;
import org.springframework.context.annotation.Bean;

public class Puzzle02 {
    // Row 8
    @Bean
    public ChessPiece whiteQueenH8() {
        return new ChessPieceImpl('q', Cell.cell('H', 8));
    }

    // Row 7 empty
    // Row 6 empty
    // Row 5 empty

    // Row 4
    @Bean
    public ChessPiece blackQueenF4() {
        return new ChessPieceImpl('Q', Cell.cell('F', 4));
    }

    // Row 3
    @Bean
    public ChessPiece blackKingD3() {
        return new ChessPieceImpl('K', Cell.cell('D', 3));
    }

    // Row 2
    @Bean
    public ChessPiece whiteKingB2() {
        return new ChessPieceImpl('k', Cell.cell('B', 2));
    }

    @Bean
    public ChessPiece blackPawnE2() {
        return new ChessPieceImpl('P', Cell.cell('E', 2));
    }

    // Row 1 empty
}
