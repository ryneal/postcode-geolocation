package com.github.ryneal.postcodegeolocation.util.comparator;

import com.github.ryneal.postcodegeolocation.model.GeoSpatial;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HaversineComparatorTest {

    @Test
    public void comparisonShouldBeEqualWhenPointsAreIdentical() {
        TestGeo geo = new TestGeo(15.0, 27.0);
        Comparator<GeoSpatial> comparator = new HaversineComparator(15.0, 27.0);

        int result = comparator.compare(geo, geo);

        assertThat(result, is(0));
    }

    @Test
    public void comparisonShouldBeGreaterWhenSecondGeoSpatialIsLarger() {
        TestGeo first = new TestGeo(25.0, 37.0);
        TestGeo second = new TestGeo(16.0, 28.0);
        Comparator<GeoSpatial> comparator = new HaversineComparator(15.0, 27.0);

        int result = comparator.compare(first, second);

        assertThat(result, is(1));
    }

    @Test
    public void comparisonShouldBeLesserWhenSecondGeoSpatialIsLarger() {
        TestGeo first = new TestGeo(16.0, 27.0);
        TestGeo second = new TestGeo(25.0, 38.0);
        Comparator<GeoSpatial> comparator = new HaversineComparator(15.0, 27.0);

        int result = comparator.compare(first, second);

        assertThat(result, is(-1));
    }

    @AllArgsConstructor
    @Data
    private class TestGeo implements GeoSpatial {
        private Double latitude;
        private Double longitude;
    }

}
