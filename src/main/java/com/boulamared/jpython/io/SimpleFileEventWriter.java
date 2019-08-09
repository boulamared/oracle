package com.boulamared.jpython.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.boulamared.jpython.event.IEventStore;
import com.boulamared.jpython.io.utils.FileWriterUtils;

/**
 * <h1>SimpleFileEventWriter</h1>
 * <p>
 * This is an implementation of the IEventWriter interface, 
 * 
 * @author BOULAMMO
 *
 */
public class SimpleFileEventWriter implements IEventWriter {

	private String extension = "inter";

	/**
	 * @param extension
	 */
	public SimpleFileEventWriter(String extension) {
		this.extension = extension;
	}
	/**
	 * This method is used to write the
	 * events in the eventStore to a tempFile that creates
	 * @return tempFile
	 * @param eventStore, encoding
	 * 
	 */
	@Override
	public File write(IEventStore eventStore, String encoding) throws IOException {
		StringBuilder builder = new StringBuilder();
		eventStore.getAll().stream().forEach(event -> {
			builder.append(System.getProperty("line.separator"));
			builder.append(event.getPayload());
			builder.append(System.getProperty("line.separator"));
		});
		File script = FileWriterUtils.createTempFile("script." + System.currentTimeMillis(), this.extension);
		FileWriterUtils.write(script, builder.toString(), encoding);
		return script;
	}

	/**
	 * This method takes the eventStore, adds the encoding then calls the write method to write in the tempFile
	 * @return file
	 * @param eventStore
	 */
	@Override
	public File write(IEventStore eventStore) throws IOException {
		return this.write(eventStore, StandardCharsets.UTF_8.name());
	}

}
