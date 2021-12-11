package org.sschweiger.adventofcode.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day11Test {
    private final Day11 puzzle = new Day11();

    @Test
    void shouldRunPart1WithSampleData() {
        var result = puzzle.testPart1();

        assertEquals(1656, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var result = puzzle.runPart1();

        assertEquals(1620, result);
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var result = puzzle.testPart2();

        assertEquals(195, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var result = puzzle.runPart2();

        assertEquals(371, result);
    }
}