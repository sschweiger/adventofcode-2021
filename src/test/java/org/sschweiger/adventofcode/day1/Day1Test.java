package org.sschweiger.adventofcode.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    @Test
    void shouldRunPart1WithSampleData() {
        var day1 = new Day1();
        var result = day1.testPart1();

        assertEquals(7, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day1 = new Day1();
        day1.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day1 = new Day1();
        var result = day1.testPart2();

        assertEquals(5, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day1 = new Day1();
        day1.runPart2();
    }
}