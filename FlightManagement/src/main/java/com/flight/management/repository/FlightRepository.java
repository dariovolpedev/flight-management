package com.flight.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flight.management.entity.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String>{

}
