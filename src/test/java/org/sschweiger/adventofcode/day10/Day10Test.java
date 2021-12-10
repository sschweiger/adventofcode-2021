package org.sschweiger.adventofcode.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day10Test {
    @Test
    void shouldRunPart1WithSampleData() {
        var puzzle = new Day10();
        var result = puzzle.testPart1();

        assertEquals(26397, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var puzzle = new Day10();
        var result = puzzle.runPart1();

        assertEquals(318081, result);
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var puzzle = new Day10();
        var result = puzzle.testPart2();

        assertEquals(288957, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var puzzle = new Day10();
        var result = puzzle.runPart2();

        assertEquals(4361305341L, result);
    }
}