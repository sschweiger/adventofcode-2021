package org.sschweiger.adventofcode;

import org.sschweiger.adventofcode.day4.BingoParser;

import java.util.List;

public class Day4 extends AdventOfCodeRunner {
    public static void main(String[] args) {
        var app = new Day4();
        app.run();
    }

    @Override
    protected int getDay() {
        return 4;
    }

    @Override
    protected void part1(List<String> lines) {
        var parser = new BingoParser(lines);
        parser.parse(5);
        var boards = parser.getBoards();
        var numbers = parser.getDrawNumbers();

        for (var number : numbers) {
            for (var board : boards) {
                if (board.drawNumber(number)) {
                    var sum = board.getSumOfRemainingNumbers();
                    LOGGER.info("Bingo! result = {} * {} = {}", sum, number, sum * number);
                    return;
                }
            }
        }
    }

    @Override
    protected void part2(List<String> lines) {
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
                        LOGGER.info("Bingo on last board! result = {} * {} = {}", sum, number, sum * number);
                        return;
                    }

                    i--;
                }
            }
        }
    }
}
