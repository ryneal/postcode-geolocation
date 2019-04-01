package com.github.ryneal.postcodegeolocation.service;

import com.github.ryneal.postcodegeolocation.model.Postcode;

import java.util.List;
import java.util.Optional;

public interface PostcodeService {
    Optional<Postcode> readPostcode(String postcode);

    List<Postcode> readPostcodesInArea(Double latitude, Double longitude, Double distance);

    Postcode savePostcode(Postcode postcode);
}
