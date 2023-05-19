package com.flight.management.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.flight.management.entity.base.AbstractFlight;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document("Flight")
public class Flight extends AbstractFlight {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7405936387906469204L;

	public Flight(int maxNumber) {
		setMaxNumber(maxNumber);
	}

}
