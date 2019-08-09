package com.boulamared.jpython.event;

import org.springframework.stereotype.Component;

import com.boulamared.jpython.config.Constants;

/**
 * <h1>Python Separator Event</h1>
 * This is an extension of the Event model, used to add separation between events in the eventStore
 * @author BOULAMMO
 * @version 1.0
 */
@Component
public class PythonSeparatorEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return PythonSeparatorEvent
	 */
	public PythonSeparatorEvent() {
		super(Constants.PYTHON_SEPARATOR);
	}

	/**
	 * @param payload
	 * @return PythonSeparatorEvent
	 */
	public PythonSeparatorEvent(String payload) {
		super(payload);
	}

}
