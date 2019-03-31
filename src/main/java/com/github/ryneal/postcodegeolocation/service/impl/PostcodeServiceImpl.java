package com.github.ryneal.postcodegeolocation.service.impl;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.repository.PostcodeRepository;
import com.github.ryneal.postcodegeolocation.service.PostcodeService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostcodeServiceImpl implements PostcodeService {

    private PostcodeRepository postcodeRepository;

    public PostcodeServiceImpl(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }

    @Override
    public Optional<Postcode> readPostcode(String postcode) {
        return this.postcodeRepository.findFirstByPostcode(postcode);
    }

    @Override
    public List<Postcode> readPostcodesInArea(Double latitude, Double longitude, Double distance) {
        return this.postcodeRepository.findByLocationNear(new Point(latitude, longitude), new Distance(distance));
    }

    @Override
    public Postcode savePostcode(Postcode postcode) {
        return this.postcodeRepository.save(postcode);
    }

    @Override
    public void write(List<? extends Postcode> list) {
        this.postcodeRepository.saveAll(list);
    }
}
