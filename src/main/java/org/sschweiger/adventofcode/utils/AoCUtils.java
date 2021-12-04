package org.sschweiger.adventofcode.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class AoCUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AoCUtils.class);

    public static List<String> readAllLines(String file) {
        LOGGER.info("read input data from {}", file);
        try {
            return Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("error reading file {}", file, e);
        }

        return Collections.emptyList();
    }
}
