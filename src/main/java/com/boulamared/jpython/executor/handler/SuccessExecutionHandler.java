package com.boulamared.jpython.executor.handler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.stereotype.Service;

import com.boulamared.jpython.executor.ExecutionResult;

/**
 * <h1>ErrorExecutionHandler</h1>
 * <p>
 * This is an implementation of the IExecutionHandler, used to manage the sucess returned from the Execution
 * </p>
 * @author BOULAMMO
 *
 */
@Service
public class SuccessExecutionHandler implements IExecutionHandler {

	/**
	 * This is the method to extract the success result from the output file(the execution result)
	 */
	@Override
	public void handle(ExecutionResult result, ByteArrayOutputStream out) throws IOException {
		Reader reader = new InputStreamReader(new ByteArrayInputStream(out.toByteArray()));
		BufferedReader br = new BufferedReader(reader);
		String temp = null;
		while ((temp = br.readLine()) != null) {
			result.getSuccess().add(temp);
		}
	}
}
