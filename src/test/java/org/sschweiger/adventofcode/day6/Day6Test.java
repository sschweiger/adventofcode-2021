package org.sschweiger.adventofcode.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
	@Test
	void shouldRunWithSampleDataFor18Days() {
		var day6 = new Day6();
		var result = day6.runSample(18);

		assertEquals(26, result);
	}

    @Test
    void shouldRunPart1WithSampleData() {
        var day6 = new Day6();
        var result = day6.testPart1();

        assertEquals(5934, result);
    }

    @Test
    void shouldRunPart1WithRealData() {
        var day6 = new Day6();
        day6.runPart1();
    }

    @Test
    void shouldRunPart2WithSampleData() {
        var day6 = new Day6();
        var result = day6.testPart2();

        assertEquals(26984457539L, result);
    }

    @Test
    void shouldRunPart2WithRealData() {
        var day6 = new Day6();
        day6.runPart2();
    }
}