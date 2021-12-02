package org.sschweiger.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {

	public static void main(String[] args) {
		var app = new Day1();
		app.run();
	}

	public void run() {
		try {
			var path = Paths.get(getClass().getClassLoader().getResource("day1.data").toURI());
			var lines = Files.readAllLines(path);

			int totalMeasurements = 0;
			int numberOfIncreasedMeasurements = 0;
			int numberOfDecreasedMeasurements = 0;

			int previous = -1;
			for (String line : lines) {
				int current = Integer.parseInt(line);
				if (previous != -1) {
					if (current > previous) {
						numberOfIncreasedMeasurements++;
					} else if (current < previous) {
						numberOfDecreasedMeasurements++;
					}
				}

				previous = current;
				totalMeasurements++;
			}

			System.out.println("total number of measurements: " + totalMeasurements);
			System.out.println("number of measurements larger then the previous one: " + numberOfIncreasedMeasurements);
			System.out.println("number of measurements smaller then the previous one: " + numberOfDecreasedMeasurements);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
