package com.boulamared.jpython.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.event.Event;
import com.boulamared.jpython.event.PythonSeparatorEvent;
import com.boulamared.jpython.interpreter.InterpreterContext;
import com.boulamared.jpython.interpreter.InterpreterFactory;
import com.boulamared.jpython.web.error.InterpreterNotFoundException;

/**
 * <h1>ExecutionHandler</h1>
 * <p>
 * This is an implementation of IClientExecutor, used to handle the execution
 * for the API client
 * </p>
 * 
 * @author BOULAMMO
 *
 */
@Service
public class ExecutionHandler implements IClientExecutor {

	@Autowired
	private InterpreterFactory factory;

	/**
	 * This method takes a context from the client, verify the statement against
	 * a predefined format, get an interpreter from the factory then run the
	 * comands and get the result using a string to send to the client
	 * 
	 * @return message
	 * @param context
	 */
	@Override
	public String execute(InterpreterContext context) throws Exception {
		if (!this.syntaxVerifier(context.getExpress()))
			return Constants.INTERPRETER_SYNTAX_ERROR;
		else {
			try {
				context.getEventStore().add(new PythonSeparatorEvent())
						.add(new Event(getCodeExecutableFromStatement(context.getExpress())));

				return this.factory.getInterpreter(this.getInterpreterFromStatement(context.getExpress()))
						.interpret(context);
			} catch (InterpreterNotFoundException ex) {
				return Constants.INTERPRETER_NOT_FOUND;
			} catch (Exception ex) {
				return "Error";
			}
		}
	}

	/**
	 * this is private method to verify if the statement sent by the client is valid
	 * @param statement
	 * @return
	 */
	private boolean syntaxVerifier(String statement) {
		return statement.matches(Constants.INTERPRETER_SYNTAX_REGEX);
	}

	/**
	 * This method extracts the interpreter from the expression received
	 * @param statement
	 * @return
	 */
	private String getInterpreterFromStatement(String statement) {
		return statement.split("\\s", 2)[0].substring(1);
	}

	/**
	 * This method extracts the code to run from the expression received
	 * @param statement
	 * @return
	 */
	private String getCodeExecutableFromStatement(String statement) {
		return statement.split("\\s", 2)[1].trim();
	}
}
