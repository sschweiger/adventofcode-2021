package org.sschweiger.adventofcode.day10;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day10 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 10;
    }

    @Override
    protected long part1(List<String> lines) {
        List<String> corruptedLines = new ArrayList<>();
        for (String line : lines) {
            var current = line;
            LOGGER.info("line: {}", line);

            current = stripLine(current);

            if (StringUtils.containsOnly(current, "([{<")) {
                LOGGER.info("line {} is incomplete! current: {}", line, current);
            } else {
                corruptedLines.add(current);
            }
        }

        var result = 0;
        for (var line : corruptedLines) {
            var wrongChar = line.charAt(StringUtils.indexOfAny(line, ")", "]", "}", ">"));
            switch (wrongChar) {
                case ')' -> result += 3;
                case ']' -> result += 57;
                case '}' -> result += 1197;
                case '>' -> result += 25137;
            }
        }

        return result;
    }

    @Override
    protected long part2(List<String> lines) {
        List<String> incompleteLines = new ArrayList<>();
        for (String line : lines) {
            var current = line;
            current = stripLine(current);

            if (StringUtils.containsOnly(current, "([{<")) {
                incompleteLines.add(current);
            }
        }

        var results = new ArrayList<Long>();
        for (var line : incompleteLines) {
            var closingChars = StringUtils.reverse(line);
            closingChars = StringUtils.replaceChars(closingChars, "(", ")");
            closingChars = StringUtils.replaceChars(closingChars, "[", "]");
            closingChars = StringUtils.replaceChars(closingChars, "{", "}");
            closingChars = StringUtils.replaceChars(closingChars, "<", ">");

            long points = 0;

            for (char c : closingChars.toCharArray()) {
                var toAdd = switch (c) {
                    case ')' -> 1;
                    case ']' -> 2;
                    case '}' -> 3;
                    case '>' -> 4;
                    default -> 0;
                };

                points = points * 5 + toAdd;
            }

            LOGGER.info("starting chars: {}, closing chars: {}, points: {}", line, closingChars, points);
            results.add(points);
        }

        results.sort(Long::compareTo);
        return results.get(results.size()/2);
    }

    private String stripLine(String current) {
        List<String> patterns = List.of("()", "[]", "{}", "<>");

        boolean found;
        do {
            found = false;
            for (var pattern : patterns) {
                if (StringUtils.contains(current, pattern)) {
                    current = StringUtils.remove(current, pattern);
                    found = true;
                }
            }
        } while (found);

        return current;
    }

}
