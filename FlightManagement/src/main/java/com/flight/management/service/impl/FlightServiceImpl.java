package com.flight.management.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.management.dto.FlightDto;
import com.flight.management.dto.FlightInputDto;
import com.flight.management.dto.WaitingCustomer;
import com.flight.management.entity.Customer;
import com.flight.management.entity.Flight;
import com.flight.management.exception.ElementNotFoundException;
import com.flight.management.exception.MaxNumberExcedeedException;
import com.flight.management.mapper.FlightMapper;
import com.flight.management.mapper.FlightMapperImpl;
import com.flight.management.repository.FlightRepository;
import com.flight.management.service.CustomerService;
import com.flight.management.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository repository;

	private FlightMapper mapper = new FlightMapperImpl();

	@Autowired
	private CustomerService customerService;

	@Override
	public List<Flight> getAll() {
		return repository.findAll();
	}

	@Override
	public Flight save(FlightDto dto) {
		if (dto.getPassengers().size() > dto.getMaxNumber()) {
			throw new MaxNumberExcedeedException("Max number excedeed");
		} else {
			return repository.insert(mapper.fromDto(dto));
		}
	}

	@Override
	public Flight update(FlightDto dto) {
		if (dto.getPassengers().size() > dto.getMaxNumber()) {
			throw new MaxNumberExcedeedException("Max number excedeed");
		} else {
			return repository.save(mapper.fromDto(dto));
		}
	}

	@Override
	public Flight getById(String id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);

	}

	@Override
	public Flight addPassenger(String flightId, Customer customer) {
		Flight flight = repository.findById(flightId).orElse(null);
		if (flight != null) {
			if (flight.getPassengers().size() < flight.getMaxNumber()) {
				flight.getPassengers().add(customer);
			} else {
				flight.getWaitingPassengers()
						.add(WaitingCustomer.builder().customerId(customer.getId()).insertDate(new Date()).build());
			}
		} else {
			throw new ElementNotFoundException("Flight with id: " + flightId + " wasn't find");
		}
		return update(mapper.fromEntity(flight));
	}

	@Override
	public Flight removePassenger(String flightId, String customerId) {
		Flight flight = repository.findById(flightId).orElse(null);
		Customer customer = customerService.getById(customerId);
		if (flight != null && customer != null) {
			if (flight.getPassengers().stream().anyMatch(passenger -> passenger.getId().equals(customer.getId()))) {
				flight.getPassengers().remove(customer);
				customerService.delete(customerId);
			} else if (flight.getWaitingPassengers().stream()
					.anyMatch(wait -> wait.getCustomerId().equals(customer.getId()))) {
				WaitingCustomer cust = flight.getWaitingPassengers().stream()
						.filter(wait -> wait.getCustomerId().equals(customer.getId())).collect(Collectors.toList())
						.get(0);
				flight.getWaitingPassengers().remove(cust);
				customerService.delete(customerId);
			}
			List<WaitingCustomer> waitingList = flight.getWaitingPassengers();
			if (waitingList.size() > 0 && flight.getPassengers().size() < flight.getMaxNumber()) {
				WaitingCustomer waitingCust = waitingList.stream()
						.sorted(Comparator.comparing(WaitingCustomer::getInsertDate)).findFirst().orElse(null);
				Customer cust = customerService.getById(waitingCust.getCustomerId());
				flight.getPassengers().add(cust);
			}
		} else if (customer == null) {
			if (flight.getWaitingPassengers().stream().anyMatch(wait -> wait.getCustomerId().equals(customerId))) {
				WaitingCustomer cust = flight.getWaitingPassengers().stream()
						.filter(wait -> wait.getCustomerId().equals(customerId)).collect(Collectors.toList()).get(0);
				flight.getWaitingPassengers().remove(cust);
				customerService.delete(customerId);
			} else {
				throw new NullPointerException("Customer doesn't exist");
			}
		} else {
			throw new ElementNotFoundException("Flight with id: " + flightId + " wasn't find");
		}
		return update(mapper.fromEntity(flight));
	}

	@Override
	public Flight createFlight(FlightInputDto dto) {
		Flight flight = new Flight(dto.getMaxPassengers());
		flight.setDateCreated(new Date());
		flight.setDateModified(new Date());
		flight.setPassengers(new ArrayList<>());
		flight.setWaitingPassengers(new ArrayList<>());
		return repository.insert(flight);
	}

}
