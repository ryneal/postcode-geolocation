package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PostcodeDistrictProcessor implements ItemProcessor<Map<String, String>, PostcodeDistrict> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostcodeDistrictProcessor.class);

    static final String POSTCODE = "Postcode";
    static final String LATITUDE = "Latitude";
    static final String LONGITUDE = "Longitude";

    @Override
    public PostcodeDistrict process(Map<String, String> map) throws NumberFormatException {
        try {
            Double longitude = NumberUtil.parseDouble(map.get(LONGITUDE));
            Double latitude = NumberUtil.parseDouble(map.get(LATITUDE));
            return PostcodeDistrict
                    .builder()
                    .postcode(map.get(POSTCODE).replaceAll(" ", ""))
                    .location(new Point(longitude, latitude))
                    .build();
        } catch (NumberFormatException e) {
            LOGGER.error("Postcode District: " + map.get(POSTCODE) + " Invalid numeric entry provided: " + e.getMessage());
            return null;
        } catch (NullPointerException e) {
            LOGGER.error("Postcode District: " + map.get(POSTCODE) + " Null lat/lon value provided");
            return null;
        }
    }
}
