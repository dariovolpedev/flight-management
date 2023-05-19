package com.flight.management.mapper;

import java.util.Date;

import com.flight.management.dto.FlightDto;
import com.flight.management.entity.Flight;

public class FlightMapperImpl implements FlightMapper {

	@Override
	public Flight fromDto(FlightDto dto) {
		Flight flight = new Flight(dto.getMaxNumber());
		flight.setPassengers(dto.getPassengers());
		flight.setWaitingPassengers(dto.getWaitingPassengers());
		flight.setDateCreated(new Date());
		return flight;
	}

	@Override
	public FlightDto fromEntity(Flight entity) {
		FlightDto dto = new FlightDto();
		dto.setMaxNumber(entity.getMaxNumber());
		dto.setPassengers(entity.getPassengers());
		dto.setWaitingPassengers(entity.getWaitingPassengers());
		return dto;
	}

}
