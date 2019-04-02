package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class PostcodeDistrictProcessorTest {

    @Test
    public void shouldProcessAllValuesIntoPostcodeDistrict() throws Exception {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();

        PostcodeDistrict district = processor.process(districtMap);

        assert district != null;
        assertThat(district.getPostcode(), is("S70"));
        assertThat(district.getLatitude(), is(0.0));
        assertThat(district.getLongitude(), is(1.0));
        assertThat(district.getEasting(), is(123L));
        assertThat(district.getNorthing(), is(321L));
        assertThat(district.getGridReference(), is("GRID"));
        assertThat(district.getTownArea(), is("Happyville"));
        assertThat(district.getRegion(), is("Supershire"));
        assertThat(district.getPostcodes(), is(234L));
        assertThat(district.getActivePostcodes(), is(345L));
        assertThat(district.getPopulation(), is(342L));
        assertThat(district.getHouseholds(), is(888L));
        assertThat(district.getNearbyDistricts().size(), is(3));
        assertThat(district.getNearbyDistricts().get(0), is("a"));
        assertThat(district.getNearbyDistricts().get(1), is("b"));
        assertThat(district.getNearbyDistricts().get(2), is("c"));
    }

    @Test
    public void shouldProcessProduceEmptyListWithNullValueOfNearestDistricts() throws Exception {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.remove(PostcodeDistrictProcessor.NEARBY_DISTRICTS);

        PostcodeDistrict district = processor.process(districtMap);

        assert district != null;
        assertThat(district.getNearbyDistricts().size(), is(0));
    }

    @Test
    public void shouldProcessProduceEmptyListWithEmptyStringValueOfNearestDistricts() throws Exception {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.put(PostcodeDistrictProcessor.NEARBY_DISTRICTS, "");

        PostcodeDistrict district = processor.process(districtMap);

        assert district != null;
        assertThat(district.getNearbyDistricts().size(), is(0));
    }

    @Test
    public void shouldProcessAllNullDoublesIntoPostcodeDistrict() throws Exception {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.remove(PostcodeDistrictProcessor.LATITUDE);
        districtMap.remove(PostcodeDistrictProcessor.LONGITUDE);

        PostcodeDistrict district = processor.process(districtMap);

        assert district != null;
        assertThat(district.getPostcode(), is("S70"));
        assertThat(district.getLatitude(), is(nullValue()));
        assertThat(district.getLongitude(), is(nullValue()));
        assertThat(district.getEasting(), is(123L));
        assertThat(district.getNorthing(), is(321L));
        assertThat(district.getGridReference(), is("GRID"));
        assertThat(district.getTownArea(), is("Happyville"));
        assertThat(district.getRegion(), is("Supershire"));
        assertThat(district.getPostcodes(), is(234L));
        assertThat(district.getActivePostcodes(), is(345L));
        assertThat(district.getPopulation(), is(342L));
        assertThat(district.getHouseholds(), is(888L));
        assertThat(district.getNearbyDistricts().size(), is(3));
        assertThat(district.getNearbyDistricts().get(0), is("a"));
        assertThat(district.getNearbyDistricts().get(1), is("b"));
        assertThat(district.getNearbyDistricts().get(2), is("c"));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWithInvalidDoublesIntoPostcodeDistrict() {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.put(PostcodeDistrictProcessor.LATITUDE, "blah");
        districtMap.put(PostcodeDistrictProcessor.LONGITUDE, "3.f");

        processor.process(districtMap);
    }

    @Test
    public void shouldProcessAllNullLongsIntoPostcodeDistrict() {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.remove(PostcodeDistrictProcessor.EASTING);
        districtMap.remove(PostcodeDistrictProcessor.NORTHING);
        districtMap.remove(PostcodeDistrictProcessor.POSTCODES);
        districtMap.remove(PostcodeDistrictProcessor.ACTIVE_POSTCODES);
        districtMap.remove(PostcodeDistrictProcessor.POPULATION);
        districtMap.remove(PostcodeDistrictProcessor.HOUSEHOLDS);

        PostcodeDistrict district = processor.process(districtMap);

        assert district != null;
        assertThat(district.getPostcode(), is("S70"));
        assertThat(district.getLatitude(), is(0.0));
        assertThat(district.getLongitude(), is(1.0));
        assertThat(district.getEasting(), is(nullValue()));
        assertThat(district.getNorthing(), is(nullValue()));
        assertThat(district.getGridReference(), is("GRID"));
        assertThat(district.getTownArea(), is("Happyville"));
        assertThat(district.getRegion(), is("Supershire"));
        assertThat(district.getPostcodes(), is(nullValue()));
        assertThat(district.getActivePostcodes(), is(nullValue()));
        assertThat(district.getPopulation(), is(nullValue()));
        assertThat(district.getHouseholds(), is(nullValue()));
        assertThat(district.getNearbyDistricts().size(), is(3));
        assertThat(district.getNearbyDistricts().get(0), is("a"));
        assertThat(district.getNearbyDistricts().get(1), is("b"));
        assertThat(district.getNearbyDistricts().get(2), is("c"));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWithInvalidLongsIntoPostcodeDistrict() {
        PostcodeDistrictProcessor processor = new PostcodeDistrictProcessor();
        Map<String, String> districtMap = generatePostcodeDistrictMap();
        districtMap.put(PostcodeDistrictProcessor.EASTING, "S");
        districtMap.put(PostcodeDistrictProcessor.NORTHING, "3f");
        districtMap.put(PostcodeDistrictProcessor.POSTCODES, "3s");
        districtMap.put(PostcodeDistrictProcessor.ACTIVE_POSTCODES, "GjI");
        districtMap.put(PostcodeDistrictProcessor.POPULATION, "LLO");
        districtMap.put(PostcodeDistrictProcessor.HOUSEHOLDS, "Hello");

        processor.process(districtMap);
    }

    private Map<String, String> generatePostcodeDistrictMap() {
        Map<String, String> map = new HashMap<>();
        map.put(PostcodeDistrictProcessor.POSTCODE, "S70");
        map.put(PostcodeDistrictProcessor.LATITUDE, "0.0");
        map.put(PostcodeDistrictProcessor.LONGITUDE, "1.0");
        map.put(PostcodeDistrictProcessor.EASTING, "123");
        map.put(PostcodeDistrictProcessor.NORTHING, "321");
        map.put(PostcodeDistrictProcessor.GRID_REFERENCE, "GRID");
        map.put(PostcodeDistrictProcessor.TOWN_AREA, "Happyville");
        map.put(PostcodeDistrictProcessor.REGION, "Supershire");
        map.put(PostcodeDistrictProcessor.POSTCODES, "234");
        map.put(PostcodeDistrictProcessor.ACTIVE_POSTCODES, "345");
        map.put(PostcodeDistrictProcessor.POPULATION, "342");
        map.put(PostcodeDistrictProcessor.HOUSEHOLDS, "888");
        map.put(PostcodeDistrictProcessor.NEARBY_DISTRICTS, "a,b,c");
        return map;
    }
}
