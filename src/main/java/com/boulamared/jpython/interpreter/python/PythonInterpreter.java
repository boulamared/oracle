package com.boulamared.jpython.interpreter.python;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.event.IEventStore;
import com.boulamared.jpython.executor.ExecutionResult;
import com.boulamared.jpython.executor.SimpleProcessExecutor;
import com.boulamared.jpython.interpreter.Interpreter;
import com.boulamared.jpython.io.SimpleFileEventWriter;

/**
 * <h1>Python Interpreter
 * <h1>
 * <p>
 * This class is the implementation of the python interpretation logic,
 * implements Interpreter interface
 * </p>
 * 
 * @author BOULAMMO
 * @version 1.0
 * @since 06/08/2019
 */

@Service
public class PythonInterpreter implements Interpreter {

	public PythonInterpreter() {
	}

	/**
	 * This method is where the interpreter executes the code sent by the client
	 * for the python interpretation
	 * 
	 * @param IEventStore
	 * @return String
	 */
	@Override
	public String run(IEventStore eventStore) throws IOException {
		SimpleFileEventWriter eventWriter = new SimpleFileEventWriter(Constants.PYTHON_EXTENSION);
		File file = eventWriter.write(eventStore);
		SimpleProcessExecutor executor = new SimpleProcessExecutor().set(Constants.TIMEOUT_ONE_MINUTE);
		ExecutionResult executionResult = executor.run("python " + file.getAbsolutePath());
		if (executionResult.getExitValue() != 0) {
			eventStore.removeLast();
		}

		return prepare(executionResult);
	}

	/**
	 * This method is used to get the result based on the exitValue
	 * 
	 * @param executionResult
	 * @return String
	 */
	private String prepare(ExecutionResult executionResult) {
		String result = "";
		if (executionResult.getExitValue() == 0) {
			result = executionResult.getLastSuccessResult();
		} else if (executionResult.getExitValue() == -2) {
			result = "Timeout execution has been detected...";
		} else {
			result = executionResult.getLastErrorResult();
		}
		return result;
	}
}
