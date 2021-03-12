package com.mb.demo;

import com.mb.strategy.DocReader;
import com.mb.strategy.DocxReader;
import com.mb.strategy.ReaderContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Milan Brankovic
 */
public class ReadWordFileDemo {

    public static void main(final String[] args) {
        if (args.length == 0) {
            System.out.println("No file name provided");
            System.exit(0);
        }

        final String inputFileName = args[0];

        final boolean isDoc = inputFileName.endsWith(DocReader.DOC_FILE_TYPE_EXTENSION),
                isDocx = inputFileName.endsWith(DocxReader.DOCX_FILE_TYPE_EXTENSION);

        final ReaderContext ctx = new ReaderContext();

        if (isDoc) {
            ctx.setStrategy(new DocReader());
        } else if (isDocx) {
            ctx.setStrategy(new DocxReader());
        } else {
            System.out.println("Unsuported file format");
            System.exit(0);
        }

        try {
            final List<String> movieNames = ctx.readFile(inputFileName);

            final String outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf('/') + 1) + "output.txt";
            final Path file = FileSystems.getDefault().getPath(outputFileName);
            Files.write(file, movieNames, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Could not write to output file");
            System.exit(0);
        }
    }
}
