package org.sschweiger.adventofcode.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.sschweiger.adventofcode.AdventOfCodePuzzle;

public class Day12 extends AdventOfCodePuzzle {

    @Override
    protected int getDay() {
        return 12;
    }

    @Override
    protected long part1(List<String> lines) {
        var nodes = new HashMap<String, Node>();
        var paths = new HashMap<String, List<String>>();

        for (var line : lines) {
            var connection = line.split("-");
            var from = connection[0];
            var to = connection[1];
            paths.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            if (!"start".equals(from) && !"end".equals(to)) {
                paths.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
            }

            var node = nodes.computeIfAbsent(from, Node::new);
            var child = nodes.computeIfAbsent(to, Node::new);
            node.addAdjacent(child);
        }

        var nodes2 = new HashMap<String, Node>();
        for (var key : paths.keySet()) {
            var node = nodes2.computeIfAbsent(key, Node::new);
            for (var value : paths.get(key)) {
                var neighbor = nodes2.computeIfAbsent(value, Node::new);
                node.addAdjacent(neighbor);
//                neighbor.addAdjacent(node);
            }
        }

        for (var node : nodes2.values()) {
            LOGGER.info("node2: {}", node);
        }

        LOGGER.info("paths: {}", paths);
//        var result = getPaths(paths, null, "start", "start", new ArrayList<>());
//        LOGGER.info("result: {}", result);

        if (nodes.containsKey("start")) {
            var root = nodes.get("start");
            for (var path : root.findPaths()) {
                LOGGER.info("path: {}", path);
            }
        }

        for (var node : nodes.values()) {
            LOGGER.info("{}", node);
        }

        return 0;
    }

//    private void getPaths(Map<String, List<String>> paths, String previous, String current, String path, List<List<String>> list) {
//
////        var result = new ArrayList<String>();
//        if (previous != null) {
////            result.add(previous + "->" + current);
////            list.add(result);
//
//            path += previous + "->" + current;
//        }
//
//        if ("end".equals(current)) {
//            LOGGER.info("end reached, path={}", path);
////            return list;
//
//            var result = new ArrayList<String>();
//            result.add(previous + "->" + current);
//            list.add(result);
//
//            return;
//        }
//
//        if (previous != null && Character.isUpperCase(previous.charAt(0))) {
//            LOGGER.info("{} -> {}", current, previous);
//
//            getPaths(paths, current, previous, path + "->", list);
//        }
//
//        var children = paths.get(current);
//        for (var child : children) {
//            if (paths.containsKey(child) && paths.get(child).size() == 1 && StringUtils.equals(paths.get(child).get(0),
//                current)) {
//                continue;
//            }
//
//            if (StringUtils.equals(previous, child)) {
//                continue;
//            }
//
//            LOGGER.info("{} -> {}", current, child);
//
//            var result = new ArrayList<String>();
//            result.add(previous + "->" + current);
//            result.addAll(getPaths(paths, current, child, path + "->" + child + "->"));
//            list.add(result);
//        }
//
//        return result;
//    }

    @Override
    protected long part2(List<String> lines) {
        return 0;
    }

    private class Node {
        private final String node;
        private final boolean bigCave;
        private final List<Node> adjacents = new ArrayList<>();
        private final Set<String> visitedPaths = new HashSet<>();

        private Node parent;

        public Node(String node) {
            this.node = node;
            this.bigCave = Character.isUpperCase(node.charAt(0));
        }

        public void addAdjacent(Node node) {
            node.parent = this;
            this.adjacents.add(node);
        }

        public List<String> findPaths() {
            List<String> result = new ArrayList<>();
            if (adjacents.isEmpty()) {
                result.add(node);
            }

            result.addAll(getPaths(adjacents));
            return result;
        }

        public List<String> getPaths(List<Node> nodes) {
            List<String> result = new ArrayList<>();

            for (var child : nodes) {
                if (bigCave && !"end".equals(child.node)) {
                    var temp = new ArrayList<>(nodes);
                    temp.remove(child);

                    for (var path : getPaths(temp)) {
                        result.add(node + " -> " + child.node + " -> " + path);
                    }
                }

                if (!bigCave && !child.bigCave && !"end".equals(child.node)) {
                    continue;
                }

                for (var path : child.findPaths()) {
                    result.add(node + " -> " + path);
                }
            }

            return result;
        }

        @Override
        public String toString() {
//            StringBuilder builder = new StringBuilder();
//            builder.append(node)
            return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("node", node)
                .append("children", adjacents)
//                .append("visitedPaths", visitedPaths)
//                .append("parent", parent)
                .toString();
        }
    }

}