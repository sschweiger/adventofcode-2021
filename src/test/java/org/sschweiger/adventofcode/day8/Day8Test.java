package org.sschweiger.adventofcode.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

	@Test
	void shouldRunPart1WithSampleData() {
		var puzzle = new Day8();
		var result = puzzle.testPart1();

		assertEquals(26, result);
	}

	@Test
	void shouldRunPart1WithRealData() {
		var puzzle = new Day8();
		puzzle.runPart1();
	}

	@Test
	void shouldRunPart2WithSampleData() {
		var puzzle = new Day8();
		var result = puzzle.testPart2();

		assertEquals(61229, result);
	}

	@Test
	void shouldRunPart2WithRealData() {
		var puzzle = new Day8();
		puzzle.runPart2();
	}
}