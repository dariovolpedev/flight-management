package com.flight.management.mapper;

import com.flight.management.dto.CustomerDto;
import com.flight.management.entity.Customer;

public class CustomerMapperImpl implements CustomerMapper {

	@Override
	public Customer fromDto(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setAge(dto.getAge());
		customer.setLastName(dto.getLastName());
		customer.setDateCreated(dto.getDateCreated());
		customer.setDateModified(dto.getDateModified());
		customer.setId(dto.getId());
		return customer;
	}

	@Override
	public CustomerDto fromEntity(Customer entity) {
		CustomerDto dto = new CustomerDto();
		dto.setAge(entity.getAge());
		dto.setLastName(entity.getLastName());
		dto.setName(entity.getName());
		dto.setDateCreated(entity.getDateCreated());
		dto.setDateModified(entity.getDateModified());
		return dto;

	}

}
