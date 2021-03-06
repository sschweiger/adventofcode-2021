package org.sschweiger.adventofcode.day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day12 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 12;
    }

    @Override
    protected long part1(List<String> lines) {
        var graph = buildGraph(lines);
        return countPaths(graph, "start", "end", false);
    }

    @Override
    protected long part2(List<String> lines) {
        var graph = buildGraph(lines);
        return countPaths(graph, "start", "end", true);
    }

    private Map<String, List<String>> buildGraph(List<String> lines) {
        var graph = new HashMap<String, List<String>>();

        for (var line : lines) {
            var connection = line.split("-");
            var from = connection[0];
            var to = connection[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
        return graph;
    }

    private long countPaths(Map<String, List<String>> graph, String start, String end,
        boolean allowVisitOneSmallCaveTwice) {
        var count = 0;

        var paths = followPath(graph, new ArrayList<>(), start, allowVisitOneSmallCaveTwice);
        for (var newPath : paths) {
            if (newPath.get(newPath.size() - 1).equals(end)) {
//                LOGGER.info("path from {} to {}: {}", start, end, newPath);
                count++;
            }
        }

        return count;
    }

    private List<List<String>> followPath(Map<String, List<String>> graph, List<String> path, String current,
        boolean allowVisitOneSmallCaveTwice) {

        path.add(current);

        if (current.equals("end")) {
            return Collections.singletonList(new ArrayList<>(path));
        }

        var neighbors = graph.getOrDefault(current, Collections.emptyList())
            .stream()
            .filter(neighbor -> isNextStepAllowed(path, neighbor, allowVisitOneSmallCaveTwice))
            .toList();

        var paths = new ArrayList<List<String>>();
        for (var neighbor : neighbors) {
            paths.addAll(followPath(graph, new ArrayList<>(path), neighbor, allowVisitOneSmallCaveTwice));
        }

        if (paths.isEmpty()) {
            return Collections.singletonList(new ArrayList<>(path));
        }

        return paths;
    }

    private boolean isNextStepAllowed(List<String> path, String next, boolean allowVisitOneSmallCaveTwice) {
        if ("start".equals(next)) {
            return false;
        }

        if ("end".equals(next) || !isSmallCave(next) || !path.contains(next)) {
            return true;
        }

        if (allowVisitOneSmallCaveTwice) {
            return path.stream()
                .filter(this::isSmallCave)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1)
                .findFirst()
                .isEmpty();
        }

        return false;
    }

    private boolean isSmallCave(String cave) {
        return Character.isLowerCase(cave.charAt(0));
    }
}