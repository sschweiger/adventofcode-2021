package org.sschweiger.adventofcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public abstract class AdventOfCodeRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(AdventOfCodeRunner.class);

    public void run() {
        LOGGER.info("run day {} with test data", getDay());
        var input = readAllLines("src/main/resources/day" + getDay() + ".test");
        part1(input);
        part2(input);

        LOGGER.info("run day {} with real data", getDay());
        input = readAllLines("src/main/resources/day" + getDay() + ".data");
        part1(input);
        part2(input);
    }

    protected abstract int getDay();

    protected abstract void part1(List<String> lines);
    protected abstract void part2(List<String> lines);

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
