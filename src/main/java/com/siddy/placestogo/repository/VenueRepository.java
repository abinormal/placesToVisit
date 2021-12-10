package com.siddy.placestogo.repository;

import com.siddy.placestogo.model.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends CrudRepository<Venue, Long> {

}
