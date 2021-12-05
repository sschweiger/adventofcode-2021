package org.sschweiger.adventofcode.day4;

import org.sschweiger.adventofcode.AdventOfCodePuzzle;

import java.util.List;

public class Day4 extends AdventOfCodePuzzle {
    @Override
    protected int getDay() {
        return 4;
    }

    @Override
    protected long part1(List<String> lines) {
        var parser = new BingoParser(lines);
        parser.parse(5);
        var boards = parser.getBoards();
        var numbers = parser.getDrawNumbers();

        for (var number : numbers) {
            for (var board : boards) {
                if (board.drawNumber(number)) {
                    var sum = board.getSumOfRemainingNumbers();
                    var result = sum * number;
                    LOGGER.info("Bingo! result = {} * {} = {}", sum, number, result);
                    return result;
                }
            }
        }

        return -1;
    }

    @Override
    protected long part2(List<String> lines) {
        var parser = new BingoParser(lines);
        parser.parse(5);
        var boards = parser.getBoards();
        var numbers = parser.getDrawNumbers();

        for (var number : numbers) {
            for (int i = 0; i < boards.size(); i++) {
                var board = boards.get(i);
                if (board.drawNumber(number)) {
                    boards.remove(i);
                    if (boards.isEmpty()) {
                        var sum = board.getSumOfRemainingNumbers();
                        var result = sum * number;
                        LOGGER.info("Bingo on last board! result = {} * {} = {}", sum, number, result);
                        return result;
                    }

                    i--;
                }
            }
        }

        return -1;
    }
}
