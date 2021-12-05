package org.sschweiger.adventofcode.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {
    @Test
    void shouldRunPart1WithSampleData() {
        var day2 = new Day2();
        var result = day2.testPart1();

        assertEquals(150, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day2 = new Day2();
        day2.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day2 = new Day2();
        var result = day2.testPart2();

        assertEquals(900, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day2 = new Day2();
        day2.runPart2();
    }
}