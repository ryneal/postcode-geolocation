package com.github.ryneal.postcodegeolocation.controller;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.service.PostcodeDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.github.ryneal.postcodegeolocation.util.PostcodeConstants.POSTCODE_DISTRICT_REGEX;

@RestController
@Validated
public class PostcodeDistrictController extends BaseController {

    private PostcodeDistrictService postcodeDistrictService;

    public PostcodeDistrictController(PostcodeDistrictService postcodeDistrictService) {
        this.postcodeDistrictService = postcodeDistrictService;
    }

    @GetMapping("/v1/postcode_districts/{postcode}")
    public ResponseEntity<PostcodeDistrict> getPostcode(@PathVariable @Pattern(regexp = POSTCODE_DISTRICT_REGEX,
            message = "Invalid Postcode District") String postcode) {
        return ResponseEntity.of(this.postcodeDistrictService.readPostcodeDistrict(postcode));
    }

    @GetMapping("/v1/postcode_districts")
    public ResponseEntity<List<PostcodeDistrict>> getPostcodesWithQuery(@RequestParam("lat")
                                                                        @DecimalMax(value = "90.0", message = "Latitude maximum is 90")
                                                                        @DecimalMin(value = "-90.0", message = "Latitude minimum is -90")
                                                                                Double lat,
                                                                        @RequestParam("lon")
                                                                        @DecimalMax(value = "180.0", message = "Longitude maximum is 180")
                                                                        @DecimalMin(value = "-180.0", message = "Longitude minimum is -180")
                                                                                Double lon,
                                                                        @RequestParam(required = false, defaultValue = "0.01")
                                                                        @DecimalMax(value = "0.5", message = "Distance maximum is 0.5")
                                                                        @DecimalMin(value = "0.0", message = "Distance minimum is 0")
                                                                                Double distance) {
        return ResponseEntity.ok(this.postcodeDistrictService.readPostcodeDistrictsInArea(lat, lon, distance));
    }
}
