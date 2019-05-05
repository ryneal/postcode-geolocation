package com.github.ryneal.postcodegeolocation.batch.task;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;

import javax.batch.api.listener.StepListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvReader implements ItemReader<Map<String, String>>, StepListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvReader.class);
    private static final String SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private final boolean hasHeader;
    private String fileLocation;
    private LineIterator lineIterator;
    private String[] headers = new String[0];
    private static final AtomicInteger COUNT = new AtomicInteger();

    public CsvReader(String fileLocation, boolean hasHeader) {
        this.fileLocation = fileLocation;
        this.hasHeader = hasHeader;
    }

    @Override
    @BeforeStep
    public void beforeStep() throws Exception {
        COUNT.set(0);
        try {
            this.lineIterator = FileUtils.lineIterator(new File(this.fileLocation));
            if (hasHeader && this.lineIterator.hasNext()) {
                this.headers = this.lineIterator.nextLine().split(SPLIT_REGEX);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File " + this.fileLocation + " not found. Csv will not be read", e);
        }
    }

    @Override
    public Map<String, String> read() {
        if (this.lineIterator != null && this.lineIterator.hasNext()) {
            int count = COUNT.incrementAndGet();
            if (count % 1000 == 0) LOGGER.info("CSV Lines Read: " + count);
            Map<String, String> records = new HashMap<>();
            String[] entries = this.lineIterator.nextLine().split(SPLIT_REGEX);
            if (hasHeader && this.headers.length != entries.length) return records;
            for (int i = 0; i < entries.length; i++) {
                records.put(hasHeader ? this.headers[i] : String.valueOf(i), entries[i]);
            }
            return records;
        }
        return null;
    }

    @Override
    @AfterStep
    public void afterStep() throws Exception {
        this.lineIterator.close();
    }
}
