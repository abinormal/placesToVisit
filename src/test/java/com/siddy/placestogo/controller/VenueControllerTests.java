package com.siddy.placestogo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siddy.placestogo.model.EventCost;
import com.siddy.placestogo.model.EventType;
import com.siddy.placestogo.model.Venue;
import com.siddy.placestogo.service.VenueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class VenueControllerTests {

    @Mock
    private VenueServiceImpl mockVenueServiceImpl;

    @InjectMocks
    private VenueController venueController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(venueController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetVenues() throws Exception {
        List<Venue> venues = new ArrayList<>();
        venues.add(new Venue(1L, "Venue One", "This is the description for Venue One",
                "W4 V1", "www.VenueOne.com", EventType.Museum, EventCost.£));
        venues.add(new Venue(2L, "Venue Two", "This is the description for Venue Two",
                "W3 V2", "www.VenueTwo.com", EventType.Market,  EventCost.££));
        venues.add(new Venue(3L, "Venue Three", "This is the description for Venue Three",
                "W4 V3", "www.VenueThree.com", EventType.Restaurant, EventCost.£££));

        when(mockVenueServiceImpl.getAllVenues()).thenReturn(venues);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/venue"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Venue One"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Venue Two"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Venue Three"));
    }

    @Test
    public void testGetVenueById() throws Exception {
         Venue venue = new Venue(4L, "Venue Four", "This is the description for Venue One",
                "W4 V1", "www.VenueOne.com", EventType.Museum, EventCost.£);
         when(mockVenueServiceImpl.getVenueById(venue.getId())).thenReturn(venue);
                this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/venue/" + venue.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Venue Four"));
    }
}

