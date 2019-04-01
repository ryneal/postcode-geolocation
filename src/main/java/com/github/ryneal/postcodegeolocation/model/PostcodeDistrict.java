package com.github.ryneal.postcodegeolocation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ryneal.postcodegeolocation.model.audit.Auditable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostcodeDistrict extends Auditable implements GeoSpatial {

    @Id
    private String postcode;
    private Long easting;
    private Long northing;
    private String gridReference;
    private String townArea;
    private String region;
    private Long postcodes;
    private Long activePostcodes;
    private Long population;
    private Long households;
    private List<String> nearbyDistricts;

    @JsonIgnore
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    @JsonProperty("latitude")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Double getLatitude() {
        return this.location != null ? this.location.getY() : null;
    }

    @JsonProperty("longitude")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Double getLongitude() {
        return this.location != null ? this.location.getX() : null;
    }
}
