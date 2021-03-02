package com.mb.strategy;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * @author Milan Brankovic
 */
public class DocxReaderTest {

    private static final String DOC_BLANK = "test_blank.docx";
    private static final String DOC_WITH_DATA = "test_data.docx";

    private final DocxReader underTest = new DocxReader();

    @Test
    public void readFile_blankFileName() {
        final List<String> resultNull = underTest.readFile(null);
        Assert.assertTrue("Expected no data for null as file path", resultNull.isEmpty());

        final List<String> resultBlankString = underTest.readFile("    ");
        Assert.assertTrue("Expected no data for blank file path", resultBlankString.isEmpty());
    }

    @Test
    public void readFile_noSuchDocumentException() {
        final List<String> result = underTest.readFile("non-existing.docx");
        Assert.assertTrue("Expected no data for non existing file", result.isEmpty());
    }

    @Test
    public void readFile_wrongType() {
        final List<String> result = underTest.readFile("wrong_type.txt");
        Assert.assertTrue("Expected no data for file with wrong type", result.isEmpty());
    }

    @Test
    public void readFile_noData() throws URISyntaxException {
        final File file = getFile(DOC_BLANK);

        final List<String> result = underTest.readFile(file.getAbsolutePath());
        Assert.assertTrue("Expected no data for empty file", result.isEmpty());
    }

    @Test
    public void readFile_withData() throws URISyntaxException {
        final File file = getFile(DOC_WITH_DATA);

        final List<String> result = underTest.readFile(file.getAbsolutePath());
        Assert.assertFalse("Expected data for non empty file", result.isEmpty());
    }

    private File getFile(final String fileName) throws URISyntaxException {
        final URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }

        return new File(resource.toURI());
    }
}
