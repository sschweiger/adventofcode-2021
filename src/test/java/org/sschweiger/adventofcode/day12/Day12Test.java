package org.sschweiger.adventofcode.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day12Test {
    private static final String smallSample = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
        """;

    private static final String largerSample = """
        dc-end
        HN-start
        start-kj
        dc-start
        dc-HN
        LN-dc
        HN-end
        kj-sa
        kj-HN
        kj-dc
        """;

    private final Day12 puzzle = new Day12();

    @Test
    void shouldRunPart1WithSmallSampleData() {
        var result = puzzle.part1(smallSample.lines().toList());

        assertEquals(10, result);
    }

    @Test
    void shouldRunPart1WithLargerSampleData() {
        var result = puzzle.part1(largerSample.lines().toList());

        assertEquals(19, result);
    }

    @Test
    void shouldRunPart1WithSampleData() {
        var result = puzzle.testPart1();

        assertEquals(226, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var result = puzzle.runPart1();

        assertEquals(4707, result);
    }

    @Test
    void shouldRunPart2WithSmallSampleData() {
        var result = puzzle.part2(smallSample.lines().toList());

        assertEquals(36, result);
    }
}