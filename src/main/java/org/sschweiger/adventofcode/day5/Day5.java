package org.sschweiger.adventofcode.day5;

import org.sschweiger.adventofcode.AdventOfCodePuzzle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day5 extends AdventOfCodePuzzle {
    @Override
    protected int getDay() {
        return 5;
    }

    @Override
    protected long part1(List<String> lines) {
        return countOverlaps(lines, true);
    }

    @Override
    protected long part2(List<String> lines) {
        return countOverlaps(lines, false);
    }

    private long countOverlaps(List<String> lines, boolean skipDiagonals) {
        var map = new HashMap<Line.Point, Long>();
        for (var input : lines) {
            var coordinates = Arrays.stream(input.split("->"))
                    .map(String::trim)
                    .map(c -> c.split(","))
                    .toList();

            var from = toPoint(coordinates.get(0));
            var to = toPoint(coordinates.get(1));

            var line = new Line(from, to);
            if (skipDiagonals && line.isDiagonal()) {
                continue;
            }

            for (var point : line.getAllPointsOnLine()) {
                map.compute(point, (k, v) -> v == null ? 1L : ++v);
            }
        }

        return map.values().stream().filter(v -> v > 1).count();
    }

    private Line.Point toPoint(String[] coordinate) {
        var x = Long.parseLong(coordinate[0]);
        var y = Long.parseLong(coordinate[1]);

        return new Line.Point(x, y);
    }
}
