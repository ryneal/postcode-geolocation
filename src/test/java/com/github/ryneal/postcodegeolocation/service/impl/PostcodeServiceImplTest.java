package com.github.ryneal.postcodegeolocation.service.impl;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.repository.PostcodeRepository;
import com.github.ryneal.postcodegeolocation.service.PostcodeService;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PostcodeServiceImplTest {

    @Test
    public void shouldSavePostcode() {
        PostcodeRepository repository = mock(PostcodeRepository.class);
        PostcodeService service = new PostcodeServiceImpl(repository);
        Postcode postcode = mock(Postcode.class);
        Postcode savedDistrict = mock(Postcode.class);
        when(repository.save(postcode)).thenReturn(savedDistrict);

        Postcode result = service.savePostcode(postcode);

        verify(repository, times(1)).save(postcode);
        verifyNoMoreInteractions(repository);
        assertThat(result, is(savedDistrict));
    }

    @Test
    public void shouldReadPostcodeCodeDistrict() {
        PostcodeRepository repository = mock(PostcodeRepository.class);
        PostcodeService service = new PostcodeServiceImpl(repository);
        Postcode postcode = mock(Postcode.class);
        when(repository.findFirstByPostcode("ABC")).thenReturn(Optional.of(postcode));

        Optional<Postcode> result = service.readPostcode("ABC");

        verify(repository, times(1)).findFirstByPostcode("ABC");
        verifyNoMoreInteractions(repository);
        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(postcode));
    }

    @Test
    public void shouldReadPostcodeCodeDistrictsInArea() {
        PostcodeRepository repository = mock(PostcodeRepository.class);
        PostcodeService service = new PostcodeServiceImpl(repository);
        Postcode postcode = mock(Postcode.class);
        when(repository.findByLocationNear(any(), any()))
                .thenReturn(Collections.singletonList(postcode));

        List<Postcode> result = service.readPostcodesInArea(1.0, 1.0, 1.0);

        verify(repository, times(1)).findByLocationNear(any(), any());
        verifyNoMoreInteractions(repository);
        assertThat(result.isEmpty(), is(false));
        assertThat(result.get(0), is(postcode));
    }

}
