package com.github.ryneal.postcodegeolocation.service;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.Optional;

public interface PostcodeService extends ItemWriter<Postcode> {
    Optional<Postcode> readPostcode(String postcode);

    List<Postcode> readPostcodesInArea(Double latitude, Double longitude, Double distance);

    Postcode savePostcode(Postcode postcode);
}
