package com.boulamared.jpython.executor.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.boulamared.jpython.executor.ExecutionResult;

/**
 * <h1>IExecutionHandler</h1>
 * <p>
 * This is an interface used to represent the handlers
 * </p>
 * @author BOULAMMO
 *
 */
public interface IExecutionHandler {
	
	void handle(ExecutionResult result, ByteArrayOutputStream out) throws IOException;

}
