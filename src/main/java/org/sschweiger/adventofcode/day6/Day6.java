package org.sschweiger.adventofcode.day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day6 extends AdventOfCodePuzzle {

	@Override
	protected int getDay() {
		return 6;
	}

	public long runSample(int days) {
		return simulateLanternfishGrowth(readAllLines(getFileName(true)), 18);
	}

	@Override
	protected long part1(List<String> lines) {
		return simulateLanternfishGrowth(lines, 80);
	}

	@Override
	protected long part2(List<String> lines) {
		return simulateLanternfishGrowth(lines, 256);
	}

	private long simulateLanternfishGrowth(List<String> lines, int days) {
		var data = Arrays.stream(lines.get(0).split(","))
				.map(Integer::parseInt)
				.toList();

		var map = new HashMap<Integer, Long>();
		for (var fish : data) {
			map.compute(fish, (k, v) -> v == null ? 1 : ++v);
		}

		for (var i = 0; i < days; i++) {
			long newFishes = map.getOrDefault(0, 0L);
			for (var j = 0; j <= 8; j++) {
				map.put(j, map.getOrDefault(j + 1, 0L));
			}

			map.compute(6, (k, v) -> v == null ? newFishes : v + newFishes);
			map.compute(8, (k, v) -> v == null ? newFishes : v + newFishes);
		}

		return map.values().stream().reduce(0L, Long::sum);
	}
}
