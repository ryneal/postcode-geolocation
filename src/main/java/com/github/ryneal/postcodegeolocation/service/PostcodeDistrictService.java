package com.github.ryneal.postcodegeolocation.service;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;

import java.util.List;
import java.util.Optional;

public interface PostcodeDistrictService {
    Optional<PostcodeDistrict> readPostcodeDistrict(String postcodeDistrict);

    List<PostcodeDistrict> readPostcodeDistrictsInArea(Double latitude, Double longitude, Double distance);

    Postcode savePostcodeDistrict(PostcodeDistrict postcodeDistrict);
}
