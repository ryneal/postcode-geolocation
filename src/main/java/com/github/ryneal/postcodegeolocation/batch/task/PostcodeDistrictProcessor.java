package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.util.NumberUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class PostcodeDistrictProcessor implements ItemProcessor<Map<String, String>, PostcodeDistrict> {

    private static final String POSTCODE = "Postcode";
    private static final String LATITUDE = "Latitude";
    private static final String LONGITUDE = "Longitude";
    private static final String EASTING = "Easting";
    private static final String NORTHING = "Northing";
    private static final String GRID_REFRENCE = "Grid Reference";
    private static final String TOWN_AREA = "Town/Area";
    private static final String REGION = "Region";
    private static final String POSTCODES = "Postcodes";
    private static final String ACTIVE_POSTCODES = "Active postcodes";
    private static final String POPULATION = "Population";
    private static final String HOUSEHOLDS = "Households";
    private static final String NEARBY_DISTRICTS = "Nearby districts";

    @Override
    public PostcodeDistrict process(Map<String, String> map) throws Exception {
        Double longitude = NumberUtil.parseDouble(map.get(LONGITUDE));
        Double latitude = NumberUtil.parseDouble(map.get(LATITUDE));
        PostcodeDistrict.PostcodeDistrictBuilder builder = PostcodeDistrict
                .builder()
                .postcode(map.get(POSTCODE));

        if (latitude != null && longitude != null) {
            builder = builder.location(new GeoJsonPoint(longitude, latitude));
        }

        return builder
                .easting(NumberUtil.parseLong(map.get(EASTING)))
                .northing(NumberUtil.parseLong(map.get(NORTHING)))
                .gridReference(map.get(GRID_REFRENCE))
                .townArea(map.get(TOWN_AREA))
                .region(map.get(REGION))
                .postcodes(NumberUtil.parseLong(map.get(POSTCODES)))
                .activePostcodes(NumberUtil.parseLong(map.get(ACTIVE_POSTCODES)))
                .population(NumberUtil.parseLong(map.get(POPULATION)))
                .households(NumberUtil.parseLong(map.get(HOUSEHOLDS)))
                .nearbyDistricts(Arrays.asList(Optional.ofNullable(map.get(NEARBY_DISTRICTS)).orElse("").split(",")))
                .build();
    }
}
