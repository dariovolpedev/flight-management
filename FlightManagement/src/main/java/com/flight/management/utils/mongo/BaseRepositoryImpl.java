package com.flight.management.utils.mongo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import com.flight.management.entity.base.AbstractBaseEntity;

public class BaseRepositoryImpl<T, I> extends SimpleMongoRepository<T, I> implements BaseRepository<T, I> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepositoryImpl.class);

	private MongoOperations mongoOperations;
	private MongoEntityInformation<T, I> entityInformation;

	
	public BaseRepositoryImpl(final MongoEntityInformation<T, I> metadata, final MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		this.entityInformation = metadata;
		this.mongoOperations = mongoOperations;
	}

	@Override
	public T update(T entity) {
		if (entity instanceof AbstractBaseEntity) {
			AbstractBaseEntity model = (AbstractBaseEntity) entity;
			model.setDateModified(new Date());
		} else {
			LOGGER.error("Entity is not of type " + AbstractBaseEntity.class);
		}
		return mongoOperations.save(entity, entityInformation.getCollectionName());
	}

}
