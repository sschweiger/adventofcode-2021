package org.sschweiger.adventofcode.day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day11 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 11;
    }

    @Override
    protected long part1(List<String> lines) {
        int[][] data = parse(lines);

        long result = 0;
        for (int i = 1; i <= 100; i++) {
            var grid = new EnergyGrid(i, data);
            result += grid.checkEnergyLevel();

            grid.print();
        }

        return result;
    }


    @Override
    protected long part2(List<String> lines) {
        int[][] data = parse(lines);

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            var grid = new EnergyGrid(i, data);
            if (grid.checkEnergyLevel() == 100) {
                return i;
            }
        }

        return -1;
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

    private static class EnergyGrid {
        private final int step;
        private final int[][] data;
        private final int[][] flashed;

        public EnergyGrid(int step, int[][] data) {
            this.step = step;
            this.data = data;
            this.flashed = new int[data.length][data[0].length];
        }

        int checkEnergyLevel() {
            for (int row = 0; row < data.length; row++) {
                for (int col = 0; col < data[row].length; col++) {
                    data[row][col] = data[row][col] + 1;
                }
            }

            for (int row = 0; row < data.length; row++) {
                for (int col = 0; col < data[row].length; col++) {
                    if (data[row][col] > 9 && flashed[row][col] != 1) {
                        flash(row, col);
                    }
                }
            }

            return Arrays.stream(flashed)
                .flatMapToInt(IntStream::of)
                .reduce(0, Integer::sum);
        }

        private void flash(int row, int col) {
            data[row][col] = 0;
            flashed[row][col] = 1;

            increaseNeighbors(row, col);
        }

        private void increaseNeighbors(int row, int col) {
            increaseCell(row - 1, col);
            increaseCell(row - 1, col + 1);
            increaseCell(row - 1, col - 1);

            increaseCell(row, col - 1);
            increaseCell(row, col + 1);

            increaseCell(row + 1, col - 1);
            increaseCell(row + 1, col);
            increaseCell(row + 1, col + 1);
        }

        private void increaseCell(int row, int col) {
            if (row < 0 || row >= data.length
                || col < 0 || col >= data[row].length
                || flashed[row][col] == 1) {
                return;
            }

            data[row][col] = data[row][col] + 1;
            if (data[row][col] > 9) {
                flash(row, col);
            }
        }

        void print() {
            StringBuilder builder = new StringBuilder();
            for (int[] row : data) {
                for (int col : row) {
                    builder.append(col);
                }

                builder.append(System.lineSeparator());
            }

            LOGGER.info("step {}{}{}", step, System.lineSeparator(), builder);
        }
    }
}