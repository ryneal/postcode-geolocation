package com.github.ryneal.postcodegeolocation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ryneal.postcodegeolocation.model.audit.Auditable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;

@EqualsAndHashCode(callSuper = true)
@RedisHash("Postcode")
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Postcode extends Auditable implements GeoSpatial {

    @Id
    @Indexed
    private String postcode;
    private Boolean inUse;
    private String district;
    private String districtCode;
    @JsonIgnore
    @GeoIndexed
    private Point location;

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
