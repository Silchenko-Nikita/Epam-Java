package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FactorialMethodSourceParametrizedTesting {

    Factorial factorial = new Factorial();

    static Stream<org.junit.jupiter.params.provider.Arguments> testCases() {
        return Stream.of(
                arguments("1", "1"),
                arguments("2", "2"),
                arguments("5", "120")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testFactorial(String in, String expected) {
        assertEquals(expected, factorial.factorial(in));
    }
}
