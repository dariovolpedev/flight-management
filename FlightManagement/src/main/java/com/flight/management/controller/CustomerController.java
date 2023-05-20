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

import com.flight.management.dto.CustomerDto;
import com.flight.management.entity.Customer;
import com.flight.management.service.CustomerService;
import com.flight.management.service.FlightService;

@RestController
//Il test diceva di impostare "people" come endpoint. Per coerenza invece ho impostato "customer".
@RequestMapping("customer")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService service;

	@Autowired
	private FlightService flightService;

	@PostMapping("/{flightId}")
	public ResponseEntity<Customer> createCustomer(@PathVariable("flightId") String flightId,
			@RequestBody CustomerDto dto) {
		LOGGER.info("Creating a new customer...");
		Customer customer = service.save(dto);
		flightService.addPassenger(flightId, customer);
		LOGGER.info("Customer created with id: " + customer.getId());
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") String customerId) {
		LOGGER.info("Getting customer with id: " + customerId);
		Customer customer = service.getById(customerId);
		LOGGER.info("Customer found");
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Customer>> getAllCustomer() {
		LOGGER.info("Getting all customers");
		List<Customer> customers = service.getAll();
		LOGGER.info("Customers found");
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@DeleteMapping("/{flightId}/{customerId}")
	public HttpStatus deleteCustomer(@PathVariable("flightId") String flightId,
			@PathVariable("customerId") String customerId) {
		LOGGER.info("Deleting customer with id: " + customerId);
		flightService.removePassenger(flightId, customerId);
		LOGGER.info("Customer successfully deleted");
		return HttpStatus.OK;
	}

}
