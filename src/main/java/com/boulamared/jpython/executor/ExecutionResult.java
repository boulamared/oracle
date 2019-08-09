package com.boulamared.jpython.executor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.boulamared.jpython.config.Constants;

/**
 * <h1>Execution Result</h1>
 * <p>
 * This is a model to hold the interpreter results, implements Serializable
 * </p>
 * @author BOULAMMO
 *
 */

@Component
public class ExecutionResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int exitValue = Constants.DEFAUT_EXIT_VALUE;
	private List<String> success = new ArrayList<String>();
	private List<String> errors = new ArrayList<String>();

	/**
	 * This is the default constructor
	 */
	public ExecutionResult() {
		super();
	}

	/**
	 * Adds a result with exitValue = 0 to the success messages List
	 * 
	 * @param result
	 */
	public void addSuccessResult(String result) {
		this.success.add(result);
	}

	/**
	 * Get the last success result message
	 * if success result list is empty, it will return an empty string
	 * @return String
	 */
	public String getLastSuccessResult() {
		return (this.success.size()>0)?this.success.get(this.success.size() - 1):"";
	}

	/**
	 * Adds a result with exitValue = -1 to the errors messages List
	 * 
	 * @param result
	 */
	public void addErrorResult(String error) {
		this.errors.add(error);
	}

	/**
	 * Get the last errors result message
	 * if errors result list is empty, it will return an empty string
	 * @return String
	 */
	public String getLastErrorResult() {
		return (this.errors.size()>0)?this.errors.get(this.errors.size() - 1):"";
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the exitValue
	 */
	public int getExitValue() {
		return exitValue;
	}

	/**
	 * @param exitValue
	 *            the exitValue to set
	 */
	public void setExitValue(int exitValue) {
		this.exitValue = exitValue;
	}

	/**
	 * @return the success
	 */
	public List<String> getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(List<String> success) {
		this.success = success;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
