package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PostcodeProcessor implements ItemProcessor<Map<String, String>, Postcode> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostcodeProcessor.class);
    private static final String YES = "YES";

    static final String POSTCODE = "Postcode";
    static final String IN_USE = "In Use?";
    static final String LATITUDE = "Latitude";
    static final String LONGITUDE = "Longitude";

    @Override
    public Postcode process(Map<String, String> map) throws NumberFormatException {
        try {
            return Postcode.builder()
                    .postcode(map.get(POSTCODE).replaceAll(" ", ""))
                    .inUse(YES.equalsIgnoreCase(map.get(IN_USE)))
                    .location(new Point(
                            NumberUtil.parseDouble(map.get(LONGITUDE)),
                            NumberUtil.parseDouble(map.get(LATITUDE))))
                    .build();
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid numeric entry provided", e);
            throw e;
        } catch (NullPointerException e) {
            LOGGER.error("Null lat/lon value provided", e);
            throw new NumberFormatException("Invalid lat/lon provided");
        }
    }
}
