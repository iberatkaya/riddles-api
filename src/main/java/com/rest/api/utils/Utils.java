package com.rest.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.springframework.core.io.ClassPathResource;

public class Utils {

    public static final ClassPathResource csvFileResource() throws FileNotFoundException {
        ClassPathResource fileResource = new ClassPathResource("riddles.csv");
        return fileResource;
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
        InputStream is = csvFileResource().getInputStream();
        try (InputStreamReader input = new InputStreamReader(is);
                LineNumberReader count = new LineNumberReader(input);) {
            while (count.skip(Long.MAX_VALUE) > 0) {
            }

            result = count.getLineNumber() + 1;
        }
        is.close();
        return result;
    }
}
