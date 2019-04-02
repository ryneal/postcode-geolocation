package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.repository.PostcodeRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class PostcodeWriterTest {

    @Test
    public void shouldSaveListOfPostcodeDistricts() throws Exception {
        PostcodeRepository postcodeRepository = Mockito.mock(PostcodeRepository.class);
        PostcodeWriter writer = new PostcodeWriter(postcodeRepository);
        List<Postcode> list = Collections.emptyList();

        writer.write(list);

        Mockito.verify(postcodeRepository, Mockito.times(1)).saveAll(list);
        Mockito.verifyNoMoreInteractions(postcodeRepository);
    }

}
