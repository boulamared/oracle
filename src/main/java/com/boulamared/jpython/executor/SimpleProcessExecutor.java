package com.boulamared.jpython.executor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.executor.handler.ErrorExecutionHandler;
import com.boulamared.jpython.executor.handler.IExecutionHandler;
import com.boulamared.jpython.executor.handler.SuccessExecutionHandler;

/**
 * <h1>SimpleProcessExecutor</h1>
 * <p>
 * This is the main implementation for the IProcessExecutor
 * </p>
 * 
 * @author BOULAMMO
 *
 */

@Service
public class SimpleProcessExecutor implements IProcessExecutor {

	private IExecutionHandler successExecutionHandler = new SuccessExecutionHandler();
	private IExecutionHandler errorExecutionHandler = new ErrorExecutionHandler();
	private long timeout = Constants.TIMEOUT_ONE_MINUTE;

	@Override
	public SimpleProcessExecutor set(long timeout) {
		this.timeout = timeout;
		return this;
	}

	/**
	 * This is the main method to run the commands using the Executor from the
	 * apache.commons.exec library, it's using an ExecuteWatchdog to set a
	 * timeout for the execution of the command
	 *
	 * @return ExecutionResult
	 * @param command
	 */
	@Override
	public ExecutionResult run(String command) throws IOException {

		CommandLine cmdLine = CommandLine.parse(command);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		ByteArrayOutputStream stderr = new ByteArrayOutputStream();
		PumpStreamHandler psh = new PumpStreamHandler(stdout, stderr);

		psh.setStopTimeout(this.timeout);

		ExecuteWatchdog watchDog = new ExecuteWatchdog(this.timeout);

		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setStreamHandler(psh);
		executor.setWatchdog(watchDog);

		int exitValue = Constants.DEFAUT_EXIT_VALUE;
		try {
			exitValue = executor.execute(cmdLine, Collections.emptyMap());
		} catch (ExecuteException exception) {
			if (watchDog.killedProcess()) {
				exitValue = Constants.TIMEOUT_EXIT_VALUE;
			}
		}

		ExecutionResult executionResult = this.buildExecutionResult(stdout, stderr, exitValue);
		return executionResult;
	}

	/**
	 * This is method is used to handle the success and the errors returned from
	 * the execution
	 * 
	 * @return ExecutionResult
	 * @param stdout,stdErr,exitValue
	 */
	@Override
	public ExecutionResult buildExecutionResult(ByteArrayOutputStream stdout, ByteArrayOutputStream stdErr,
			int exitValue) throws IOException {
		ExecutionResult executionResult = new ExecutionResult();
		this.successExecutionHandler.handle(executionResult, stdout);
		this.errorExecutionHandler.handle(executionResult, stdErr);

		executionResult.setExitValue(exitValue);
		return executionResult;
	}

}
