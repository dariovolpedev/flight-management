package com.flight.management.service;

import com.flight.management.dto.FlightDto;
import com.flight.management.dto.FlightInputDto;
import com.flight.management.entity.Customer;
import com.flight.management.entity.Flight;

public interface FlightService extends BaseService<Flight, FlightDto> {

	Flight addPassenger(String flightId, Customer customer);

	Flight createFlight(FlightInputDto dto);

	Flight removePassenger(String flightId, String customerId);

}
