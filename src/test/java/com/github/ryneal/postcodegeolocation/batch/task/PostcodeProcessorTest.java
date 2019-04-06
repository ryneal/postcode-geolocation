package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PostcodeProcessorTest {

    @Test
    public void shouldProcessAllValuesIntoPostcode() {
        PostcodeProcessor processor = new PostcodeProcessor();
        Map<String, String> map = generatePostcodeMap();

        Postcode postcode = processor.process(map);

        assertNotNull(postcode);
        assertThat(postcode.getPostcode(), is("LS69BH"));
        assertThat(postcode.getInUse(), is(true));
        assertThat(postcode.getLatitude(), is(0.0));
        assertThat(postcode.getLongitude(), is(1.0));
        assertThat(postcode.getEasting(), is(123L));
        assertThat(postcode.getNorthing(), is(321L));
        assertThat(postcode.getGridRef(), is("GRID"));
        assertThat(postcode.getCounty(), is("Supershire"));
        assertThat(postcode.getDistrict(), is("LS6"));
        assertThat(postcode.getWard(), is("Super Ward"));
        assertThat(postcode.getDistrictCode(), is("LS6T"));
        assertThat(postcode.getWardCode(), is("Ward code"));
        assertThat(postcode.getCountry(), is("United Kingdom"));
        assertThat(postcode.getCountryCode(), is("UK"));
        assertThat(postcode.getConstituency(), is("Super Land"));
        assertThat(postcode.getIntroduced(), is("26-10-1999"));
        assertThat(postcode.getTerminated(), is("27-10-1999"));
        assertThat(postcode.getParish(), is("Spaghetti Monster"));
        assertThat(postcode.getNationalPark(), is("Kruger"));
        assertThat(postcode.getPopulation(), is(342L));
        assertThat(postcode.getHouseholds(), is(888L));
        assertThat(postcode.getBuiltUpArea(), is("Swole area"));
        assertThat(postcode.getBuiltUpSubDivision(), is("Swole division"));
        assertThat(postcode.getLowerLayerOutputArea(), is("Super Low"));
        assertThat(postcode.getRuralUrban(), is("Rural"));
        assertThat(postcode.getRegion(), is("Super Region"));
        assertThat(postcode.getAltitude(), is(123456.0));
        assertThat(postcode.getLondonZone(), is("Kent"));
        assertThat(postcode.getLsoaCode(), is("LSOA"));
        assertThat(postcode.getLocalAuthority(), is("Rogue One"));
        assertThat(postcode.getMsoaCode(), is("MSOA"));
        assertThat(postcode.getMiddleLayerOutputArea(), is("Super Mid"));
        assertThat(postcode.getParishCode(), is("PCode"));
        assertThat(postcode.getCensusOutputArea(), is("CArea"));
        assertThat(postcode.getConstituencyCode(), is("SW"));
        assertThat(postcode.getMultipleDeprivationIndex(), is(2345L));
        assertThat(postcode.getQuality(), is(1L));
        assertThat(postcode.getUserType(), is(0L));
        assertThat(postcode.getLastUpdated(), is("30-3-2019"));
        assertThat(postcode.getNearestStation(), is("Hogwarts"));
        assertThat(postcode.getDistanceToStation(), is(500.0));
        assertThat(postcode.getPostcodeArea(), is("LSS"));
        assertThat(postcode.getPostcodeDistrict(), is("LS6"));
        assertThat(postcode.getPoliceForce(), is("The Bill"));
    }

    @Test
    public void shouldProcessNullLongValuesIntoPostcode() {
        PostcodeProcessor processor = new PostcodeProcessor();
        Map<String, String> map = generatePostcodeMap();
        map.remove(PostcodeProcessor.EASTING);
        map.remove(PostcodeProcessor.NORTHING);
        map.remove(PostcodeProcessor.POPULATION);
        map.remove(PostcodeProcessor.HOUSEHOLDS);
        map.remove(PostcodeProcessor.MULTI_DEPRIVATION_INDEX);
        map.remove(PostcodeProcessor.QUALITY);
        map.remove(PostcodeProcessor.USER_TYPE);

        Postcode postcode = processor.process(map);

        assertNotNull(postcode);
        assertThat(postcode.getPostcode(), is("LS69BH"));
        assertThat(postcode.getInUse(), is(true));
        assertThat(postcode.getLatitude(), is(0.0));
        assertThat(postcode.getLongitude(), is(1.0));
        assertThat(postcode.getEasting(), is(nullValue()));
        assertThat(postcode.getNorthing(), is(nullValue()));
        assertThat(postcode.getGridRef(), is("GRID"));
        assertThat(postcode.getCounty(), is("Supershire"));
        assertThat(postcode.getDistrict(), is("LS6"));
        assertThat(postcode.getWard(), is("Super Ward"));
        assertThat(postcode.getDistrictCode(), is("LS6T"));
        assertThat(postcode.getWardCode(), is("Ward code"));
        assertThat(postcode.getCountry(), is("United Kingdom"));
        assertThat(postcode.getCountryCode(), is("UK"));
        assertThat(postcode.getConstituency(), is("Super Land"));
        assertThat(postcode.getIntroduced(), is("26-10-1999"));
        assertThat(postcode.getTerminated(), is("27-10-1999"));
        assertThat(postcode.getParish(), is("Spaghetti Monster"));
        assertThat(postcode.getNationalPark(), is("Kruger"));
        assertThat(postcode.getPopulation(), is(nullValue()));
        assertThat(postcode.getHouseholds(), is(nullValue()));
        assertThat(postcode.getBuiltUpArea(), is("Swole area"));
        assertThat(postcode.getBuiltUpSubDivision(), is("Swole division"));
        assertThat(postcode.getLowerLayerOutputArea(), is("Super Low"));
        assertThat(postcode.getRuralUrban(), is("Rural"));
        assertThat(postcode.getRegion(), is("Super Region"));
        assertThat(postcode.getAltitude(), is(123456.0));
        assertThat(postcode.getLondonZone(), is("Kent"));
        assertThat(postcode.getLsoaCode(), is("LSOA"));
        assertThat(postcode.getLocalAuthority(), is("Rogue One"));
        assertThat(postcode.getMsoaCode(), is("MSOA"));
        assertThat(postcode.getMiddleLayerOutputArea(), is("Super Mid"));
        assertThat(postcode.getParishCode(), is("PCode"));
        assertThat(postcode.getCensusOutputArea(), is("CArea"));
        assertThat(postcode.getConstituencyCode(), is("SW"));
        assertThat(postcode.getMultipleDeprivationIndex(), is(nullValue()));
        assertThat(postcode.getQuality(), is(nullValue()));
        assertThat(postcode.getUserType(), is(nullValue()));
        assertThat(postcode.getLastUpdated(), is("30-3-2019"));
        assertThat(postcode.getNearestStation(), is("Hogwarts"));
        assertThat(postcode.getDistanceToStation(), is(500.0));
        assertThat(postcode.getPostcodeArea(), is("LSS"));
        assertThat(postcode.getPostcodeDistrict(), is("LS6"));
        assertThat(postcode.getPoliceForce(), is("The Bill"));
    }

    @Test
    public void shouldProcessNullDoubleValuesIntoPostcode() {
        PostcodeProcessor processor = new PostcodeProcessor();
        Map<String, String> map = generatePostcodeMap();
        map.remove(PostcodeProcessor.ALTITUDE);
        map.remove(PostcodeProcessor.DISTANCE_STATION);

        Postcode postcode = processor.process(map);

        assertNotNull(postcode);
        assertThat(postcode.getPostcode(), is("LS69BH"));
        assertThat(postcode.getInUse(), is(true));
        assertThat(postcode.getLatitude(), is(0.0));
        assertThat(postcode.getLongitude(), is(1.0));
        assertThat(postcode.getEasting(), is(123L));
        assertThat(postcode.getNorthing(), is(321L));
        assertThat(postcode.getGridRef(), is("GRID"));
        assertThat(postcode.getCounty(), is("Supershire"));
        assertThat(postcode.getDistrict(), is("LS6"));
        assertThat(postcode.getWard(), is("Super Ward"));
        assertThat(postcode.getDistrictCode(), is("LS6T"));
        assertThat(postcode.getWardCode(), is("Ward code"));
        assertThat(postcode.getCountry(), is("United Kingdom"));
        assertThat(postcode.getCountryCode(), is("UK"));
        assertThat(postcode.getConstituency(), is("Super Land"));
        assertThat(postcode.getIntroduced(), is("26-10-1999"));
        assertThat(postcode.getTerminated(), is("27-10-1999"));
        assertThat(postcode.getParish(), is("Spaghetti Monster"));
        assertThat(postcode.getNationalPark(), is("Kruger"));
        assertThat(postcode.getPopulation(), is(342L));
        assertThat(postcode.getHouseholds(), is(888L));
        assertThat(postcode.getBuiltUpArea(), is("Swole area"));
        assertThat(postcode.getBuiltUpSubDivision(), is("Swole division"));
        assertThat(postcode.getLowerLayerOutputArea(), is("Super Low"));
        assertThat(postcode.getRuralUrban(), is("Rural"));
        assertThat(postcode.getRegion(), is("Super Region"));
        assertThat(postcode.getAltitude(), is(nullValue()));
        assertThat(postcode.getLondonZone(), is("Kent"));
        assertThat(postcode.getLsoaCode(), is("LSOA"));
        assertThat(postcode.getLocalAuthority(), is("Rogue One"));
        assertThat(postcode.getMsoaCode(), is("MSOA"));
        assertThat(postcode.getMiddleLayerOutputArea(), is("Super Mid"));
        assertThat(postcode.getParishCode(), is("PCode"));
        assertThat(postcode.getCensusOutputArea(), is("CArea"));
        assertThat(postcode.getConstituencyCode(), is("SW"));
        assertThat(postcode.getMultipleDeprivationIndex(), is(2345L));
        assertThat(postcode.getQuality(), is(1L));
        assertThat(postcode.getUserType(), is(0L));
        assertThat(postcode.getLastUpdated(), is("30-3-2019"));
        assertThat(postcode.getNearestStation(), is("Hogwarts"));
        assertThat(postcode.getDistanceToStation(), is(nullValue()));
        assertThat(postcode.getPostcodeArea(), is("LSS"));
        assertThat(postcode.getPostcodeDistrict(), is("LS6"));
        assertThat(postcode.getPoliceForce(), is("The Bill"));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowNumberExceptionWhenLatLonNotProvided() {
        PostcodeProcessor processor = new PostcodeProcessor();
        Map<String, String> map = generatePostcodeMap();
        map.remove(PostcodeProcessor.LATITUDE);
        map.remove(PostcodeProcessor.LONGITUDE);

        processor.process(map);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowNumberExceptionWhenNonNumericLatLonProvided() {
        PostcodeProcessor processor = new PostcodeProcessor();
        Map<String, String> map = generatePostcodeMap();
        map.put(PostcodeProcessor.LATITUDE, "String");
        map.put(PostcodeProcessor.LONGITUDE, "Other String");

        processor.process(map);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowNumberExceptionWhenNonNumericValuesProvided() {
        PostcodeProcessor processor = new PostcodeProcessor();
        Map<String, String> map = generatePostcodeMap();
        map.put(PostcodeProcessor.EASTING, "e");
        map.put(PostcodeProcessor.NORTHING, "n");
        map.put(PostcodeProcessor.POPULATION, "p");
        map.put(PostcodeProcessor.HOUSEHOLDS, "h");
        map.put(PostcodeProcessor.MULTI_DEPRIVATION_INDEX, "m");
        map.put(PostcodeProcessor.QUALITY, "q");
        map.put(PostcodeProcessor.USER_TYPE, "u");
        map.put(PostcodeProcessor.ALTITUDE, "a");
        map.put(PostcodeProcessor.DISTANCE_STATION, "d");

        processor.process(map);
    }

    private Map<String, String> generatePostcodeMap() {
        Map<String, String> map = new HashMap<>();
        map.put(PostcodeProcessor.POSTCODE, "LS69BH");
        map.put(PostcodeProcessor.IN_USE, "YES");
        map.put(PostcodeProcessor.LATITUDE, "0.0");
        map.put(PostcodeProcessor.LONGITUDE, "1.0");
        map.put(PostcodeProcessor.EASTING, "123");
        map.put(PostcodeProcessor.NORTHING, "321");
        map.put(PostcodeProcessor.GRID_REF, "GRID");
        map.put(PostcodeProcessor.COUNTY, "Supershire");
        map.put(PostcodeProcessor.DISTRICT, "LS6");
        map.put(PostcodeProcessor.WARD, "Super Ward");
        map.put(PostcodeProcessor.DISTRICT_CODE, "LS6T");
        map.put(PostcodeProcessor.WARD_CODE, "Ward code");
        map.put(PostcodeProcessor.COUNTRY, "United Kingdom");
        map.put(PostcodeProcessor.COUNTRY_CODE, "UK");
        map.put(PostcodeProcessor.CONSTITUENCY, "Super Land");
        map.put(PostcodeProcessor.INTRODUCED, "26-10-1999");
        map.put(PostcodeProcessor.TERMINATED, "27-10-1999");
        map.put(PostcodeProcessor.PARISH, "Spaghetti Monster");
        map.put(PostcodeProcessor.NATIONAL_PARK, "Kruger");
        map.put(PostcodeProcessor.POPULATION, "342");
        map.put(PostcodeProcessor.HOUSEHOLDS, "888");
        map.put(PostcodeProcessor.BUILT_UP_AREA, "Swole area");
        map.put(PostcodeProcessor.BUILT_UP_SUB_DIVISION, "Swole division");
        map.put(PostcodeProcessor.LOWER_LAYER, "Super Low");
        map.put(PostcodeProcessor.RURAL_URBAN, "Rural");
        map.put(PostcodeProcessor.REGION, "Super Region");
        map.put(PostcodeProcessor.ALTITUDE, "123456");
        map.put(PostcodeProcessor.LONDON_ZONE, "Kent");
        map.put(PostcodeProcessor.LSOA_CODE, "LSOA");
        map.put(PostcodeProcessor.LOCAL_AUTHORITY, "Rogue One");
        map.put(PostcodeProcessor.MSOA_CODE, "MSOA");
        map.put(PostcodeProcessor.MIDDLE_LAYER, "Super Mid");
        map.put(PostcodeProcessor.PARISH_CODE, "PCode");
        map.put(PostcodeProcessor.CENSUS_OUTPUT, "CArea");
        map.put(PostcodeProcessor.CONSTITUENCY_CODE, "SW");
        map.put(PostcodeProcessor.MULTI_DEPRIVATION_INDEX, "2345");
        map.put(PostcodeProcessor.QUALITY, "1");
        map.put(PostcodeProcessor.USER_TYPE, "0");
        map.put(PostcodeProcessor.LAST_UPDATED, "30-3-2019");
        map.put(PostcodeProcessor.NEAREST_STATION, "Hogwarts");
        map.put(PostcodeProcessor.DISTANCE_STATION, "500.0");
        map.put(PostcodeProcessor.POSTCODE_AREA, "LSS");
        map.put(PostcodeProcessor.POSTCODE_DISTRICT, "LS6");
        map.put(PostcodeProcessor.POLICE_FORCE, "The Bill");

        return map;
    }

}
