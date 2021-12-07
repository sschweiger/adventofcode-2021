package org.sschweiger.adventofcode.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day7 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 7;
    }

    @Override
    protected long part1(List<String> lines) {
        FuelCalculator calculator = (from, to) -> Math.abs(from - to);
        return calculateCosts(lines, calculator);
    }

    @Override
    protected long part2(List<String> lines) {
        FuelCalculator calculator = (from, to) -> IntStream.rangeClosed(1, Math.abs(from - to)).reduce(0, Integer::sum);
        return calculateCosts(lines, calculator);
    }


    @FunctionalInterface
    interface FuelCalculator {
        int calculate(int from, int to);
    }

    private Integer calculateCosts(List<String> lines, FuelCalculator fuelCalculator) {
        var data = Arrays.stream(lines.get(0).split(","))
            .map(Integer::parseInt)
            .toList();

        var min = data.stream().min(Integer::compareTo).orElse(0);
        var max = data.stream().max(Integer::compareTo).orElse(0);
        var numbers = IntStream.rangeClosed(min, max).boxed().toList();

        var costs = new HashMap<Integer, Integer>();
        for (var number : numbers) {
            var c = 0;
            for (var input : data) {
                c += fuelCalculator.calculate(number, input);
            }

            final int c2 = c;
            costs.compute(number, (k, v) -> v == null ? c2 : v + c2);
        }

        return costs.values().stream().min(Integer::compareTo).orElse(0);
    }
}
