package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.repository.PostcodeRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostcodeWriter implements ItemWriter<Postcode> {

    private PostcodeRepository postcodeRepository;

    public PostcodeWriter(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }


    @Override
    public void write(List<? extends Postcode> list) {
        this.postcodeRepository.saveAll(list);
    }

}
