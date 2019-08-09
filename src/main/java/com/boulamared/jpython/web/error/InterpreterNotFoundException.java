package com.boulamared.jpython.web.error;
/**
 * <h1>InterpreterNotFoundException</h1>
 * <p>
 * This is a custom Exception made to handle the error for the none implemented interpreters
 * </p>
 * 
 * @author BOULAMMO
 *
 */
public class InterpreterNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor 
	 * @param errorMessage
	 * @return InterpreterNotFoundException
	 */
	public InterpreterNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
