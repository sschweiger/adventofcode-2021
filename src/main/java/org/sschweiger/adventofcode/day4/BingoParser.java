package org.sschweiger.adventofcode.day4;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoParser {
    private final String input;
    private final List<BingoBoard> boards = new ArrayList<>();
    private final List<Integer> drawNumbers = new ArrayList<>();

    public BingoParser(String input) {
        this.input = input;
    }

    public void parse(int boardSize) {
        var lines = readFromFile(input);
        for (var number : lines.get(0).split(",")) {
            drawNumbers.add(Integer.parseInt(number));
        }

        lines.remove(0);

        List<String> rows = new ArrayList<>();
        for (var line : lines) {
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

    private List<String> readFromFile(String input) {
        try {
            return Files.readAllLines(Paths.get(input), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
