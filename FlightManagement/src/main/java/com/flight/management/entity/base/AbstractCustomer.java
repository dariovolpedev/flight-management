package com.flight.management.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCustomer extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8845419535318321918L;

	private String name;

	private String lastName;

	private int age;
}
