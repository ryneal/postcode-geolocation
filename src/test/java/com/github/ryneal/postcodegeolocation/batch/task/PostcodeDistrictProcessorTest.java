package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PostcodeDistrictProcessorTest {

    @Test
    public void shouldProcessAllValuesIntoPostcodeDistrict() {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();

        PostcodeDistrict district = processor.process(districtMap);

        assertNotNull(district);
        assertThat(district.getPostcode(), is("S70"));
        assertThat(district.getLatitude(), is(0.0));
        assertThat(district.getLongitude(), is(1.0));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldHandleNullLatLonAsNumberErrorInPostcodeDistrict() {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.remove(PostcodeDistrictProcessor.LATITUDE);
        districtMap.remove(PostcodeDistrictProcessor.LONGITUDE);

        processor.process(districtMap);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWithInvalidDoublesIntoPostcodeDistrict() {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.put(PostcodeDistrictProcessor.LATITUDE, "blah");
        districtMap.put(PostcodeDistrictProcessor.LONGITUDE, "3.f");

        processor.process(districtMap);
    }

    private Map<String, String> generatePostcodeDistrictMap() {
        Map<String, String> map = new HashMap<>();
        map.put(PostcodeDistrictProcessor.POSTCODE, "S70");
        map.put(PostcodeDistrictProcessor.LATITUDE, "0.0");
        map.put(PostcodeDistrictProcessor.LONGITUDE, "1.0");
        return map;
    }
}
