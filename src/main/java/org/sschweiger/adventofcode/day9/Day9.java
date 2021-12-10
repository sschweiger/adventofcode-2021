package org.sschweiger.adventofcode.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day9 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 9;
    }

    @Override
    protected long part1(List<String> lines) {
        int[][] data = parse(lines);

        var rows = data.length;
        var result = 0;
        for (var row = 0; row < data.length; row++) {
            var columns = data[row].length;
            for (var col = 0; col < columns; col++) {
                var value = data[row][col];
                if (row - 1 >= 0 && value >= data[row - 1][col]) {
                    continue;
                }

                if (row + 1 < rows && value >= data[row + 1][col]) {
                    continue;
                }

                if (col - 1 >= 0 && value >= data[row][col - 1]) {
                    continue;
                }

                if (col + 1 < columns && value >= data[row][col + 1]) {
                    continue;
                }

                LOGGER.info("found low point at x={},y={}! value={}", row, col, value);
                result += value + 1;
            }
        }

        return result;
    }

    @Override
    protected long part2(List<String> lines) {
        int[][] data = parse(lines);

        var basins = new ArrayList<Integer>();
        for (var row = 0; row < data.length; row++) {
            var columns = data[row].length;
            for (var col = 0; col < columns; col++) {
                var basin = searchNeighbors(data, row, col);
                if (!basin.isEmpty()) {
                    basins.add(basin.size());
                    LOGGER.info("current: {}/{}, basin: {}", row, col, basin);
                }
            }
        }

        var result = new ArrayList<Integer>();
        for (int i = 0; i < 3 && result.size() < 3; i++) {
            var max = basins.stream().max(Integer::compareTo).orElse(0);
            result.addAll(basins.stream().filter(size -> size == max).limit(3).toList());
            basins.removeIf(size -> size == max);
        }

        return result.stream().reduce(1, (a, b) -> a * b);
    }

    private List<Integer> searchNeighbors(int[][] data, int row, int col) {
        if (!isLocationToCheck(data, row, col)) {
            return Collections.emptyList();
        }

        var basin = new ArrayList<Integer>();
        basin.add(data[row][col]);
        data[row][col] = -1;

        basin.addAll(search(data, row, col - 1, 0, -1));
        basin.addAll(search(data, row, col + 1, 0, 1));
        basin.addAll(search(data, row - 1, col, -1, 0));
        basin.addAll(search(data, row + 1, col, 1, 0));

        return basin;
    }

    private List<Integer> search(int[][] data, int row, int col, int rowIncrement, int colIncrement) {
        var basin = new ArrayList<Integer>();
        while (isLocationToCheck(data, row, col)) {
            basin.addAll(searchNeighbors(data, row, col));

            row += rowIncrement;
            col += colIncrement;
        }

        return basin;
    }

    private boolean isLocationToCheck(int[][] data, int row, int col) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[row].length) {
            return false;
        }

        var value = data[row][col];
        if (value == 9 || value == -1) {
            return false;
        }

        return true;
    }

    private int[][] parse(List<String> lines) {
        var rows = lines.size();
        var columns = lines.get(0).length();

        int[][] data = new int[rows][columns];
        for (var i = 0; i < rows; i++) {
            data[i] = Arrays.stream(lines.get(i).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        return data;
    }
}
