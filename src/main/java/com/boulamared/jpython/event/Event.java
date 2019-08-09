package com.boulamared.jpython.event;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

/**
 * <h1>Event</h1>
 * <p>
 * This is a model class to hold the event information
 * </p>
 * 
 * @author BOULAMMO
 * @version 1.0
 * @since 06/08/2019
 */

@Component
public class Event implements Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Long createdAt;
	private String payload;	

	/**
	 * Event default constructor, initializes the createdAt and id with default values
	 */
	public Event() {
		super();
		this.createdAt = System.currentTimeMillis();
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * @param payload
	 */
	public Event(String payload) {
		this.payload = payload;
		this.createdAt = System.currentTimeMillis();
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the createdAt
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * @param payload
	 *            the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}

}
