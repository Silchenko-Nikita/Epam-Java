package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.ChessPieceImpl;
import org.springframework.context.annotation.Bean;

public class Puzzle03 {
    // Row 8
    @Bean
    public ChessPiece whiteRookH8() {
        return new ChessPieceImpl('r', Cell.cell('H', 8));
    }

    // Row 7
    @Bean
    public ChessPiece blackPawnB7() {
        return new ChessPieceImpl('P', Cell.cell('B', 7));
    }

    @Bean
    public ChessPiece blackQueenD7() {
        return new ChessPieceImpl('Q', Cell.cell('D', 7));
    }

    @Bean
    public ChessPiece blackPawnE7() {
        return new ChessPieceImpl('P', Cell.cell('E', 7));
    }

    @Bean
    public ChessPiece whiteRookF7() {
        return new ChessPieceImpl('r', Cell.cell('F', 7));
    }

    // Row 6
    @Bean
    public ChessPiece blackPawnA6() {
        return new ChessPieceImpl('P', Cell.cell('A', 6));
    }

    @Bean
    public ChessPiece blackPawnD6() {
        return new ChessPieceImpl('P', Cell.cell('D', 6));
    }

    @Bean
    public ChessPiece blackPawnG6() {
        return new ChessPieceImpl('P', Cell.cell('G', 6));
    }

    // Row 5
    @Bean
    public ChessPiece whitePawnD5() {
        return new ChessPieceImpl('p', Cell.cell('D', 5));
    }

    @Bean
    public ChessPiece blackKingG5() {
        return new ChessPieceImpl('K', Cell.cell('G', 5));
    }

    // Row 4
    @Bean
    public ChessPiece blackBishopB4() {
        return new ChessPieceImpl('B', Cell.cell('B', 4));
    }

    // Row 3
    @Bean
    public ChessPiece blackRookC3() {
        return new ChessPieceImpl('R', Cell.cell('C', 3));
    }

    // Row 2
    @Bean
    public ChessPiece whitePawnG2() {
        return new ChessPieceImpl('p', Cell.cell('G', 2));
    }

    @Bean
    public ChessPiece whitePawnH2() {
        return new ChessPieceImpl('p', Cell.cell('H', 2));
    }

    // Row 1
    @Bean
    public ChessPiece blackRookC1() {
        return new ChessPieceImpl('R', Cell.cell('C', 1));
    }

    @Bean
    public ChessPiece whiteBishopD1() {
        return new ChessPieceImpl('b', Cell.cell('D', 1));
    }

    @Bean
    public ChessPiece whiteKingH1() {
        return new ChessPieceImpl('k', Cell.cell('H', 1));
    }
}
