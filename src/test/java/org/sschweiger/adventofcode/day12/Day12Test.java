package org.sschweiger.adventofcode.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day12Test {

    private final Day12 puzzle = new Day12();

    @Test
    void shouldRunPartWithSampleData() {
        var input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
            """;

        var result = puzzle.part1(input.lines().toList());

        assertEquals(10, result);
    }
}