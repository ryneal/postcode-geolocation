package com.github.ryneal.postcodegeolocation.batch.task;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;

import javax.batch.api.listener.StepListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CsvReader implements ItemReader<Map<String, String>>, StepListener {

    private static final String SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private final boolean hasHeader;
    private File file;
    private LineIterator lineIterator;
    private String[] headers = new String[0];

    public CsvReader(File file, boolean hasHeader) {
        this.file = file;
        this.hasHeader = hasHeader;
    }

    protected CsvReader(LineIterator lineIterator, boolean hasHeader) {
        this.lineIterator = lineIterator;
        this.hasHeader = hasHeader;
    }

    @Override
    @BeforeStep
    public void beforeStep() throws Exception {
        this.lineIterator = FileUtils.lineIterator(this.file);
        if (hasHeader && this.lineIterator.hasNext()) {
            this.headers = this.lineIterator.nextLine().split(SPLIT_REGEX);
        }
    }

    @Override
    public Map<String, String> read() {
        if (this.lineIterator.hasNext()) {
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
