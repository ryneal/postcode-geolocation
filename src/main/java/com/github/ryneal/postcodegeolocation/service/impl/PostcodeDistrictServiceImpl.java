package com.github.ryneal.postcodegeolocation.service.impl;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.repository.PostcodeDistrictRepository;
import com.github.ryneal.postcodegeolocation.service.PostcodeDistrictService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostcodeDistrictServiceImpl implements PostcodeDistrictService {

    private PostcodeDistrictRepository postcodeDistrictRepository;

    public PostcodeDistrictServiceImpl(PostcodeDistrictRepository postcodeDistrictRepository) {
        this.postcodeDistrictRepository = postcodeDistrictRepository;
    }

    @Override
    public Optional<PostcodeDistrict> readPostcodeDistrict(String postcodeDistrict) {
        return this.postcodeDistrictRepository.findFirstByPostcode(postcodeDistrict);
    }

    @Override
    public List<PostcodeDistrict> readPostcodeDistrictsInArea(Double latitude, Double longitude, Double distance) {
        return this.postcodeDistrictRepository.findByLocationNear(new Point(latitude, longitude), new Distance(distance));
    }

    @Override
    public PostcodeDistrict savePostcodeDistrict(PostcodeDistrict postcodeDistrict) {
        return this.postcodeDistrictRepository.save(postcodeDistrict);
    }
}
