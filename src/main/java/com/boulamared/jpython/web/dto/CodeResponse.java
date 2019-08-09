package com.boulamared.jpython.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;
/**
 * <h1>CodeResponse</h1>
 * <p>
 * This is a DTO used to hold the request made for the client
 * </p>
 * 
 * @author BOULAMMO
 *
 */
@Component
public class CodeResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * @param result
	 */
	public CodeResponse() {
	}

	/**
	 * @param result
	 */
	public CodeResponse(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	
	
}
