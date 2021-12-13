package org.sschweiger.adventofcode.day13;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day13Test {
    private final Day13 puzzle = new Day13();

    @Test
    void shouldRunPart1WithSampleData() {
        var result = puzzle.testPart1();

        assertEquals(17, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var result = puzzle.runPart1();

        assertEquals(687, result);
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var result = puzzle.testPart2();

        assertEquals(33, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var result = puzzle.runPart2();

        assertEquals(3610, result);
    }
}