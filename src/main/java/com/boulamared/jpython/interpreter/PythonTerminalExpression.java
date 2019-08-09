/**
 * 
 */
package com.boulamared.jpython.interpreter;

import java.io.File;
import java.io.IOException;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.event.IEventStore;
import com.boulamared.jpython.executor.ExecutionResult;
import com.boulamared.jpython.executor.SimpleProcessExecutor;
import com.boulamared.jpython.io.SimpleFileEventWriter;

/**
 * <h1>PythonTerminalExpression</h1>
 * <p>
 * This class is an extension of the AbstractExpression class, represents the
 * interpreter
 * </p>
 * 
 * @author BOULAMMO
 *
 */
public class PythonTerminalExpression extends AbstractExpression {

	/**
	 * This method takes a context, and call the ProcessExecutor to interpret
	 * the events using the eventStore then returns a simple result
	 * 
	 * @param context
	 * @return result
	 */
	@Override
	public String interpret(InterpreterContext context) throws IOException {
		IEventStore eventStore = context.getEventStore();
		SimpleFileEventWriter eventWriter = new SimpleFileEventWriter(Constants.PYTHON_EXTENSION);
		File file = eventWriter.write(eventStore);
		SimpleProcessExecutor executor = new SimpleProcessExecutor().set(Constants.TIMEOUT_ONE_MINUTE);
		ExecutionResult executionResult = executor.run("python " + file.getAbsolutePath());
		if (executionResult.getExitValue() != 0) {
			eventStore.removeLast();
		}
		return prepare(eventStore, executionResult);
	}

	/**
	 * this method is used to extract the result from the ExecutionResult, using
	 * the exitValue a result might be a timeout error, an error or success
	 * result
	 * 
	 * @param eventStore
	 * @param executionResult
	 * @return result
	 */
	private String prepare(IEventStore eventStore, ExecutionResult executionResult) {
		String result = "";
		if (executionResult.getExitValue() == 0) {
			result = executionResult.getLastSuccessResult();
		} else if (executionResult.getExitValue() == -2) {
			result = Constants.PYTHON_INTERPRETER_TIMEOUT_ERROR;
		} else {
			result = executionResult.getLastErrorResult();
		}
		return result;
	}

}
