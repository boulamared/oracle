/**
 * 
 */
package com.boulamared.jpython.interpreter;

import java.io.IOException;

/**
 * @author BOULAMMO
 *
 */
public abstract class AbstractExpression {

	public abstract String interpret(InterpreterContext context) throws IOException;

}
