package com.flight.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.management.dto.CustomerDto;
import com.flight.management.entity.Customer;
import com.flight.management.mapper.CustomerMapper;
import com.flight.management.mapper.CustomerMapperImpl;
import com.flight.management.repository.CustomerRepository;
import com.flight.management.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	private CustomerMapper mapper = new CustomerMapperImpl();

	@Override
	public List<Customer> getAll() {
		return repository.findAll();
	}

	@Override
	public Customer save(CustomerDto dto) {
		return repository.insert(mapper.fromDto(dto));
	}

	@Override
	public Customer update(CustomerDto dto) {
		return repository.save(mapper.fromDto(dto));
	}

	@Override
	public Customer getById(String id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);

	}

}
