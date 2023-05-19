package com.flight.management.service;

import java.util.List;

public interface BaseService<T, I> {
	List<T> getAll();

	T save(I dto);

	T update(I dto);

	T getById(String id);
	
	void delete(String id);
}
