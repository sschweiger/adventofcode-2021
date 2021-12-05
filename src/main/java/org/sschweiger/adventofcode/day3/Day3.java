package org.sschweiger.adventofcode.day3;

import org.sschweiger.adventofcode.AdventOfCodePuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Day3 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 3;
    }

    @Override
    protected long part1(List<String> lines) {
        return calculatePowerConsumption(lines);
    }

    @Override
    protected long part2(List<String> lines) {
        return calculateLifeSupportRating(lines);
    }

    private long calculatePowerConsumption(List<String> lines) {
        var counters = getBitCounters(lines);

        var gammaBytes = new StringBuilder();
        var epsilonBytes = new StringBuilder();
        for (var counter : counters) {
            gammaBytes.append(counter.getMostCommonBit());
            epsilonBytes.append(counter.getLeastCommonBit());
        }

        var gamma = Integer.parseInt(gammaBytes.toString(), 2);
        var epsilon = Integer.parseInt(epsilonBytes.toString(), 2);
        var result = gamma * epsilon;

        LOGGER.info("part 1: gamma={}, epsilon={}, result={}", gamma, epsilon, result);
        return result;
    }

    private long calculateLifeSupportRating(List<String> lines) {
        var oxygenGeneratorRating = Integer.parseInt(filterOnBit(lines, BitCounter::getMostCommonBit), 2);
        var co2ScrubberRating = Integer.parseInt(filterOnBit(lines, BitCounter::getLeastCommonBit), 2);
        var result = oxygenGeneratorRating * co2ScrubberRating;

        LOGGER.info("part 2: oxygen={}, co2scrubber={}, result={}", oxygenGeneratorRating, co2ScrubberRating, result);
        return result;
    }

    private String filterOnBit(List<String> lines, Function<BitCounter, Character> retrieveBit) {
        var tempList = new ArrayList<>(lines);
        for (var i = 0; i < lines.get(0).length(); i++) {
            var counters = getBitCounters(tempList);
            var counter = counters.get(i);

            final var bit = retrieveBit.apply(counter);
            final int pos = i;
            tempList.removeIf(line -> line.charAt(pos) != bit);

            if (tempList.size() == 1) {
                return tempList.get(0);
            }
        }

        return "";
    }

    private List<BitCounter> getBitCounters(List<String> lines) {
        var digits = lines.get(0).length();
        var counters = new ArrayList<BitCounter>();
        for (var i = 0; i < digits; i++) {
            var counter = new BitCounter();
            counter.count(lines, i);
            counters.add(counter);
        }

        return counters;
    }

    private class BitCounter {
        int cntZero = 0;
        int cntOne = 0;

        void count(List<String> lines, int position) {
            for (String line : lines) {
                char c = line.charAt(position);
                switch (c) {
                    case '0' -> cntZero++;
                    case '1' -> cntOne++;
                }
            }
        }

        char getMostCommonBit() {
            return cntZero > cntOne ? '0' : '1';
        }

        char getLeastCommonBit() {
            return cntOne < cntZero ? '1' : '0';
        }
    }
}
