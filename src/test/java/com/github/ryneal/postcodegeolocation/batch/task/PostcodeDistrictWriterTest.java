package com.github.ryneal.postcodegeolocation.batch.task;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.repository.PostcodeDistrictRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class PostcodeDistrictWriterTest {

    @Test
    public void shouldSaveListOfPostcodeDistricts() throws Exception {
        PostcodeDistrictRepository districtRepository = Mockito.mock(PostcodeDistrictRepository.class);
        PostcodeDistrictWriter writer = new PostcodeDistrictWriter(districtRepository);
        List<PostcodeDistrict> list = Collections.emptyList();

        writer.write(list);

        Mockito.verify(districtRepository, Mockito.times(1)).saveAll(list);
        Mockito.verifyNoMoreInteractions(districtRepository);
    }

}
