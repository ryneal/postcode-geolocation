package com.github.ryneal.postcodegeolocation.util.comparator;

import com.github.ryneal.postcodegeolocation.model.GeoSpatial;

import java.util.Comparator;

public class DistanceComparator implements Comparator<GeoSpatial> {

    private Double lat;
    private Double lon;

    public DistanceComparator(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public int compare(GeoSpatial o1, GeoSpatial o2) {
        double o1Distance = calculateDistance(o1);
        double o2Distance = calculateDistance(o2);
        return Double.compare(o1Distance, o2Distance);
    }

    private double calculateDistance(GeoSpatial postcode) {
        return Math.sqrt((lon - postcode.getLongitude()) * (lon - postcode.getLongitude()) +
                (lat - postcode.getLatitude()) * (lat - postcode.getLatitude()));
    }
}
