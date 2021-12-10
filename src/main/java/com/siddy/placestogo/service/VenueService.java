package com.siddy.placestogo.service;

import com.siddy.placestogo.model.Venue;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenues();
    Venue getVenueById(Long id);
    Venue insertVenue(Venue venue);
}
