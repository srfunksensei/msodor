package com.mb.strategy;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.mb.extractor.Extractor;

/**
 * 
 * Class which provides algorithm for reading DOCX files
 * 
 * @author Milan Brankovic
 *
 */
public class DocxReader implements ReaderStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mb.strategy.ReaderStrategy#readFile(java.lang.String)
	 */
	@Override
	public List<String> readFile(final String fileName) {
		final Path path = FileSystems.getDefault().getPath(fileName);

		final List<String> movieNames = new ArrayList<>();
		try (final XWPFDocument document = new XWPFDocument(Files.newInputStream(path))) {
			int year = -1;
			for (final XWPFParagraph para : document.getParagraphs()) {
				if (para != null) {
					try {
						year = Extractor.extractMovieYear(para.getText());
					} catch (Exception ex) {
						Optional<String> movie = Extractor.extractMovieNameWithYear(para.getText(), year);
						movie.ifPresent(movieNames::add);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return movieNames;
	}
}
