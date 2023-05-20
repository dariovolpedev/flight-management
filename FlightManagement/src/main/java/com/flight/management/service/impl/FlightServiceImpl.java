package com.flight.management.service.impl;

import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.management.dto.FlightDto;
import com.flight.management.entity.Customer;
import com.flight.management.entity.Flight;
import com.flight.management.exception.ElementNotFoundException;
import com.flight.management.exception.MaxNumberExcedeedException;
import com.flight.management.mapper.FlightMapper;
import com.flight.management.mapper.FlightMapperImpl;
import com.flight.management.repository.FlightRepository;
import com.flight.management.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository repository;

	private FlightMapper mapper = new FlightMapperImpl();

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
				flight.getWaitingPassengers().add(customer);
			}
		} else {
			throw new ElementNotFoundException("Flight with id: " + flightId + " wasn't find");
		}
		return flight;
	}

	@Override
	public Flight removePassenger(String flightId, Customer customer) {
		Flight flight = repository.findById(flightId).orElse(null);
		if (flight != null) {
			flight.getPassengers().remove(customer);
			Queue<Customer> waitingList = flight.getWaitingPassengers();
			if (waitingList.size() > 0) {
				Customer cust = waitingList.poll();
				flight.getPassengers().add(cust);
			}
		} else {
			throw new ElementNotFoundException("Flight with id: " + flightId + " wasn't find");
		}
		return flight;
	}

}
