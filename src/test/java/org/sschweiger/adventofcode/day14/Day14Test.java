package org.sschweiger.adventofcode.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day14Test {
    private final Day14 puzzle = new Day14();

    @Test
    void shouldRunPart1WithSampleData() {
        var result = puzzle.testPart1();

        assertEquals(1588, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var result = puzzle.runPart1();

        assertEquals(3143, result);
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var result = puzzle.testPart2();

        assertEquals(2188189693529L, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var result = puzzle.runPart2();

        assertEquals(4110215602456L, result);
    }
}