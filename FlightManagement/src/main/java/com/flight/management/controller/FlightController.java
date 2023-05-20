package com.flight.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.management.dto.FlightInputDto;
import com.flight.management.entity.Flight;
import com.flight.management.service.FlightService;

@RestController
@RequestMapping("flight")
public class FlightController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	private FlightService service;

	@PostMapping()
	public ResponseEntity<Flight> createFlight(@RequestBody FlightInputDto dto) {
		LOGGER.info("Creating a new flight...");
		Flight flight = service.createFlight(dto);
		LOGGER.info("Flight created with id: " + flight.getId());
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}

	@GetMapping("/{flightId}")
	public ResponseEntity<Flight> getFlight(@PathVariable("flightId") String flightId) {
		LOGGER.info("Getting flight with id: " + flightId);
		Flight flight = service.getById(flightId);
		LOGGER.info("Flight found");
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Flight>> getAllCustomer() {
		LOGGER.info("Getting all flights");
		List<Flight> flights = service.getAll();
		LOGGER.info("Flights found: " + flights.size());
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}

	@DeleteMapping("/{flightId}")
	public HttpStatus deleteFlight(@PathVariable("flightId") String flightId) {
		LOGGER.info("Deleting flight with id: " + flightId);
		service.delete(flightId);
		LOGGER.info("Flight successfully deleted");
		return HttpStatus.OK;
	}

}
