package com.mb.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.mb.strategy.DocReader;
import com.mb.strategy.DocxReader;
import com.mb.strategy.ReaderContext;

/**
 * 
 * @author Milan Brankovic
 *
 */
public class ReadWordFileDemo {

	final private static String DOC_EXTENSION = ".doc";
	final private static String DOCX_EXTENSION = ".docx";

	public static void main(final String[] args) {
		if(args.length == 0){
			System.out.println("No file name provided");
			System.exit(0);
		} 
		
		final String inputFileName = args[0];

		boolean isDoc = inputFileName.endsWith(DOC_EXTENSION),
				isDocx = inputFileName.endsWith(DOCX_EXTENSION);
		
		ReaderContext ctx = new ReaderContext();
		
		if(isDoc){
			ctx.setStrategy(new DocReader());
		} else if(isDocx){
			ctx.setStrategy(new DocxReader());
		} else {
			System.out.println("Unsuported file format");
			System.exit(0);
		}
		
		try {
			List<String> movieNames = ctx.readFile(inputFileName);
			
			final String outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf('/') + 1) + "output.txt"; 
			Path file = FileSystems.getDefault().getPath(outputFileName);
			Files.write(file, movieNames, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();

			System.out.println("Could not write to output file");
			System.exit(0);
		} 
	}
}
