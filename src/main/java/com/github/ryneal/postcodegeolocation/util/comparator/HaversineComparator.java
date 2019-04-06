package com.github.ryneal.postcodegeolocation.util.comparator;

import com.github.ryneal.postcodegeolocation.model.GeoSpatial;

import java.util.Comparator;

public class HaversineComparator implements Comparator<GeoSpatial> {

    private Double lat;
    private Double lon;

    public HaversineComparator(Double lat, Double lon) {
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
        double dLat = Math.toRadians(postcode.getLatitude() - lat);
        double dLon = Math.toRadians(postcode.getLongitude() - lon);
        double lat1 = Math.toRadians(lat);
        double lat2 = Math.toRadians(postcode.getLatitude());
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        return  2 * Math.asin(Math.sqrt(a));
    }
}
