package com.mb.strategy;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Milan Brankovic
 */
public class DocxReaderTest {

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
}
