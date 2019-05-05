package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String, String> generatePostcodeMap() {
        Map<String, String> map = new HashMap<>();
        map.put(PostcodeProcessor.POSTCODE, "LS69BH");
        map.put(PostcodeProcessor.IN_USE, "YES");
        map.put(PostcodeProcessor.LATITUDE, "0.0");
        map.put(PostcodeProcessor.LONGITUDE, "1.0");

        return map;
    }

}
