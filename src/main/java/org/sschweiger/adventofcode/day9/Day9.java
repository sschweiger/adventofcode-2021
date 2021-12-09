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
                var basin = searchAdjacents(data, row, col);
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

    private List<Integer> searchAdjacents(int[][] data, int row, int col) {
        var basin = new ArrayList<Integer>();

        var value = data[row][col];
        if (value == 9 || data[row][col] == -1) {
            return basin;
        }

        basin.add(value);
        data[row][col] = -1;

//        basin.addAll(searchNeighbors(data, row, col));
        basin.addAll(searchLeft(data, row, col - 1));
        basin.addAll(searchRight(data, row, col + 1));
        basin.addAll(searchUp(data, row - 1, col));
        basin.addAll(searchDown(data, row + 1, col));

        if (basin.size() == 1) {
            return Collections.emptyList();
        }

        return basin;
    }

    private List<Integer> searchNeighbors(int[][] data, int row, int col) {
        var basin = new ArrayList<Integer>();
        basin.addAll(searchLeft(data, row, col - 1));
        basin.addAll(searchRight(data, row, col + 1));
        basin.addAll(searchUp(data, row - 1, col));
        basin.addAll(searchDown(data, row + 1, col));

        return basin;
    }

    private List<Integer> searchLeft(int[][] data, int row, int col) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[row].length) {
            return Collections.emptyList();
        }

        var value = data[row][col];
        if (value == 9 || value == -1) {
            return Collections.emptyList();
        }

        var basin = new ArrayList<Integer>();
        for (var i = col; i >= 0; i--) { // search left
            if (data[row][i] == 9 || data[row][i] == -1) {
                break;
            }

            basin.add(value);
            data[row][col] = -1;

            basin.addAll(searchNeighbors(data, row, col));
        }

        return basin;
    }

    private List<Integer> searchRight(int[][] data, int row, int col) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[row].length) {
            return Collections.emptyList();
        }

        var value = data[row][col];
        if (value == 9 || value == -1) {
            return Collections.emptyList();
        }

        var basin = new ArrayList<Integer>();
        for (var i = col; i < data[row].length; i++) { // search right
            if (data[row][i] == 9 || data[row][i] == -1) {
                break;
            }

            basin.add(value);
            data[row][col] = -1;

            basin.addAll(searchNeighbors(data, row, col));
        }

        return basin;
    }

    private List<Integer> searchUp(int[][] data, int row, int col) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[row].length) {
            return Collections.emptyList();
        }

        var value = data[row][col];
        if (value == 9 || value == -1) {
            return Collections.emptyList();
        }

        var basin = new ArrayList<Integer>();
        for (var i = row; i >= 0; i--) { // search up
            if (data[i][col] == 9 || data[i][col] == -1) {
                break;
            }

            basin.add(value);
            data[row][col] = -1;

            basin.addAll(searchNeighbors(data, row, col));
        }

        return basin;
    }

    private List<Integer> searchDown(int[][] data, int row, int col) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[row].length) {
            return Collections.emptyList();
        }

        var value = data[row][col];
        if (value == 9 || value == -1) {
            return Collections.emptyList();
        }

        var basin = new ArrayList<Integer>();
        for (var i = row; i < data.length; i++) { // search down
            if (data[i][col] == 9 || data[i][col] == -1) {
                break;
            }

            basin.add(value);
            data[row][col] = -1;

            basin.addAll(searchNeighbors(data, row, col));
        }

        return basin;
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
