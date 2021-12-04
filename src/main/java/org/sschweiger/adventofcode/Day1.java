package org.sschweiger.adventofcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sschweiger.adventofcode.utils.AoCUtils;

import java.util.List;

public class Day1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day1.class);

    public static void main(String[] args) {
        var app = new Day1();
        app.run("src/main/resources/day1.test");
        app.run("src/main/resources/day1.data");
    }

    public void run(String input) {
        var lines = AoCUtils.readAllLines(input);
        part1(lines);
        part2(lines);
    }

    private void part1(List<String> lines) {
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
        }

        LOGGER.info("part 1: increased={}, decreased={}", numberOfIncreasedMeasurements, numberOfDecreasedMeasurements);
    }

    private void part2(List<String> lines) {
        int numberOfIncreasedMeasurements = 0;
        int numberOfDecreasedMeasurements = 0;

        for (var i = 0; i < lines.size(); i++) {
            var firstWindowSum = getSumOfLines(lines, i, 3);
            var secondWindowSum = getSumOfLines(lines, i + 1, 3);

            if (firstWindowSum == -1 || secondWindowSum == -1) {
                continue;
            }

            if (secondWindowSum > firstWindowSum) {
                numberOfIncreasedMeasurements++;
            } else if (secondWindowSum < firstWindowSum) {
                numberOfDecreasedMeasurements++;
            }
        }

        LOGGER.info("part 2: increased={}, decreased={}", numberOfIncreasedMeasurements, numberOfDecreasedMeasurements);
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
