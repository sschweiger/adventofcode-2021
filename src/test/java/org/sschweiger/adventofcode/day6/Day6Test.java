package org.sschweiger.adventofcode.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
    @Test
    void shouldRunPart1WithSampleData() {
        var day6 = new Day6();
        var result = day6.testPart1();

        assertEquals(5, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day6 = new Day6();
        day6.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day6 = new Day6();
        var result = day6.testPart2();

        assertEquals(12, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day6 = new Day6();
        day6.runPart2();
    }
}