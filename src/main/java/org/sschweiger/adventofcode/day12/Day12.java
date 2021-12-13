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
            if (!"start".equals(from) && !"end".equals(to)) {
                graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
            }
        }
        return graph;
    }

    private long countPaths(Map<String, List<String>> graph, String start, String end,
        boolean allowVisitOneSmallCaveTwice) {
        var count = 0;

        var paths = followPath(graph, new ArrayList<>(), start, allowVisitOneSmallCaveTwice);
        for (var newPath : paths) {
            if (newPath.get(newPath.size() - 1).equals(end)) {
                LOGGER.info("path from {} to {}: {}", start, end, newPath);
                count++;
            }
        }

        return count;
    }

    private List<List<String>> followPath(Map<String, List<String>> graph, List<String> path, String current,
        boolean allowVisitOneSmallCaveTwice) {

        var nexts = graph.getOrDefault(current, Collections.emptyList());
        if (!current.equals("end") && (nexts.isEmpty() || nexts.size() == 1 && isSmallCave(nexts.get(0)))) {
            var newPath = new ArrayList<String>();
            newPath.addAll(path);
            return Collections.singletonList(newPath);
        }

        path.add(current);

        if (current.equals("end")) {
            var newPath = new ArrayList<String>();
            newPath.addAll(path);
            return Collections.singletonList(newPath);
        }

        var neighbors = graph.getOrDefault(current, Collections.emptyList())
            .stream()
            .filter(neighbor -> !alreadyVisited(path, neighbor))
            .filter(neighbor -> isNextStepAllowed(path, neighbor, allowVisitOneSmallCaveTwice))
            .toList();

        var paths = new ArrayList<List<String>>();
        for (var neighbor : neighbors) {
            paths.addAll(followPath(graph, new ArrayList<>(path), neighbor, allowVisitOneSmallCaveTwice));
        }

        if (paths.isEmpty()) {
            var newPath = new ArrayList<String>();
            newPath.addAll(path);
            paths.add(newPath);
        }

        return paths;
    }

    private boolean isNextStepAllowed(List<String> path, String next, boolean allowVisitOneSmallCaveTwice) {
        if ("end".equals(next)) {
            return true;
        }

        if (!isSmallCave(next)) {
            return true;
        }

        if (!path.contains(next)) {
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
//            .values().stream()
//            .filter(count -> count > 1)
//            .toList();

//        return !path.contains(next) || counts.values().stream().filter(count -> count > 1).findFirst().isEmpty();
    }

    private boolean alreadyVisited(List<String> path, String neighbor) {
        if (path.size() < 2) {
            return false;
        }

        var current = path.get(path.size() - 1);
        for (int i = path.size() - 1; i >= 1; i--) {
            var to = path.get(i);
            var from = path.get(i - 1);

            if (from.equals(current) && to.equals(neighbor)) {
                return true;
            }
        }

        return false;
    }

    private boolean isSmallCave(String cave) {
        return Character.isLowerCase(cave.charAt(0));
    }
}