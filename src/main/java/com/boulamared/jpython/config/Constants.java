package com.boulamared.jpython.config;

/**
 * <h1>Constants</h1>
 * <p>
 * This is a public interface to hold the Constants used in the project
 * </p>
 * 
 * @author BOULAMMO
 *
 */

public interface Constants {

	/**
	 * End-point variables
	 */

	final String EXECUTE_END_POINT_PATH = "/execute";
	final String PING_END_POINT_PATH = "/ping";
	final String CONTEXT_IDENTIFIER = "context";
	final String HTTP_RESPONSE_OK = "OK";

	/**
	 * Interpreter exit values
	 */
	final int DEFAUT_EXIT_VALUE = Integer.valueOf(-1);
	final int SUCCESS_EXIT_VALUE = Integer.valueOf(0);
	final int ERROR_EXIT_VALUE = Integer.valueOf(-1);
	final int TIMEOUT_EXIT_VALUE = Integer.valueOf(-2);

	/**
	 * timeouts default values
	 */
	final long TIMEOUT_ONE_SECOND = 1000;
	final long TIMEOUT_ONE_MINUTE = 60 * TIMEOUT_ONE_SECOND;
	final long TIMEOUT_FIVE_MINUTES = 5 * TIMEOUT_ONE_MINUTE;
	final long TIMEOUT_TEN_MINUTES = 10 * TIMEOUT_ONE_MINUTE;

	/**
	 * Interpreters constants
	 */
	final String PYTHON_EXTENSION = "py";
	final String PYTHON_KEYWORD = "python";
	final String PYTHON_SEPARATOR = "print('')";
	/**
	 * Error Messages
	 */
	final String PYTHON_INTERPRETER_SYNTAX_ERROR = "Python interpreter syntax is incorrect";
	final String PYTHON_INTERPRETER_TIMEOUT_ERROR = "Timeout execution has been detected...";
	final String INTERPRETER_SYNTAX_ERROR = "Interpreter syntax error, please verify your last statement";
	final String INTERPRETER_NOT_FOUND = "Interpreter not found";
	final String INTERPRETER_SYNTAX_REGEX = "%[a-zA-Z]*\\s[\\d\\D]*";

}
