package com.github.ryneal.postcodegeolocation.controller;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.service.PostcodeService;
import com.github.ryneal.postcodegeolocation.util.comparator.DistanceComparator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostcodeController {

    private static final String POSTCODE_REGEX = "^([G][I][R] 0[A]{2})|((([A-Z][0-9]{1,2})|(([A-Z][A-HJ-Y][0-9]{1,2})|(([AZ][0-9][A-Z])|([A-Z][A-HJ-Y][0-9]?[A-Z]))))[0-9][A-Z]{2})$";

    private PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/v1/postcodes/{postcode}")
    public ResponseEntity<Postcode> getPostcode(@PathVariable @Pattern(regexp = POSTCODE_REGEX, message = "Invalid Postcode") String postcode) {
        return ResponseEntity.of(this.postcodeService.readPostcode(postcode));
    }

    @GetMapping("/v1/postcodes")
    public ResponseEntity<List<Postcode>> getPostcodesWithQuery(@RequestParam("lat")
                                                                @DecimalMax(value = "90.0", message = "Latitude maximum is 90")
                                                                @DecimalMin(value = "-90.0", message = "Latitude minimum is -90")
                                                                        Double lat,
                                                                @RequestParam("lon")
                                                                @DecimalMax(value = "180.0", message = "Longitude maximum is 180")
                                                                @DecimalMin(value = "-180.0", message = "Longitude minimum is -180")
                                                                        Double lon,
                                                                @RequestParam(required = false, defaultValue = "0.01")
                                                                @DecimalMax(value = "0.5", message = "Distance maximum is 1")
                                                                @DecimalMin(value = "0.0", message = "Distance minimum is 0")
                                                                        Double distance) {
        return ResponseEntity.ok(this.postcodeService.readPostcodesInArea(lat, lon, distance).stream()
                .sorted(new DistanceComparator(lat, lon))
                .collect(Collectors.toList()));
    }
}
