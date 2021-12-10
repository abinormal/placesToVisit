package com.siddy.placestogo.service;

import com.siddy.placestogo.model.Venue;
import com.siddy.placestogo.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        venueRepository.findAll().forEach(venues::add);
        return venues;
    }

    @Override
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).get();
    }

    @Override
    public Venue insertVenue(Venue venue) {
        return venueRepository.save(venue);
    }

}
