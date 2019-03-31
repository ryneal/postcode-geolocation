package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.util.NumberUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PostcodeProcessor implements ItemProcessor<Map<String, String>, Postcode> {

    private static final String YES = "YES";

    private static final String POSTCODE = "Postcode";
    private static final String IN_USE = "In Use?";
    private static final String LATITUDE = "Latitude";
    private static final String LONGITUDE = "Longitude";
    private static final String EASTING = "Easting";
    private static final String NORTHING = "Northing";
    private static final String GRID_REF = "Grid Ref";
    private static final String COUNTY = "County";
    private static final String DISTRICT = "District";
    private static final String WARD = "Ward";
    private static final String DISTRICT_CODE = "District Code";
    private static final String WARD_CODE = "Ward Code";
    private static final String COUNTRY = "Country";
    private static final String COUNTRY_CODE = "County Code";
    private static final String CONSTITUENCY = "Constituency";
    private static final String INTRODUCED = "Introduced";
    private static final String TERMINATED = "Terminated";
    private static final String PARISH = "Parish";
    private static final String NATIONAL_PARK = "National Park";
    private static final String POPULATION = "Population";
    private static final String HOUSEHOLDS = "Households";
    private static final String BUILT_UP_AREA = "Built up area";
    private static final String BUILT_UP_SUB_DIVISION = "Built up sub-division";
    private static final String LOWER_LAYER = "Lower layer super output area";
    private static final String RURAL_URBAN = "Rural/urban";
    private static final String REGION = "Region";
    private static final String ALTITUDE = "Altitude";
    private static final String LONDON_ZONE = "London zone";
    private static final String LSOA_CODE = "LSOA Code";
    private static final String LOCAL_AUTHORITY = "Local authority";
    private static final String MSOA_CODE = "MSOA Code";
    private static final String MIDDLE_LAYER = "Middle layer super output area";
    private static final String PARISH_CODE = "Parish Code";
    private static final String CENSUS_OUTPUT = "Census output area";
    private static final String CONSTITUENCY_CODE = "Constituency Code";
    private static final String MULTI_DEPRIVATION_INDEX = "Index of Multiple Deprivation";
    private static final String QUALITY = "Quality";
    private static final String USER_TYPE = "User Type";
    private static final String LAST_UPDATED = "Last updated";
    private static final String NEAREST_STATION = "Nearest station";
    private static final String DISTANCE_STATION = "Distance to station";
    private static final String POSTCODE_AREA = "Postcode area";
    private static final String POSTCODE_DISTRICT = "Postcode district";
    private static final String POLICE_FORCE = "Police force";

    @Override
    public Postcode process(Map<String, String> map) {
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
                .buildUpSubDivision(map.get(BUILT_UP_SUB_DIVISION))
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
    }
}
