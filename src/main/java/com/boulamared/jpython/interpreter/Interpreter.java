package com.boulamared.jpython.interpreter;

import java.io.IOException;

import com.boulamared.jpython.event.IEventStore;

public interface Interpreter {

	String run(IEventStore eventStore) throws IOException;

}
