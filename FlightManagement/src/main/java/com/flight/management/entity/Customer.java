package com.flight.management.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.flight.management.entity.base.AbstractCustomer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("Customer")
public class Customer extends AbstractCustomer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5408981334026770459L;

}
