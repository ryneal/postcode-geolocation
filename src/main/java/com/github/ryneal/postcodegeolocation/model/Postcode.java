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

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Postcode extends Auditable implements GeoSpatial {

    @Id
    private String postcode;
    private Boolean inUse;
    private Long easting;
    private Long northing;
    private String gridRef;
    private String county;
    private String district;
    private String ward;
    private String districtCode;
    private String wardCode;
    private String country;
    private String countryCode;
    private String constituency;
    private String introduced;
    private String terminated;
    private String parish;
    private String nationalPark;
    private Long population;
    private Long households;
    private String builtUpArea;
    private String builtUpSubDivision;
    private String lowerLayerOutputArea;
    private String ruralUrban;
    private String region;
    private Double altitude;
    private String londonZone;
    private String lsoaCode;
    private String localAuthority;
    private String msoaCode;
    private String middleLayerOutputArea;
    private String parishCode;
    private String censusOutputArea;
    private String constituencyCode;
    private Long multipleDeprivationIndex;
    private Long quality;
    private Long userType;
    private String lastUpdated;
    private String nearestStation;
    private Double distanceToStation;
    private String postcodeArea;
    private String postcodeDistrict;
    private String policeForce;
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
