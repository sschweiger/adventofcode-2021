package org.sschweiger.adventofcode.day7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day7Test {

	@Test
	void shouldRunPart1WithSampleData() {
		var puzzle = new Day7();
		var result = puzzle.testPart1();

		assertEquals(37, result);
	}

	@Test
	void shouldRunPart1WithRealData() {
		var puzzle = new Day7();
		puzzle.runPart1();
	}

	@Test
	void shouldRunPart2WithSampleData() {
		var puzzle = new Day7();
		var result = puzzle.testPart2();

		assertEquals(168, result);
	}

	@Test
	void shouldRunPart2WithRealData() {
		var puzzle = new Day7();
		puzzle.runPart2();
	}
}