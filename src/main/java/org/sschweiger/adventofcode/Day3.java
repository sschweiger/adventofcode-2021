package org.sschweiger.adventofcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day3 {

	private static final Logger LOGGER = Logger.getLogger(Day3.class.getName());

	public static void main(String[] args) {
		var app = new Day3();
		app.run("src/main/resources/day3.test");
		app.run("src/main/resources/day3.data");
	}

	public void run(String input) {
		LOGGER.log(Level.INFO, "input: {0}", input);
		var lines = readFromFile(input);

		calculatePowerConsumption(lines);
		calculateLifeSupportRating(lines);
	}

	private void calculatePowerConsumption(List<String> lines) {
		var counters = getBitCounters(lines);

		var gamma = new StringBuilder();
		var epsilon = new StringBuilder();
		for (var counter : counters) {
			gamma.append(counter.getMostCommonBit());
			epsilon.append(counter.getLeastCommonBit());
		}

		var gammaDecimal = Integer.parseInt(gamma.toString(), 2);
		var epsilonDecimal = Integer.parseInt(epsilon.toString(), 2);

		LOGGER.info("gamma=" + gamma + ", " + gammaDecimal);
		LOGGER.info("epsilon=" + epsilon + ", " + epsilonDecimal);
		LOGGER.info("result=" + gammaDecimal * epsilonDecimal);
	}

	private void calculateLifeSupportRating(List<String> lines) {
		var oxygenGeneratorRating = filterOnBit(lines, BitCounter::getMostCommonBit);
		var oxygenGeneratorRatingDecimal = Integer.parseInt(oxygenGeneratorRating, 2);

		var co2ScrubberRating = filterOnBit(lines, BitCounter::getLeastCommonBit);
		var co2ScrubberRatingDecimal = Integer.parseInt(co2ScrubberRating, 2);

		LOGGER.info("oxygenGeneratorRating: " + oxygenGeneratorRating + ", " + oxygenGeneratorRatingDecimal);
		LOGGER.info("co2ScrubberRating: " + co2ScrubberRating + ", " + co2ScrubberRatingDecimal);
		LOGGER.info("life support rating = " + oxygenGeneratorRatingDecimal * co2ScrubberRatingDecimal);
	}

	private String filterOnBit(List<String> lines, Function<BitCounter, Character> retrieveBit) {
		var tempList = new ArrayList<>(lines);
		for (var i = 0; i < lines.get(0).length(); i++) {
			var counters = getBitCounters(tempList);
			var counter = counters.get(i);

			final var bit = retrieveBit.apply(counter);
			final int pos = i;
			tempList.removeIf(line -> line.charAt(pos) != bit);

			if (tempList.size() == 1) {
				return tempList.get(0);
			}
		}

		return "";
	}

	private List<BitCounter> getBitCounters(List<String> lines) {
		var digits = lines.get(0).length();
		var counters = new ArrayList<BitCounter>();
		for (var i = 0; i < digits; i++) {
			var counter = new BitCounter();
			counter.count(lines, i);
			counters.add(counter);
		}

		return counters;
	}

	private class BitCounter {

		int cntZero = 0;
		int cntOne = 0;

		void count(List<String> lines, int position) {
			for (String line : lines) {
				char c = line.charAt(position);
				switch (c) {
					case '0' -> cntZero++;
					case '1' -> cntOne++;
				}
			}
		}

		char getMostCommonBit() {
			return cntZero > cntOne ? '0' : '1';
		}

		char getLeastCommonBit() {
			return cntOne < cntZero ? '1' : '0';
		}
	}

	private List<String> readFromFile(String input) {
		try {
			return Files.readAllLines(Paths.get(input), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
}
