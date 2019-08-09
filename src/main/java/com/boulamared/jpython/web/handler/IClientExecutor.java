package com.boulamared.jpython.web.handler;

import com.boulamared.jpython.interpreter.InterpreterContext;

/**
 * <h1>IClientExecutor</h1>
 * This is an abstraction for the client to call
 * @author BOULAMMO
 *
 */
public interface IClientExecutor {

	public String execute(InterpreterContext context) throws Exception;
}
