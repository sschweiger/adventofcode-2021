package org.sschweiger.adventofcode.day9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day9Test {

    @Test
    void shouldRunPart1WithSampleData() {
        var puzzle = new Day9();
        var result = puzzle.testPart1();

        assertEquals(15, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var puzzle = new Day9();
        var result = puzzle.runPart1();

        assertEquals(524, result);
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var puzzle = new Day9();
        var result = puzzle.testPart2();

        assertEquals(1134, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var puzzle = new Day9();
        var result = puzzle.runPart2();

        assertEquals(1235430, result);
    }
}