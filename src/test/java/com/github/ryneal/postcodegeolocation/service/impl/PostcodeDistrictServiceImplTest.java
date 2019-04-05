package com.github.ryneal.postcodegeolocation.service.impl;

import com.github.ryneal.postcodegeolocation.model.PostcodeDistrict;
import com.github.ryneal.postcodegeolocation.repository.PostcodeDistrictRepository;
import com.github.ryneal.postcodegeolocation.service.PostcodeDistrictService;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PostcodeDistrictServiceImplTest {

    @Test
    public void shouldSavePostcodeDistrict() {
        PostcodeDistrictRepository repository = mock(PostcodeDistrictRepository.class);
        PostcodeDistrictService service = new PostcodeDistrictServiceImpl(repository);
        PostcodeDistrict postcodeDistrict = mock(PostcodeDistrict.class);
        PostcodeDistrict savedDistrict = mock(PostcodeDistrict.class);
        when(repository.save(postcodeDistrict)).thenReturn(savedDistrict);

        PostcodeDistrict result = service.savePostcodeDistrict(postcodeDistrict);

        verify(repository, times(1)).save(postcodeDistrict);
        verifyNoMoreInteractions(repository);
        assertThat(result, is(savedDistrict));
    }

    @Test
    public void shouldReadPostcodeCodeDistrict() {
        PostcodeDistrictRepository repository = mock(PostcodeDistrictRepository.class);
        PostcodeDistrictService service = new PostcodeDistrictServiceImpl(repository);
        PostcodeDistrict postcodeDistrict = mock(PostcodeDistrict.class);
        when(repository.findFirstByPostcode("ABC")).thenReturn(Optional.of(postcodeDistrict));

        Optional<PostcodeDistrict> result = service.readPostcodeDistrict("ABC");

        verify(repository, times(1)).findFirstByPostcode("ABC");
        verifyNoMoreInteractions(repository);
        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(postcodeDistrict));
    }

    @Test
    public void shouldReadPostcodeCodeDistrictsInArea() {
        PostcodeDistrictRepository repository = mock(PostcodeDistrictRepository.class);
        PostcodeDistrictService service = new PostcodeDistrictServiceImpl(repository);
        PostcodeDistrict postcodeDistrict = mock(PostcodeDistrict.class);
        when(repository.findByLocationNear(any(), any()))
                .thenReturn(Collections.singletonList(postcodeDistrict));

        List<PostcodeDistrict> result = service.readPostcodeDistrictsInArea(1.0, 1.0, 1.0);

        verify(repository, times(1)).findByLocationNear(any(), any());
        verifyNoMoreInteractions(repository);
        assertThat(result.isEmpty(), is(false));
        assertThat(result.get(0), is(postcodeDistrict));
    }

}
