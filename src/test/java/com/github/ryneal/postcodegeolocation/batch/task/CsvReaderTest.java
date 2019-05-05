package com.github.ryneal.postcodegeolocation.batch.task;

import org.junit.Test;

import java.net.URL;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CsvReaderTest {

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private URL testOneResource = this.loader.getResource("test1.csv");
    private URL testTwoResource = this.loader.getResource("test2.csv");
    private URL testThreeResource = this.loader.getResource("test3.csv");

    @Test
    public void shouldReadCsvSampleWithHeader() throws Exception {
        CsvReader csvReader = new CsvReader(this.testOneResource.getPath(), true);
        csvReader.beforeStep();

        Map<String, String> firstLine = csvReader.read();
        Map<String, String> secondLine = csvReader.read();

        assert firstLine != null;
        assertThat(firstLine.get("First"), is("a"));
        assertThat(firstLine.get("Second"), is("b"));
        assertThat(firstLine.get("Third"), is("c"));
        assert secondLine == null;
    }

    @Test
    public void shouldReadCsvSampleWithoutHeader() throws Exception {
        CsvReader csvReader = new CsvReader(this.testOneResource.getPath(), false);
        csvReader.beforeStep();

        Map<String, String> firstLine = csvReader.read();
        Map<String, String> secondLine = csvReader.read();

        assert firstLine != null;
        assertThat(firstLine.get("0"), is("First"));
        assertThat(firstLine.get("1"), is("Second"));
        assertThat(firstLine.get("2"), is("Third"));
        assert secondLine != null;
        assertThat(secondLine.get("0"), is("a"));
        assertThat(secondLine.get("1"), is("b"));
        assertThat(secondLine.get("2"), is("c"));
    }

    @Test
    public void shouldReturnEmptyMapIfCsvIsFormattedWithTooFewRowEntries() throws Exception {
        CsvReader csvReader = new CsvReader(this.testTwoResource.getPath(), true);
        csvReader.beforeStep();

        Map<String, String> firstLine = csvReader.read();

        assert firstLine != null;
        assertThat(firstLine.size(), is(0));
    }

    @Test
    public void shouldReturnEmptyMapIfCsvIsFormattedWithTooManyRowEntries() throws Exception {
        CsvReader csvReader = new CsvReader(this.testThreeResource.getPath(), true);
        csvReader.beforeStep();

        Map<String, String> firstLine = csvReader.read();

        assert firstLine != null;
        assertThat(firstLine.size(), is(0));
    }
}
