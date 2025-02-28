package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.ChessPieceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Default {
    @Bean
    public ChessPiece blackRookA8() {
        return new ChessPieceImpl('R', Cell.cell('A', 8));
    }

    @Bean
    public ChessPiece blackKnightB8() {
        return new ChessPieceImpl('N', Cell.cell('B', 8));
    }

    @Bean
    public ChessPiece blackBishopC8() {
        return new ChessPieceImpl('B', Cell.cell('C', 8));
    }

    @Bean
    public ChessPiece blackQueenD8() {
        return new ChessPieceImpl('Q', Cell.cell('D', 8));
    }

    @Bean
    public ChessPiece blackKingE8() {
        return new ChessPieceImpl('K', Cell.cell('E', 8));
    }

    @Bean
    public ChessPiece blackBishopF8() {
        return new ChessPieceImpl('B', Cell.cell('F', 8));
    }

    @Bean
    public ChessPiece blackKnightG8() {
        return new ChessPieceImpl('N', Cell.cell('G', 8));
    }

    @Bean
    public ChessPiece blackRookH8() {
        return new ChessPieceImpl('R', Cell.cell('H', 8));
    }

    @Bean
    public ChessPiece blackPawnA7() {
        return new ChessPieceImpl('P', Cell.cell('A', 7));
    }

    @Bean
    public ChessPiece blackPawnB7() {
        return new ChessPieceImpl('P', Cell.cell('B', 7));
    }

    @Bean
    public ChessPiece blackPawnC7() {
        return new ChessPieceImpl('P', Cell.cell('C', 7));
    }

    @Bean
    public ChessPiece blackPawnD7() {
        return new ChessPieceImpl('P', Cell.cell('D', 7));
    }

    @Bean
    public ChessPiece blackPawnE7() {
        return new ChessPieceImpl('P', Cell.cell('E', 7));
    }

    @Bean
    public ChessPiece blackPawnF7() {
        return new ChessPieceImpl('P', Cell.cell('F', 7));
    }

    @Bean
    public ChessPiece blackPawnG7() {
        return new ChessPieceImpl('P', Cell.cell('G', 7));
    }

    @Bean
    public ChessPiece blackPawnH7() {
        return new ChessPieceImpl('P', Cell.cell('H', 7));
    }

    @Bean
    public ChessPiece whitePawnA2() {
        return new ChessPieceImpl('p', Cell.cell('A', 2));
    }

    @Bean
    public ChessPiece whitePawnB2() {
        return new ChessPieceImpl('p', Cell.cell('B', 2));
    }

    @Bean
    public ChessPiece whitePawnC2() {
        return new ChessPieceImpl('p', Cell.cell('C', 2));
    }

    @Bean
    public ChessPiece whitePawnD2() {
        return new ChessPieceImpl('p', Cell.cell('D', 2));
    }

    @Bean
    public ChessPiece whitePawnE2() {
        return new ChessPieceImpl('p', Cell.cell('E', 2));
    }

    @Bean
    public ChessPiece whitePawnF2() {
        return new ChessPieceImpl('p', Cell.cell('F', 2));
    }

    @Bean
    public ChessPiece whitePawnG2() {
        return new ChessPieceImpl('p', Cell.cell('G', 2));
    }

    @Bean
    public ChessPiece whitePawnH2() {
        return new ChessPieceImpl('p', Cell.cell('H', 2));
    }

    @Bean
    public ChessPiece whiteRookA1() {
        return new ChessPieceImpl('r', Cell.cell('A', 1));
    }

    @Bean
    public ChessPiece whiteKnightB1() {
        return new ChessPieceImpl('n', Cell.cell('B', 1));
    }

    @Bean
    public ChessPiece whiteBishopC1() {
        return new ChessPieceImpl('b', Cell.cell('C', 1));
    }

    @Bean
    public ChessPiece whiteQueenD1() {
        return new ChessPieceImpl('q', Cell.cell('D', 1));
    }

    @Bean
    public ChessPiece whiteKingE1() {
        return new ChessPieceImpl('k', Cell.cell('E', 1));
    }

    @Bean
    public ChessPiece whiteBishopF1() {
        return new ChessPieceImpl('b', Cell.cell('F', 1));
    }

    @Bean
    public ChessPiece whiteKnightG1() {
        return new ChessPieceImpl('n', Cell.cell('G', 1));
    }

    @Bean
    public ChessPiece whiteRookH1() {
        return new ChessPieceImpl('r', Cell.cell('H', 1));
    }
}