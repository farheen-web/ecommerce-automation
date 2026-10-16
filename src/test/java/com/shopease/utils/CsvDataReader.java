
package com.shopease.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {

    public static Object[][] readCsv(String resourcePath) {

        List<Object[]> rows = new ArrayList<>();

        try (InputStream input = CsvDataReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (input == null) {
                throw new RuntimeException(
                        "CSV file not found: " + resourcePath);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {

                String line;
                boolean header = true;

                while ((line = reader.readLine()) != null) {

                    if (header) {
                        header = false;
                        continue;
                    }

                    if (line.isBlank()) {
                        continue;
                    }

                    String[] values = line.split(",", -1);

                    if (values.length != 2) {
                        throw new RuntimeException(
                                "Invalid CSV row: " + line);
                    }

                    String expectedText = values[0].trim();
                    String booleanValue = values[1].trim();

                    if (expectedText.isEmpty()) {
                        throw new RuntimeException(
                            "Expected text cannot be empty in CSV row: " + line);
                    }

                    if (!booleanValue.equalsIgnoreCase("true")
                            && !booleanValue.equalsIgnoreCase("false")) {
                        throw new RuntimeException(
                            "Invalid boolean value in CSV row: " + line
                            + ". Expected true or false.");
                    }

                    boolean shouldContain = Boolean.parseBoolean(booleanValue);

                    rows.add(new Object[] { expectedText, shouldContain });
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to read CSV: " + resourcePath, e);
        }

        if (rows.isEmpty()) {
            throw new RuntimeException(
                    "CSV contains no test data: " + resourcePath);
        }

        return rows.toArray(new Object[0][]);
    }
}
