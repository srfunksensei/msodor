package com.mb.extractor;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Optional;

/**
 * @author Milan Brankovic
 */
public class ExtractorTest {

    @Test(expected = NumberFormatException.class)
    public void extractMovieYear_withChars() {
        Extractor.extractMovieYear("asdgf");
    }

    @Test
    public void extractMovieYear_lengthHigherThanExpected() {
        final String paragraph = String.join("", Collections.nCopies(Extractor.YEAR_LENGTH + 2, "1"));
        final int result = Extractor.extractMovieYear(paragraph);
        Assert.assertEquals("Expected different length of year", 1111, result);
    }

    @Test
    public void extractMovieYear() {
        final String year = "1987";
        final int result = Extractor.extractMovieYear(year);
        Assert.assertEquals("Expected different year", Integer.parseInt(year), result);
    }

    @Test
    public void extractMovieNameWithYear_emptyParagraph() {
        final String paragraph = "";
        final int year = 1987;
        final Optional<String> result = Extractor.extractMovieNameWithYear(paragraph, year);
        Assert.assertFalse("Expected no result", result.isPresent());
    }

    @Test
    public void extractMovieNameWithYear_paragraphWithoutSlash() {
        final String paragraph = "Valter brani Sarajevo";
        final int year = 1987;
        final Optional<String> result = Extractor.extractMovieNameWithYear(paragraph, year);
        Assert.assertFalse("Expected no result", result.isPresent());
    }

    @Test
    public void extractMovieNameWithYear() {
        final String paragraph = "LISINSKI / pr. Hrvatski slikopis (Croatia film), 1944. – r. Oktavijan Miletić, sc. Milan Katić – k. Oktavijan Miletić, Ivan Zeitlinger – gl. Boris Papandopulo, Vatroslav Lisinski - mt. Branko Marjanović – sgf. Vladimir Žedrinski – kgf. – ul. Branko Špoljar, Lidija Dominković, Veljko Maričić, Srebrenka Jurinac, Tošo Lesić, Hinko Nučić, Tomislav Neralić, Jozo Martinčević, Pero Budak, Janko Rakuša, Mira Župan, August Cilić – dugometražni igrani – 35 mm, cb, 85 minuta";
        final int year = 1987;
        final Optional<String> result = Extractor.extractMovieNameWithYear(paragraph, year);
        Assert.assertTrue("Expected result", result.isPresent());
        Assert.assertEquals("Expected movie name with year", "LISINSKI (1987)", result.get());
    }
}
