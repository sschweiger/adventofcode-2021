package org.sschweiger.adventofcode.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void shouldRunPart1WithSampleData() {
        var day4 = new Day4();
        var result = day4.testPart1();

        assertEquals(4512, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day4 = new Day4();
        day4.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day4 = new Day4();
        var result = day4.testPart2();

        assertEquals(1924, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day4 = new Day4();
        day4.runPart2();
    }

}