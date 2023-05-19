package com.flight.management.mapper;

public interface BaseMapper<T, I> {
	
	I fromEntity(T dto);

	T fromDto(I entity);
}
