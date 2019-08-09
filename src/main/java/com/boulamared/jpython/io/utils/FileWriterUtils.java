package com.boulamared.jpython.io.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * <h1>FileWriterUtils</h1>
 * <p>
 * This interface is an abstraction for the File operations
 * </p>
 * @author BOULAMMO
 *
 */
public class FileWriterUtils {

	public static void write(File file, String data, String encoding) throws IOException {
		FileUtils.write(file, data,encoding);
	}

	public static File createTempFile(String name, String suffix) throws IOException {
		return File.createTempFile(name, "." + suffix);
	}

	public static boolean delete(File file) {
		return FileUtils.deleteQuietly(file);
	}

}
