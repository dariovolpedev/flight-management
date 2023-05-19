package com.flight.management.utils.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, I> extends MongoRepository<T, I> {

	T update(T entity);

}
