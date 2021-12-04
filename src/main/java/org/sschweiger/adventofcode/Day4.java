package org.sschweiger.adventofcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sschweiger.adventofcode.day4.BingoBoard;
import org.sschweiger.adventofcode.day4.BingoParser;

import java.util.ArrayList;

public class Day4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Day4.class);

    public static void main(String[] args) {
        var app = new Day4();
        app.run("src/main/resources/day4.test");
        app.run("src/main/resources/day4.data");
    }

    public void run(String input) {
        LOGGER.info("start playing bingo for input {}", input);
        var parser = new BingoParser(input);
        parser.parse(5);
        var boards = parser.getBoards();
        var numbers = parser.getDrawNumbers();

        var finishedBoards = new ArrayList<BingoBoard>();
        for (var number : numbers) {
            for (int i = 0; i < boards.size(); i++) {
                var board = boards.get(i);
                if (board.drawNumber(number)) {
                    var sum = board.getSumOfRemainingNumbers();
                    LOGGER.info("Bingo! result = {} * {} = {}", sum, number, sum * number);

                    finishedBoards.add(board);
                    boards.remove(i);
                    i--;
                }
            }

            if (boards.isEmpty()) {
                var lastBoard = finishedBoards.get(finishedBoards.size() - 1);
                var sum = lastBoard.getSumOfRemainingNumbers();
                LOGGER.info("Bingo on last board! result = {} * {} = {}", sum, number, sum * number);
                break;
            }
        }
    }
}
