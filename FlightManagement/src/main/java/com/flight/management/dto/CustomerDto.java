package com.flight.management.dto;

import com.flight.management.entity.base.AbstractCustomer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends AbstractCustomer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3364354054452902307L;

}
