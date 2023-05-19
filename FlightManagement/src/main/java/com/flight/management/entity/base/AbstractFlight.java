package com.flight.management.entity.base;

import java.util.List;
import java.util.Queue;

import com.flight.management.entity.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractFlight extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6633178423368045822L;

	private List<Customer> passengers;

	private Queue<Customer> waitingPassengers;

	private int maxNumber;

}
