package com.flight.management.service;

import com.flight.management.dto.FlightDto;
import com.flight.management.entity.Customer;
import com.flight.management.entity.Flight;

public interface FlightService extends BaseService<Flight, FlightDto> {
	
	Flight removePassenger(String flightId, Customer customer);

	Flight addPassenger(String flightId, Customer customer);

}
