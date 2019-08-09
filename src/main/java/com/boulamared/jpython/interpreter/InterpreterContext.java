/**
 * 
 */
package com.boulamared.jpython.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.boulamared.jpython.event.IEventStore;
import com.boulamared.jpython.event.InMemoryEventStore;

/**
 * <h1>InterpreterContext</h1>
 * <p>
 * This is a class to hold all the information related to the client, the
 * results list, errors list, the eventStore and the current expression to
 * execute
 * </p>
 * 
 * @author BOULAMMO
 *
 */
@Component
public class InterpreterContext {

	private HashMap<String, Object> context = new HashMap<String, Object>();

	private final String EXPRESSION_KEY = "EXPRESSION_KEY";
	private final String RESULT_KEY = "RESULT_KEY";
	private final String ERROR_KEY = "ERROR_KEY";
	private final String EXIT_CODE_KEY = "EXIT_CODE_KEY";
	private final String EVENT_STORE_KEY = "EVENT_STORE_KEY";
	private final String RESULTS_KEY = "RESULTS_KEY";
	private final String ERRORS_KEY = "ERRORS_KEY";

	private List<String> success = new ArrayList<String>();
	private List<String> errors = new ArrayList<String>();
	private IEventStore eventStore = new InMemoryEventStore();

	/**
	 * Constructor for InterpreterContext, used to initialize the Results list, the errors list and the eventStore
	 */
	public InterpreterContext() {
		this.context.put(RESULTS_KEY, this.success);
		this.context.put(ERRORS_KEY, this.errors);
		this.context.put(EVENT_STORE_KEY, this.eventStore);
	}

	public void addExpression(String expression) {
		this.context.put(EXPRESSION_KEY, expression);
	}

	public String getExpress() {
		return (String) this.context.get(EXPRESSION_KEY);
	}

	public void addResult(String result) {
		this.success.add(result);
		this.context.put(RESULT_KEY, result);
	}

	public String getResult() {
		return (String) this.context.get(RESULT_KEY);
	}

	public void addError(String error) {
		this.errors.add(error);
		this.context.put(ERROR_KEY, error);
	}

	public String getError() {
		return (String) this.context.get(ERROR_KEY);
	}

	public void addExitCode(int exitCode) {
		this.context.put(EXIT_CODE_KEY, exitCode);
	}

	public int getExitCode() {
		return (int) this.context.get(EXIT_CODE_KEY);
	}

	public void addEventStore(IEventStore eventStore) {
		this.context.put(EVENT_STORE_KEY, eventStore);
	}

	public IEventStore getEventStore() {
		return (IEventStore) this.context.get(EVENT_STORE_KEY);
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllResults() {
		return (List<String>) this.context.get(RESULTS_KEY);
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllErrors() {
		return (List<String>) this.context.get(ERRORS_KEY);
	}

}
