package com.boulamared.jpython.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.interpreter.InterpreterContext;
import com.boulamared.jpython.web.dto.CodeRequest;
import com.boulamared.jpython.web.dto.CodeResponse;
import com.boulamared.jpython.web.handler.ExecutionHandler;

/**
 * <h1>Execution Controller</h1>
 * <p>
 * this is the main end-point controller, contains the methods to interact with
 * the client
 * </p>
 * 
 * @author BOULAMMO
 * @version 1.0
 * @since 08/08/2019
 */
@RestController
public class ExecutionController {

	@Autowired
	private ExecutionHandler executionHandler;

	/**
	 * This is the main method, used to receive the client's code using
	 * CodeRequest wrapper, Using a code wrapper, and return the result using a
	 * CodeResponse
	 * 
	 * @param code
	 * @param httpServletRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@PostMapping(Constants.EXECUTE_END_POINT_PATH)
	public ResponseEntity<?> execute(@RequestBody CodeRequest code, HttpServletRequest httpServletRequest)
			throws Exception {

		// Test if the client has a context inside the session, if not create a
		// new one
		InterpreterContext context = (InterpreterContext) httpServletRequest.getSession()
				.getAttribute(Constants.CONTEXT_IDENTIFIER);

		if (context == null) {
			context = new InterpreterContext();
			httpServletRequest.getSession().setAttribute(Constants.CONTEXT_IDENTIFIER, context);
		}

		// add the current command to the context to run it
		context.addExpression(code.getCode());
		// run the current context and wrap the result inside a CodeResponse
		CodeResponse response = new CodeResponse(this.executionHandler.execute(context));
		return new ResponseEntity<CodeResponse>(response, HttpStatus.OK);
	}

	/**
	 * This is a method to test the status of the web service, return "OK" if
	 * it's all Good
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@GetMapping(Constants.PING_END_POINT_PATH)
	public ResponseEntity<String> ping(HttpServletRequest httpServletRequest) {
		return new ResponseEntity<String>(Constants.HTTP_RESPONSE_OK, HttpStatus.OK);
	}
}
