package org.sschweiger.adventofcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class AdventOfCodePuzzle {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AdventOfCodePuzzle.class);

    private long run(String file, Function<List<String>, Long> function) {
        LOGGER.info("run day {} part 1 with data from {}", getDay(), file);
        var input = readAllLines(file);
        var result = function.apply(input);
        LOGGER.info("result={}", result);
        return result;
    }

    private String getFileName(boolean useTestData) {
        var file = "src/main/resources/day" + getDay();
        return file + (useTestData ? ".test" : ".data");
    }

    public long testPart1() {
        return run(getFileName(true), this::part1);
    }

    public long runPart1() {
        return run(getFileName(false), this::part1);
    }

    public long testPart2() {
        return run(getFileName(true), this::part2);
    }

    public long runPart2() {
        return run(getFileName(false), this::part2);
    }

    protected abstract int getDay();

    protected abstract long part1(List<String> lines);

    protected abstract long part2(List<String> lines);

    private List<String> readAllLines(String file) {
        LOGGER.info("read input data from {}", file);
        try {
            return Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("error reading file {}", file, e);
        }

        return Collections.emptyList();
    }
}
