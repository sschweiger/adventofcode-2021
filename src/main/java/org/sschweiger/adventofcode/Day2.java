package org.sschweiger.adventofcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sschweiger.adventofcode.utils.AoCUtils;

import java.util.List;

// https://adventofcode.com/2021/day/2
public class Day2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day2.class);

    public static void main(String[] args) {
        var app = new Day2();
        app.run("src/main/resources/day2.test");
        app.run("src/main/resources/day2.data");
    }

    public void run(String input) {
        List<String> lines = AoCUtils.readAllLines(input);
        part1(lines);
        part2(lines);
    }

    private void part1(List<String> lines) {
        int horizontalPosition = 0;
        int verticalPosition = 0;

        for (var line : lines) {
            var parts = line.split(" ");

            var command = parts[0];
            var value = Integer.parseInt(parts[1]);

            switch (command) {
                case "forward" -> horizontalPosition += value;
                case "down" -> verticalPosition += value;
                case "up" -> verticalPosition -= value;
            }
        }

        LOGGER.info("part 1: horizontal={}, vertical={}, result={}", horizontalPosition, verticalPosition, horizontalPosition * verticalPosition);
    }

    private void part2(List<String> lines) {
        int horizontalPosition = 0;
        int verticalPosition = 0;
        int aim = 0;

        for (var line : lines) {
            var parts = line.split(" ");

            var command = parts[0];
            var value = Integer.parseInt(parts[1]);

            switch (command) {
                case "forward" -> {
                    horizontalPosition += value;
                    verticalPosition += aim * value;
                }
                case "down" -> aim += value;
                case "up" -> aim -= value;
            }
        }

        LOGGER.info("part 2: horizontal={}, vertical={}, result={}", horizontalPosition, verticalPosition, horizontalPosition * verticalPosition);
    }
}
