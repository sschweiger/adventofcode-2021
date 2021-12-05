package org.sschweiger.adventofcode.day5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.LongStream;

public class Line {
    private final Point from;
    private final Point to;

    public Line(Point from, Point to) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
    }

    public boolean isVertical() {
        return from.x == to.x;
    }

    public boolean isHorizontal() {
        return from.y == to.y;
    }

    public boolean isDiagonal() {
        return from.x != to.x && from.y != to.y;
    }

    public Collection<Point> getAllPointsOnLine() {
        var distanceX = LongStream.rangeClosed(Long.min(from.x, to.x), Long.max(from.x, to.x)).count();
        var distanceY = LongStream.rangeClosed(Long.min(from.y, to.y), Long.max(from.y, to.y)).count();

        var distance = isVertical() ? distanceY : distanceX;
        var xModifier = isVertical() ? 0 : from.x > to.x ? -1 : 1;
        var yModifier = isHorizontal() ? 0 : from.y > to.y ? -1 : 1;

        return generatePoints(distance, xModifier, yModifier);
    }

    private Collection<Point> generatePoints(long distance, int xModifier, int yModifier) {
        var points = new ArrayList<Point>();
        var current = from;
        for (int i = 0; i < distance; i++) {
            points.add(current);
            current = new Point(current.x + xModifier, current.y + yModifier);
        }

        return points;
    }

    public static record Point(long x, long y) {
    }
}
