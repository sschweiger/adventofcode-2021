package org.sschweiger.adventofcode.day4;

import java.util.*;

import static java.util.function.Predicate.not;

public class BingoBoard {
    private final List<String> rows;
    private final int boardSize;
    private final int[][] data;

    private final List<BingoRow> bingoRows = new ArrayList<>();

    private boolean bingo = false;
    private BingoRow winnerRow;

    public BingoBoard(List<String> rows, int boardSize) {
        this.rows = rows;
        this.boardSize = boardSize;
        this.data = new int[boardSize][boardSize];

        initBoard();
    }

    private void initBoard() {
        for (var i = 0; i < boardSize; i++) {
            data[i] = Arrays.stream(rows.get(i).split(" "))
                    .map(String::trim)
                    .filter(not(String::isBlank))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (var x = 0; x < boardSize; x++) {
            var row = new ArrayList<Integer>(5);
            var column = new ArrayList<Integer>(5);

            for (var y = 0; y < boardSize; y++) {
                row.add(data[x][y]);
                column.add(data[y][x]);
            }

            bingoRows.add(new BingoRow(row));
            bingoRows.add(new BingoRow(column));
        }
    }

    public boolean drawNumber(int number) {
        for (var row : bingoRows) {
            if (row.hasBingo(number)) {
                bingo(row);
            }
        }

        return bingo;
    }

    private void bingo(BingoRow row) {
        if (this.bingo) {
            return;
        }

        this.bingo = true;
        this.winnerRow = row;
    }

    public int getSumOfRemainingNumbers() {
        var remainingNumbers = new HashSet<Integer>();
        for (var rowLeft : bingoRows) {
            remainingNumbers.addAll(rowLeft.numbersLeft);
        }

        return remainingNumbers.stream().reduce(0, Integer::sum);
    }

    public static class BingoRow {
        private final Collection<Integer> numbers;
        private final Set<Integer> numbersLeft;

        public BingoRow(Collection<Integer> numbers) {
            this.numbers = numbers;
            this.numbersLeft = new HashSet<>(numbers);
        }

        public boolean hasBingo(int number) {
            numbersLeft.remove(number);
            return numbersLeft.isEmpty();
        }

        @Override
        public String toString() {
            return "BingoRow{" +
                    "numbers=" + numbers +
                    '}';
        }
    }
}
