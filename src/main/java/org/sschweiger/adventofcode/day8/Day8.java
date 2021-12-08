package org.sschweiger.adventofcode.day8;

import org.sschweiger.adventofcode.AdventOfCodePuzzle;

import java.util.*;

public class Day8 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 8;
    }

    @Override
    protected long part1(List<String> lines) {
        var result = 0;
        for (var line : lines) {
            var segments = line.split(" \\| ")[1].split(" ");
            result += Arrays.stream(segments)
                    .map(String::length)
                    .filter(length -> List.of(2, 3, 4, 7).contains(length))
                    .count();

        }

        return result;
    }

    @Override
    protected long part2(List<String> lines) {
        var lengths = Map.of(2, 1, 3, 7, 4, 4, 7, 8);

        var result = 0;
        for (var line : lines) {
            var parts = line.split(" \\| ");
            var signals = Arrays.stream(parts[0].split(" ")).map(this::sort).toList();
            var codes = new HashMap<Integer, String>();
            for (var length : lengths.keySet()) {
                signals.stream()
                        .filter(s -> s.length() == length)
                        .findFirst()
                        .ifPresent(s -> codes.put(lengths.get(length), sort(s)));
            }

            var cf = Set.of(codes.get(1).split(""));
            var a = removeFromSet(codes.get(7), cf);
            var bd = removeFromSet(codes.get(4), cf);
            var g = new HashSet<String>();

            // 9
            for (var signal : signals) {
                if (signal.length() == 6) {
                    var characters = removeFromSet(signal, cf, a, bd);
                    if (characters.size() == 1) {
                        codes.put(9, signal);
                        g.addAll(characters);
                    }
                }
            }

            // 0
            for (var signal : signals) {
                if (signal.length() == 6 && !signal.equals(codes.get(9))) {
                    var characters = removeFromSet(signal, a, cf, g);
                    codes.put(characters.size() == 2 ? 0 : 6, signal);
                }
            }

            // 3
            for (var signal : signals) {
                if (signal.length() == 5) {
                    var characters = removeFromSet(signal, a, cf, g);
                    if (characters.size() == 1) {
                        codes.put(3, signal);
                    }
                }
            }

            // 5, 2
            for (var signal : signals) {
                if (signal.length() == 5 && !signal.equals(codes.get(3))) {
                    var characters = removeFromSet(signal, a, bd, g); // f
                    codes.put(characters.size() == 1 ? 5 : 2, signal);
                }
            }

            var outputSegments = Arrays.stream(parts[1].split(" ")).map(this::sort).toList();
            var number = "";
            for (var segment : outputSegments) {
                for (var code : codes.entrySet()) {
                    if (code.getValue().equals(segment)) {
                        number += code.getKey();
                        break;
                    }
                }
            }

            result += Integer.parseInt(number);
        }

        return result;
    }

    private String sort(String input) {
        var characters = input.toCharArray();
        Arrays.sort(characters);

        return new String(characters);
    }

    private Set<String> removeFromSet(String signal, Set<String>... toRemove) {
        var characters = new HashSet<String>();
        characters.addAll(Arrays.asList(signal.split("")));

        for (var set : toRemove) {
            characters.removeAll(set);
        }

        return characters;
    }
}
