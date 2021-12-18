package org.sschweiger.adventofcode.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day14 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 14;
    }

    @Override
    protected long part1(List<String> lines) {
        return calculatePolymer(lines, 10);
    }

    @Override
    protected long part2(List<String> lines) {
        return calculatePolymer(lines, 40);
    }

    private long calculatePolymer(List<String> lines, int steps) {
        var template = lines.get(0);
        lines.remove(1);
        lines.remove(0);

        var rules = new HashMap<String, String[]>();
        for (var line : lines) {
            var pair = line.split(" -> ");

            String[] replace = {pair[0].charAt(0) + pair[1], pair[1] + pair[0].charAt(1)};
            rules.put(pair[0], replace);
        }

        Map<String, Long> counts = new HashMap<>();
        for (int i = 0; i < template.length() - 1; i++) {
            var token = template.substring(i, i + 2);
            counts.compute(token, (k, v) -> v == null ? 1L : v + 1);
        }

        for (int i = 0; i < steps; i++) {
            counts = count(rules, counts);
        }

        var characters = new HashMap<Character, Long>();
        characters.put(template.charAt(0), 1L);

        for (var key : counts.keySet()) {
            final var value = counts.get(key);
            characters.compute(key.charAt(1), (k, v) -> v == null ? value : v + value);
        }

        LOGGER.info("{}", characters);

        var min = characters.values().stream().min(Long::compareTo).orElse(0L);
        var max = characters.values().stream().max(Long::compareTo).orElse(0L);

        return max - min;
    }

    private Map<String, Long> count(Map<String, String[]> rules, Map<String, Long> input) {
        var map = new HashMap<String, Long>();
        for (var entry : input.entrySet()) {
            if (rules.containsKey(entry.getKey())) {
                map.compute(rules.get(entry.getKey())[0],
                    (k, v) -> v == null ? entry.getValue() : v + entry.getValue());
                map.compute(rules.get(entry.getKey())[1],
                    (k, v) -> v == null ? entry.getValue() : v + entry.getValue());
            } else {
                map.put(entry.getKey(), entry.getValue());
            }
        }

        return map;
    }
}