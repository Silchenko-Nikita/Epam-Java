package org.author.demo;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void runMain() {
        App.main(null);
    }

    @Test
    public void testConstructor() {
        new App();
    }
}