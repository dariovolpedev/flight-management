package com.flight.management.entity.base;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public abstract class AbstractBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8438255226187295728L;

	@Id
	private String id;

	@JsonIgnore
	private Date dateCreated;

	@JsonIgnore
	private Date dateModified;

}
