package com.github.ryneal.postcodegeolocation.repository;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostcodeRepository extends CrudRepository<Postcode, String> {
    Optional<Postcode> findFirstByPostcode(String postcode);
    List<Postcode> findByLocationNear(Point point, Distance max);
}
