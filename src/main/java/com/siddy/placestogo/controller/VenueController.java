package com.siddy.placestogo.controller;

import com.siddy.placestogo.model.Venue;
import com.siddy.placestogo.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {

    VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> venues = venueService.getAllVenues();
        return new ResponseEntity<>(venues, HttpStatus.OK);
    }

    @GetMapping({"/{venueId}"})
    public ResponseEntity<Venue> getBookById(@PathVariable Long venueId) {
        return new ResponseEntity<>(venueService.getVenueById(venueId), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Venue> addVenue(@RequestBody Venue venue) {
        Venue newVenue = venueService.insertVenue(venue);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("venue", "/venue/" + newVenue.getId().toString());
        return new ResponseEntity<>(newVenue, httpHeaders, HttpStatus.CREATED);
    }
}
