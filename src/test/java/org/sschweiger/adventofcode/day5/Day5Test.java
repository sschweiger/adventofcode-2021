package org.sschweiger.adventofcode.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    @Test
    void shouldRunPart1WithSampleData() {
        var day5 = new Day5();
        var result = day5.testPart1();

        assertEquals(5, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day5 = new Day5();
        day5.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day5 = new Day5();
        var result = day5.testPart2();

        assertEquals(12, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day5 = new Day5();
        day5.runPart2();
    }

}