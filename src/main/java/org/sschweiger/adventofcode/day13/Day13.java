package org.sschweiger.adventofcode.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day13 extends AdventOfCodePuzzle {

    record Point(int x, int y) {}

    record Instruction(String direction, int position) {}

    @Override
    protected int getDay() {
        return 13;
    }

    @Override
    protected long part1(List<String> lines) {
        return analyze(lines, 1);
    }

    @Override
    protected long part2(List<String> lines) {
        return analyze(lines, -1);
    }

    private long analyze(List<String> lines, int numberOfInstructions) {
        var grid = parseGrid(lines);
        var instructions = parseInstructions(lines);
        if (numberOfInstructions > 0) {
            instructions = instructions.subList(0, numberOfInstructions);
        }

        var result = 0;
        for (var instr : instructions) {
            if ("x".equals(instr.direction)) {
                grid = foldX(grid, instr.position);
            } else {
                grid = foldY(grid, instr.position);
            }

            int count = countDots(grid);
            LOGGER.info("{} dots after {}", count, instr);
            result += count;
        }

        return result;
    }

    private int countDots(String[][] grid) {
        var count = 0;
        for (var i = 0; i < grid.length; i++) {
            var builder = new StringBuilder();
            for (var j = 0; j < grid[i].length; j++) {
                builder.append(grid[i][j]);

                if ("#".equals(grid[i][j])) {
                    count++;
                }
            }

            LOGGER.info("{}: {}", i, builder);
        }
        return count;
    }

    private String[][] parseGrid(List<String> lines) {
        var coordinates = new ArrayList<Point>();

        for (var line : lines) {
            if (StringUtils.isBlank(line) || !Character.isDigit(line.charAt(0))) {
                continue;
            }

            int[] point = Arrays.stream(line.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

            coordinates.add(new Point(point[0], point[1]));
        }

        var maxX = coordinates.stream().map(p -> p.x).max(Integer::compareTo).orElse(0);
        var maxY = coordinates.stream().map(p -> p.y).max(Integer::compareTo).orElse(0);

        var grid = new String[maxY + 1][maxX + 1];
        for (String[] strings : grid) {
            Arrays.fill(strings, ".");
        }

        for (var point : coordinates) {
            grid[point.y][point.x] = "#";
        }

        return grid;
    }

    private List<Instruction> parseInstructions(List<String> lines) {
        var instructions = new ArrayList<Instruction>();
        for (var line : lines) {
            if (line.startsWith("fold along")) {
                var parts = StringUtils.remove(line, "fold along ").split("=");
                instructions.add(new Instruction(parts[0], Integer.parseInt(parts[1])));
            }
        }

        return instructions;
    }

    private String[][] foldY(String[][] grid, int foldPosition) {
        var folded = new String[foldPosition][grid[0].length];
        for (var i = 0; i < grid.length; i++) {
            if (i == foldPosition) {
                continue;
            }

            if (i < foldPosition) {
                folded[i] = grid[i];
            } else {
                for (var j = 0; j < grid[i].length; j++) {
                    var row = foldPosition - (i - foldPosition);
                    folded[row][j] = grid[row][j];
                    if ("#".equals(grid[i][j])) {
                        folded[row][j] = "#";
                    }
                }
            }
        }

        return folded;
    }

    private String[][] foldX(String[][] grid, int foldPosition) {
        var folded = new String[grid.length][foldPosition];

        for (var i = 0; i < grid.length; i++) {
            for (var j = 0; j < grid[i].length; j++) {
                if (j == foldPosition) {
                    continue;
                }

                if (j < foldPosition) {
                    folded[i][j] = grid[i][j];
                } else {
                    var column = foldPosition - (j - foldPosition);
                    folded[i][column] = grid[i][column];
                    if ("#".equals(grid[i][j])) {
                        folded[i][column] = "#";
                    }
                }
            }
        }

        return folded;
    }
}