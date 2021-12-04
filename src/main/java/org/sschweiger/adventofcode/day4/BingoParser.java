package org.sschweiger.adventofcode.day4;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BingoParser {
    private final List<String> lines;
    private final List<BingoBoard> boards = new ArrayList<>();
    private final List<Integer> drawNumbers = new ArrayList<>();

    public BingoParser(List<String> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public void parse(int boardSize) {
        for (var number : lines.get(0).split(",")) {
            drawNumbers.add(Integer.parseInt(number));
        }

        List<String> rows = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (StringUtils.isBlank(line)) {
                if (!rows.isEmpty()) {
                    boards.add(new BingoBoard(rows, boardSize));
                }

                rows = new ArrayList<>();
            } else {
                rows.add(line);
            }
        }

        boards.add(new BingoBoard(rows, boardSize));
    }

    public List<BingoBoard> getBoards() {
        return boards;
    }

    public List<Integer> getDrawNumbers() {
        return drawNumbers;
    }
}
