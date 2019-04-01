package com.github.ryneal.postcodegeolocation.util;

import com.github.ryneal.postcodegeolocation.batch.task.CsvReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.net.URL;
import java.util.Map;

import static com.github.ryneal.postcodegeolocation.util.PostcodeConstants.POSTCODE_DISTRICT_REGEX;
import static com.github.ryneal.postcodegeolocation.util.PostcodeConstants.POSTCODE_REGEX;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostcodeConstantsTest {

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private CsvReader postcodeCsvReader;
    private CsvReader postcodeDistrictReader;

    private URL postcodeResource = this.loader.getResource("postcodes_sample.csv");
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

    @Test(timeout = 10000L)
    public void postcodesInFileShouldAllMatchWithPostcodeRegex() throws Exception {
        Map<String, String> map;
        while ((map = this.postcodeCsvReader.read()) != null) {
            String postcode = map.get("Postcode").replace(" ", "");
            assertTrue("Postcode: " + postcode, postcode.matches(POSTCODE_REGEX));
        }
    }

    @Test(timeout = 10000L)
    public void postcodesDistrictsInFileShouldAllMatchWithPostcodeRegex() throws Exception {
        Map<String, String> map;
        while ((map = this.postcodeDistrictReader.read()) != null) {
            String postcode = map.get("Postcode").replace(" ", "");
            assertTrue("District: " + postcode, postcode.matches(POSTCODE_DISTRICT_REGEX));
        }
    }
}
