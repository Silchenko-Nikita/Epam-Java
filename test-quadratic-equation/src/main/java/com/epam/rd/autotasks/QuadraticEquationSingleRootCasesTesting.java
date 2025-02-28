package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(1, -2, 1, "1.0"),
                arguments(1, 0, 0, "-0.0"),
                arguments(8, 0, 0, "-0.0"),
                arguments(1, -6, 9, "3.0"),
                arguments(1, 12, 36, "-6.0")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testCase(double a, double b, double c, String expected) {
        assertEquals(expected, quadraticEquation.solve(a, b, c));
    }
}