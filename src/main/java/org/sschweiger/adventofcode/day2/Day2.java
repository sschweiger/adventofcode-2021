package org.sschweiger.adventofcode.day2;

import org.sschweiger.adventofcode.AdventOfCodePuzzle;

import java.util.List;

// https://adventofcode.com/2021/day/2
public class Day2 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 2;
    }

    @Override
    protected long part1(List<String> lines) {
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

        var result = horizontalPosition * verticalPosition;
        LOGGER.info("part 1: horizontal={}, vertical={}, result={}", horizontalPosition, verticalPosition, result);
        return result;
    }

    @Override
    protected long part2(List<String> lines) {
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

        var result = horizontalPosition * verticalPosition;
        LOGGER.info("part 2: horizontal={}, vertical={}, result={}", horizontalPosition, verticalPosition, result);
        return result;
    }
}
