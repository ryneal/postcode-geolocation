package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Component
public class PostcodeDistrictProcessor implements ItemProcessor<Map<String, String>, PostcodeDistrict> {

    static final String POSTCODE = "Postcode";
    static final String LATITUDE = "Latitude";
    static final String LONGITUDE = "Longitude";
    static final String EASTING = "Easting";
    static final String NORTHING = "Northing";
    static final String GRID_REFERENCE = "Grid Reference";
    static final String TOWN_AREA = "Town/Area";
    static final String REGION = "Region";
    static final String POSTCODES = "Postcodes";
    static final String ACTIVE_POSTCODES = "Active postcodes";
    static final String POPULATION = "Population";
    static final String HOUSEHOLDS = "Households";
    static final String NEARBY_DISTRICTS = "Nearby districts";
    private static final Logger LOGGER = LoggerFactory.getLogger(PostcodeDistrictProcessor.class);

    @Override
    public PostcodeDistrict process(Map<String, String> map) throws NumberFormatException {
        try {
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
                    .gridReference(map.get(GRID_REFERENCE))
                    .townArea(map.get(TOWN_AREA))
                    .region(map.get(REGION))
                    .postcodes(NumberUtil.parseLong(map.get(POSTCODES)))
                    .activePostcodes(NumberUtil.parseLong(map.get(ACTIVE_POSTCODES)))
                    .population(NumberUtil.parseLong(map.get(POPULATION)))
                    .households(NumberUtil.parseLong(map.get(HOUSEHOLDS)))
                    .nearbyDistricts(Optional.ofNullable(map.get(NEARBY_DISTRICTS))
                            .filter(s -> !s.isEmpty())
                            .map(s -> s.split(","))
                            .map(Arrays::asList)
                            .orElse(Collections.emptyList()))
                    .build();
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid numeric entry provided", e);
            throw e;
        }
    }
}
