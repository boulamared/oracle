package com.boulamared.jpython.web.error;

/**
 * <h1>Python Interpreter Wrong Syntax Exception</h1> This is a custom
 * exception, used to catch the errors in the statements sent by the client. the
 * syntax should be <b><%InterpreterName><whitespace><pythonStatement></b>
 * 
 * @author BOULAMMO
 * @version 1.0
 * @since 08/08/2019
 */
public class PythonInterpreterWrongSyntaxException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PythonInterpreterWrongSyntaxException(String errorMessage) {
		super(errorMessage);
	}

}
