package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.repository.PostcodeDistrictRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostcodeDistrictWriter implements ItemWriter<PostcodeDistrict> {

    private PostcodeDistrictRepository postcodeDistrictRepository;

    public PostcodeDistrictWriter(PostcodeDistrictRepository postcodeDistrictRepository) {
        this.postcodeDistrictRepository = postcodeDistrictRepository;
    }

    @Override
    public void write(List<? extends PostcodeDistrict> list) throws Exception {
        this.postcodeDistrictRepository.saveAll(list);
    }
}
