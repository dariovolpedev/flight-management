package com.flight.management.repository;

import org.springframework.stereotype.Repository;

import com.flight.management.entity.Flight;
import com.flight.management.utils.mongo.BaseRepository;

@Repository
public interface FlightRepository extends BaseRepository<Flight, String>{

}
