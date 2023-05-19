package com.flight.management.dto;

import com.flight.management.entity.base.AbstractFlight;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlightDto extends AbstractFlight{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1472650807540282714L;

}
