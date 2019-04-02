package com.github.ryneal.postcodegeolocation.util;

import com.github.ryneal.postcodegeolocation.batch.task.CsvReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.github.ryneal.postcodegeolocation.util.PostcodeConstants.POSTCODE_DISTRICT_REGEX;
import static com.github.ryneal.postcodegeolocation.util.PostcodeConstants.POSTCODE_REGEX;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PostcodeConstantsTest {

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private CsvReader postcodeCsvReader;
    private CsvReader postcodeDistrictReader;

    private URL postcodeResource = this.loader.getResource("postcodes_only.csv");
    private URL postcodeDistrictResource = this.loader.getResource("postcode_districts.csv");

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.postcodeResource);
        assertNotNull(this.postcodeDistrictResource);
        this.postcodeCsvReader = new CsvReader(new File(this.postcodeResource.getPath()), true);
        this.postcodeDistrictReader = new CsvReader(new File(this.postcodeDistrictResource.getPath()), true);
        this.postcodeCsvReader.beforeStep();
        this.postcodeDistrictReader.beforeStep();
    }

    @After
    public void shutDown() throws Exception {
        this.postcodeCsvReader.afterStep();
        this.postcodeDistrictReader.afterStep();
    }

    @Test
    public void offNominalPostcodesShouldPassValidation() {
        List<String> postcodes = Arrays.asList("N1P1AA", "NPT0AD", "NPT0VA", "W1M0AA", "W1N0AA",
                "W1R0AA", "W1V0AA", "W1X0AA", "W1Y0AA");

        for (String postcode : postcodes) {
            String lowerCasePostcode = postcode.toLowerCase();
            assertTrue("Postcode: " + postcode, postcode.matches(POSTCODE_REGEX));
            assertTrue("Postcode: " + lowerCasePostcode, postcode.matches(POSTCODE_REGEX));
        }
    }

    @Test
    public void postcodesInFileShouldAllMatchWithPostcodeRegex() {
        Map<String, String> map;
        while ((map = this.postcodeCsvReader.read()) != null) {
            String postcode = map.get("Postcode").replace(" ", "");
            String lowerCasePostcode = postcode.toLowerCase();
            assertTrue("Postcode: " + postcode, postcode.matches(POSTCODE_REGEX));
            assertTrue("Postcode: " + lowerCasePostcode, postcode.matches(POSTCODE_REGEX));
        }
    }

    @Test(timeout = 10000L)
    public void postcodesDistrictsInFileShouldAllMatchWithPostcodeDistrictRegex() {
        Map<String, String> map;
        while ((map = this.postcodeDistrictReader.read()) != null) {
            String postcode = map.get("Postcode").replace(" ", "");
            assertTrue("District: " + postcode, postcode.matches(POSTCODE_DISTRICT_REGEX));
        }
    }
}
