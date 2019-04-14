package com.github.ryneal.postcodegeolocation.repository;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostcodeDistrictRepository extends CrudRepository<PostcodeDistrict, String> {
    Optional<PostcodeDistrict> findFirstByPostcode(String postcodeDistrict);

    List<PostcodeDistrict> findByLocationNear(Point point, Distance max);
}
