package org.sschweiger.adventofcode.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {
    @Test
    void shouldRunPart1WithSampleData() {
        var day3 = new Day3();
        var result = day3.testPart1();

        assertEquals(198, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day3 = new Day3();
        day3.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day3 = new Day3();
        var result = day3.testPart2();

        assertEquals(230, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day3 = new Day3();
        day3.runPart2();
    }
}