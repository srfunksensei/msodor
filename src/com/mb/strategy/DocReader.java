package com.mb.strategy;

import com.mb.extractor.Extractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * Class which provides algorithm for reading DOC files
 *
 * @author Milan Brankovic
 *
 */
public class DocReader implements ReaderStrategy {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mb.strategy.ReaderStrategy#readFile(java.lang.String)
	 */
	@Override
	public List<String> readFile(final String fileName) {
		final Path path = FileSystems.getDefault().getPath(fileName);

		final List<String> movieNames = new ArrayList<>();
		try (final HWPFDocument document = new HWPFDocument(Files.newInputStream(path));
				final WordExtractor extractor = new WordExtractor(document)) {
			int year = -1;
			for (final String para : extractor.getParagraphText()) {
				if (para != null) {
					try {
						year = Extractor.extractMovieYear(para);
					} catch (Exception ex) {
						Optional<String> movie = Extractor.extractMovieNameWithYear(para, year);
						movie.ifPresent(movieNames::add);
					}
				}
			}
		} catch (Exception exep) {
			exep.printStackTrace();
		}

		return movieNames;
	}

}
