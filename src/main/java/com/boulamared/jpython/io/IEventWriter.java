package com.boulamared.jpython.io;

import java.io.File;
import java.io.IOException;

import com.boulamared.jpython.event.IEventStore;

/**
 * <h1>IEventWriter</h1>
 * <p>
 * This is an abstraction for the EventWriter
 * </p>
 * @author BOULAMMO
 *
 */
public interface IEventWriter {

	public File write(IEventStore eventStore, String encoding) throws IOException;

	public File write(IEventStore eventStore) throws IOException;

}
