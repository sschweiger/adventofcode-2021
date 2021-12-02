package org.sschweiger.adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1Part2 {

	public static void main(String[] args) {
		var app = new Day1Part2();
		app.run();
	}

	public void run() {
		try {
			var path = Paths.get(getClass().getClassLoader().getResource("day1part2.data").toURI());
			var lines = Files.readAllLines(path);

			int numberOfIncreasedMeasurements = 0;
			int numberOfDecreasedMeasurements = 0;

			int firstWindowSum = 0;
			int secondWindowSum = 0;

			for (var i = 0; i < lines.size(); i++) {
				firstWindowSum = getSumOfLines(lines, i, 3);
				secondWindowSum = getSumOfLines(lines, i+1, 3);

				if (firstWindowSum == -1 || secondWindowSum == -1) {
					continue;
				}

				if (secondWindowSum > firstWindowSum) {
					numberOfIncreasedMeasurements++;
				} else if (secondWindowSum < firstWindowSum) {
					numberOfDecreasedMeasurements++;
				}
			}

			System.out.println("number of measurements larger then the previous one: " + numberOfIncreasedMeasurements);
			System.out.println("number of measurements smaller then the previous one: " + numberOfDecreasedMeasurements);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getSumOfLines(List<String> lines, int startIndex, int numberOfRecords) {
		var lastIndex = startIndex + (numberOfRecords - 1);
		if (startIndex + (numberOfRecords - 1) >= lines.size()) {
			return -1;
		}

		var sum = 0;
		for (var i = startIndex; i <= lastIndex; i++) {
			sum += Integer.parseInt(lines.get(i));
		}

		return sum;
	}
}
