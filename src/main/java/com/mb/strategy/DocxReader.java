package com.mb.strategy;

import com.mb.extractor.Extractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Class which provides algorithm for reading DOCX files
 * 
 * @author Milan Brankovic
 *
 */
public class DocxReader implements ReaderStrategy {

	public static final String DOCX_FILE_TYPE_EXTENSION = ".docx";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mb.strategy.ReaderStrategy#readFile(java.lang.String)
	 */
	@Override
	public List<String> readFile(final String fileName) {
		final List<String> movieNames = new ArrayList<>();
		if (fileName == null || fileName.trim().isEmpty()) {
			return movieNames;
		}

		if (!fileName.endsWith(getFileType())) {
			return movieNames;
		}

		final Path path = FileSystems.getDefault().getPath(fileName);
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

	@Override
	public String getFileType() {
		return DOCX_FILE_TYPE_EXTENSION;
	}
}
