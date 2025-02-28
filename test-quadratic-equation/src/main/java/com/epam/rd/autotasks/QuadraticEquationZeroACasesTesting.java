package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuadraticEquationZeroACasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(0, 5, -30),
                arguments(0, -3, 10),
                arguments(0, -38, 1560),
                arguments(0, 34, 1046.5)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testCase(double a, double b, double c) {
        assertThrows(IllegalArgumentException.class, () -> quadraticEquation.solve(a, b, c));
    }
}
