package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import com.epam.rd.autotasks.chesspuzzles.ChessPieceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultWhite {
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