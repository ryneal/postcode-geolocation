package com.github.ryneal.postcodegeolocation.controller;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.repository.PostcodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PostcodeControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PostcodeRepository repository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        setupData();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("postcodeController"));
    }

    @Test
    public void givenPostcode_whenSearched_thenResultProvided() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes/S48AA"))
                .andExpect(jsonPath("$.postcode").value("S48AA"))
                .andExpect(jsonPath("$.latitude").value("0.0"))
                .andExpect(jsonPath("$.longitude").value("0.0"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidPostcode_whenSearched_thenResultStatusIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes/s6f"))
                .andExpect(jsonPath("$.message").value("Invalid Postcode"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenValidCoordsWithoutDistance_whenSearched_thenResultProvided() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=5.0&lon=4.0"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].postcode").value("LS119BH"))
                .andExpect(jsonPath("$.[0].latitude").value("4.0"))
                .andExpect(jsonPath("$.[0].longitude").value("5.0"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidCoordsWithDefaultDistance_whenSearched_thenResultProvided() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=5.0&lon=4.0&distance=0.01"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].postcode").value("LS119BH"))
                .andExpect(jsonPath("$.[0].latitude").value("4.0"))
                .andExpect(jsonPath("$.[0].longitude").value("5.0"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidCoordsWithLargerDistance_whenSearched_thenResultsProvidedInOrderOfDistance() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=5.0&lon=4.0&distance=0.1"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].postcode").value("LS119BH"))
                .andExpect(jsonPath("$.[0].latitude").value("4.0"))
                .andExpect(jsonPath("$.[0].longitude").value("5.0"))
                .andExpect(jsonPath("$.[1].postcode").value("LS119BT"))
                .andExpect(jsonPath("$.[1].latitude").value("4.05"))
                .andExpect(jsonPath("$.[1].longitude").value("6.0"))
                .andExpect(jsonPath("$.[2].postcode").value("LS119BJ"))
                .andExpect(jsonPath("$.[2].latitude").value("4.05"))
                .andExpect(jsonPath("$.[2].longitude").value("6.5"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidNegativeLat_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=-90.1&lon=4.0&distance=0.1"))
                .andExpect(jsonPath("$.message").value("Latitude minimum is -90"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidPositiveLat_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=90.1&lon=4.0&distance=0.1"))
                .andExpect(jsonPath("$.message").value("Latitude maximum is 90"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidLat_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=abc&lon=4.0&distance=0.1"))
                .andExpect(jsonPath("$.message").value("A non-numeric value was provided, For input string: \"abc\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidNegativeLon_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=90&lon=-180.1&distance=0.1"))
                .andExpect(jsonPath("$.message").value("Longitude minimum is -180"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidPositiveLon_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=90&lon=180.1&distance=0.1"))
                .andExpect(jsonPath("$.message").value("Longitude maximum is 180"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidLon_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=0.0&lon=abc&distance=0.1"))
                .andExpect(jsonPath("$.message").value("A non-numeric value was provided, For input string: \"abc\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidDistance_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=0.0&lon=0.0&distance=abc"))
                .andExpect(jsonPath("$.message").value("A non-numeric value was provided, For input string: \"abc\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenOverMaxDistance_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=0.0&lon=0.0&distance=0.51"))
                .andExpect(jsonPath("$.message").value("Distance maximum is 0.5"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenBelowZeroDistance_whenSearched_thenResultIsBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/postcodes?lat=0.0&lon=0.0&distance=-0.01"))
                .andExpect(jsonPath("$.message").value("Distance minimum is 0"))
                .andExpect(status().isBadRequest());
    }

    private void setupData() {
        Postcode postcode1 = Postcode.builder()
                .postcode("S48AA")
                .location(new Point(0.0, 0.0))
                .build();
        this.repository.save(postcode1);

        Postcode postcode2 = Postcode.builder()
                .postcode("LS119BH")
                .location(new Point(5.0, 4.0))
                .build();
        this.repository.save(postcode2);

        Postcode postcode3 = Postcode.builder()
                .postcode("LS119BT")
                .location(new Point(6.0, 4.05))
                .build();
        this.repository.save(postcode3);

        Postcode postcode4 = Postcode.builder()
                .postcode("LS119BJ")
                .location(new Point(6.5, 4.05))
                .build();
        this.repository.save(postcode4);
    }

}
