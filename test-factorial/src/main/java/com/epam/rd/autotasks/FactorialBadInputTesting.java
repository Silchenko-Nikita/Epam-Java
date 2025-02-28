package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testNullInput(){
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial(null));
    }

    @Test
    void testNegativeInput(){
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-3"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-2"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-10"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-1"));
    }

    @Test
    void testFractionalInput(){
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("43.2"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("2.4"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("1.411"));
    }

    @Test
    void testNonDigitalInput(){
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("blabla"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("ewq"));
    }
}
