package com.boulamared.jpython.interpreter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.web.error.InterpreterNotFoundException;
/**
 * <h1>InterpreterFactory</h1>
 * <p>
 * This class is used to get an implementation of the AbstrationExpression(With represents an interpreter),
 * using the factory Design Pattern
 * </p>
 * @author BOULAMMO
 *
 */
@Service
public class InterpreterFactory  {
	
	private  Map<String, AbstractExpression> expressions = new HashMap<String ,AbstractExpression>();
	
	/**
	 * Constructor used to init the map of the interpreters
	 * @return 
	 * @param
	 */
	public InterpreterFactory() {
		this.expressions.put(Constants.PYTHON_KEYWORD, new PythonTerminalExpression());
	}
	
	/**
	 * this method returns an interpreter using the name supplied by the user
	 * @param interpreter
	 * @return interpreter
	 */
	public AbstractExpression getInterpreter(String interpreter) {
		if(!this.expressions.containsKey(interpreter))
			throw new InterpreterNotFoundException(Constants.INTERPRETER_NOT_FOUND);
		return this.expressions.get(interpreter);
	}
	
	/**
	 * check if there an an implementation of an interpreter
	 * @param interpreter
	 * @return boolean
	 */
	public boolean isSupportedInterpreter(String interpreter) {
		return this.expressions.containsKey(interpreter);
	}

	/**
	 * @return the expressions
	 */
	public Map<String, AbstractExpression> getExpressions() {
		return expressions;
	}

	/**
	 * @param expressions the expressions to set
	 */
	public void setExpressions(Map<String, AbstractExpression> expressions) {
		this.expressions = expressions;
	}
	
	
	
}
