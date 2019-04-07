package com.github.ryneal.postcodegeolocation.controller;

import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.repository.PostcodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Invalid Postcode"))
                .andExpect(status().isBadRequest());
    }

    private void setupData() {
        Postcode postcode1 = Postcode.builder()
                .postcode("S48AA")
                .location(new GeoJsonPoint(0.0, 0.0))
                .build();
        this.repository.save(postcode1);

        Postcode postcode2 = Postcode.builder()
                .postcode("LS119BH")
                .location(new GeoJsonPoint(5.0, 4.0))
                .build();
        this.repository.save(postcode2);

        Postcode postcode3 = Postcode.builder()
                .postcode("LS119BT")
                .location(new GeoJsonPoint(5.0, 4.05))
                .build();
        this.repository.save(postcode3);

        Postcode postcode4 = Postcode.builder()
                .postcode("LS119BT")
                .location(new GeoJsonPoint(5.05, 4.0))
                .build();
        this.repository.save(postcode4);
    }

}
