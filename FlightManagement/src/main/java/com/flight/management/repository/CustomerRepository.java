package com.flight.management.repository;

import org.springframework.stereotype.Repository;

import com.flight.management.entity.Customer;
import com.flight.management.utils.mongo.BaseRepository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, String>{

}
