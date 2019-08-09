package com.boulamared.jpython.executor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <h1>IProcessorExecutor</h1>
 * <p>
 * This is an interface to manage the executors
 * </p>
 * @author BOULAMMO
 *
 */

public interface IProcessExecutor {
	
	public IProcessExecutor set( long timeout);

	public ExecutionResult run(String command) throws IOException;

	ExecutionResult buildExecutionResult(ByteArrayOutputStream stdout, ByteArrayOutputStream stdError, int exitValue) throws IOException;
}
