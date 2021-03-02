package com.mb.strategy;

import java.util.List;

/**
 * 
 * Interface for reading Microsoft files.
 * 
 * @author Milan Brankovic
 *
 */
public interface ReaderStrategy {
	
	/**
	 * Reads file with specific processing.
	 * 
	 * @param fileName name of the file which will be processed
	 */
	List<String> readFile(final String fileName);

	/**
	 * Returns file type which will be used for reading
	 *
	 * @return
	 */
	String getFileType();
}
