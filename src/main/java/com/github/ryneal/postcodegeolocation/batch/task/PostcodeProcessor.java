package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
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
    static final String EASTING = "Easting";
    static final String NORTHING = "Northing";
    static final String GRID_REF = "Grid Ref";
    static final String COUNTY = "County";
    static final String DISTRICT = "District";
    static final String WARD = "Ward";
    static final String DISTRICT_CODE = "District Code";
    static final String WARD_CODE = "Ward Code";
    static final String COUNTRY = "Country";
    static final String COUNTRY_CODE = "County Code";
    static final String CONSTITUENCY = "Constituency";
    static final String INTRODUCED = "Introduced";
    static final String TERMINATED = "Terminated";
    static final String PARISH = "Parish";
    static final String NATIONAL_PARK = "National Park";
    static final String POPULATION = "Population";
    static final String HOUSEHOLDS = "Households";
    static final String BUILT_UP_AREA = "Built up area";
    static final String BUILT_UP_SUB_DIVISION = "Built up sub-division";
    static final String LOWER_LAYER = "Lower layer super output area";
    static final String RURAL_URBAN = "Rural/urban";
    static final String REGION = "Region";
    static final String ALTITUDE = "Altitude";
    static final String LONDON_ZONE = "London zone";
    static final String LSOA_CODE = "LSOA Code";
    static final String LOCAL_AUTHORITY = "Local authority";
    static final String MSOA_CODE = "MSOA Code";
    static final String MIDDLE_LAYER = "Middle layer super output area";
    static final String PARISH_CODE = "Parish Code";
    static final String CENSUS_OUTPUT = "Census output area";
    static final String CONSTITUENCY_CODE = "Constituency Code";
    static final String MULTI_DEPRIVATION_INDEX = "Index of Multiple Deprivation";
    static final String QUALITY = "Quality";
    static final String USER_TYPE = "User Type";
    static final String LAST_UPDATED = "Last updated";
    static final String NEAREST_STATION = "Nearest station";
    static final String DISTANCE_STATION = "Distance to station";
    static final String POSTCODE_AREA = "Postcode area";
    static final String POSTCODE_DISTRICT = "Postcode district";
    static final String POLICE_FORCE = "Police force";

    @Override
    public Postcode process(Map<String, String> map) throws NumberFormatException {
        try {
            return Postcode.builder()
                    .postcode(map.get(POSTCODE))
                    .inUse(YES.equalsIgnoreCase(map.get(IN_USE)))
                    .location(new GeoJsonPoint(
                            NumberUtil.parseDouble(map.get(LONGITUDE)),
                            NumberUtil.parseDouble(map.get(LATITUDE))))
                    .easting(NumberUtil.parseLong(map.get(EASTING)))
                    .northing(NumberUtil.parseLong(map.get(NORTHING)))
                    .gridRef(map.get(GRID_REF))
                    .county(map.get(COUNTY))
                    .district(map.get(DISTRICT))
                    .ward(map.get(WARD))
                    .districtCode(map.get(DISTRICT_CODE))
                    .wardCode(map.get(WARD_CODE))
                    .country(map.get(COUNTRY))
                    .countryCode(map.get(COUNTRY_CODE))
                    .constituency(map.get(CONSTITUENCY))
                    .introduced(map.get(INTRODUCED))
                    .terminated(map.get(TERMINATED))
                    .parish(map.get(PARISH))
                    .nationalPark(map.get(NATIONAL_PARK))
                    .population(NumberUtil.parseLong(map.get(POPULATION)))
                    .households(NumberUtil.parseLong(map.get(HOUSEHOLDS)))
                    .builtUpArea(map.get(BUILT_UP_AREA))
                    .builtUpSubDivision(map.get(BUILT_UP_SUB_DIVISION))
                    .lowerLayerOutputArea(map.get(LOWER_LAYER))
                    .ruralUrban(map.get(RURAL_URBAN))
                    .region(map.get(REGION))
                    .altitude(NumberUtil.parseDouble(map.get(ALTITUDE)))
                    .londonZone(map.get(LONDON_ZONE))
                    .lsoaCode(map.get(LSOA_CODE))
                    .localAuthority(map.get(LOCAL_AUTHORITY))
                    .msoaCode(map.get(MSOA_CODE))
                    .middleLayerOutputArea(map.get(MIDDLE_LAYER))
                    .parishCode(map.get(PARISH_CODE))
                    .censusOutputArea(map.get(CENSUS_OUTPUT))
                    .constituencyCode(map.get(CONSTITUENCY_CODE))
                    .multipleDeprivationIndex(NumberUtil.parseLong(map.get(MULTI_DEPRIVATION_INDEX)))
                    .quality(NumberUtil.parseLong(map.get(QUALITY)))
                    .userType(NumberUtil.parseLong(map.get(USER_TYPE)))
                    .lastUpdated(map.get(LAST_UPDATED))
                    .nearestStation(map.get(NEAREST_STATION))
                    .distanceToStation(NumberUtil.parseDouble(map.get(DISTANCE_STATION)))
                    .postcodeArea(map.get(POSTCODE_AREA))
                    .postcodeDistrict(map.get(POSTCODE_DISTRICT))
                    .policeForce(map.get(POLICE_FORCE))
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
