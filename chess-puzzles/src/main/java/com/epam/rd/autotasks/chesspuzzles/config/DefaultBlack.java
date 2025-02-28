package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.ChessPieceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultBlack {
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
}