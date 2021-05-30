package com.rest.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.springframework.util.ResourceUtils;

public class Utils {

    public static final String csvFilePath() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:riddles.csv");
        return file.getPath();
    }

    /**
     * Get the total number of riddles in the CSV file. Taken from:
     * https://stackoverflow.com/a/5342096/11701504.
     * 
     * @return The total count of riddles in the CSV.
     * @throws IOException
     */
    public static final int totalRiddlesCount() throws IOException {
        int result = 0;
        try (FileReader input = new FileReader(csvFilePath()); LineNumberReader count = new LineNumberReader(input);) {
            while (count.skip(Long.MAX_VALUE) > 0) {
            }

            result = count.getLineNumber() + 1;
        }
        return result;
    }
}
