package com.mb.strategy;

import java.util.List;

/**
 * 
 * "Context" class responsible for calling appropriate 
 * processing file methodology. 
 * 
 * @author Milan Brankovic
 *
 */
public class ReaderContext {

	private ReaderStrategy strategy;
	
	public void setStrategy(ReaderStrategy strategy) {
		this.strategy = strategy;
	}

	public List<String> readFile(final String fileName){
		return strategy.readFile(fileName);
	}
}
