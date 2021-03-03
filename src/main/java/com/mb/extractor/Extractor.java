package com.mb.extractor;

import java.util.Optional;

/**
 * 
 * Utility class which provides method for extracting specific
 * movie information
 * 
 * @author Milan Brankovic
 *
 */
public class Extractor {

	public static final int YEAR_LENGTH = 4;
	
	/**
	 * Returns movie year
	 * 
	 * @param paragraph String from which movie year will be extracted
	 * @return returns year extracted from paragraph
	 */
	public static int extractMovieYear(final String paragraph) {
		return Integer.parseInt(paragraph.substring(0, YEAR_LENGTH));
	}
	
	/**
	 * Returns movie name and year in following format [name (year)].
	 * 
	 * @param paragraph String from which movie name will be extracted
	 * @param year movie year
	 * @return movie name and year if name can be extracted, otherwise empty optional 
	 */
	public static Optional<String> extractMovieNameWithYear(final String paragraph, final int year){
		final int slashIndex = paragraph.indexOf('/');
		if (slashIndex != -1) {
			final String name = paragraph.substring(0, slashIndex).trim();
			return Optional.of(name + " (" + year + ")");
		}
		
		return Optional.empty();
	}
}
