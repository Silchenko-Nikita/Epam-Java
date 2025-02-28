package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(2, 5, -3, "-3.0 0.5"),
                arguments(1, -3, 1, "0.3819660112501051 2.618033988749895"),
                arguments(2, -38, 156, "6.0 13.0"),
                arguments(-0.5, 34, 1046.5, "-23.0 91.0")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testCase(double a, double b, double c, String expected) {
        assertEquals(expected, quadraticEquation.solve(a, b, c));
    }
}
