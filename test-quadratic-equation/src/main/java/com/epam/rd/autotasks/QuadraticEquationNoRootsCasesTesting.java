package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuadraticEquationNoRootsCasesTesting {

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(-563, 0, -5),
                arguments(2, 10, 30),
                arguments(-0.5, 1, -50),
                arguments(1, 11, 111),
                arguments(2, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testNoRootsCase(double a, double b, double c) {
        assertEquals("no roots", quadraticEquation.solve(a, b, c));
    }
}
