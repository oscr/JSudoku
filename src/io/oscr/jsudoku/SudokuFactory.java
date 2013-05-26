package io.oscr.jsudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles the creation and copying of Sudoku objects. Also provides methods to read and write a Sudoku to a file. 
 */
public enum SudokuFactory {
	;

	/**
	 * Creates a Sudoku. The Sudoku is guaranteed to be solvable and the numbers will be randomized. Two invocations are highly unlikely to return the same Sudoku.
	 * 
	 * @return A new Sudoku. 
	 */
	public static Sudoku makeSudoku() {
		return new SudokuModel();

	}

	/**
	 * Creates a deep copy of a Sudoku. 
	 * 
	 * @param sudoku to be copied.
	 * @return Deep copy of Sudoku argument.
	 */
	public static Sudoku copySudoku(Sudoku sudoku) {
		return new SudokuModel(sudoku);
	}
	
	/**
	 * Reads a Sudoku from a file. 
	 * 
	 * Reads the content of a file and will try to create a Sudoku from the content. Does not check if Sudoku is valid (for example two 1's on the same row) or solved. 
	 * 
	 * @param filename to read from.
	 * @return Sudoku read from file.
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 */
	public static Sudoku readSudoku(File filename) throws IOException {
		if(filename == null){
			throw new IllegalArgumentException("readSudoku invoked with null argument.");
		}
		
		BufferedReader br = null;
		try {
			FileReader file = new FileReader(filename);
			br = new BufferedReader(file);
			
		} catch (FileNotFoundException fne) {
			throw new FileNotFoundException("Could not find the file: " + filename);
			
		}

		// Create new array to stor the Sudoku
		int[][] sudoku = new int[Constants.BOARDSIZE][];

		// Reads the Sudoku from the file. 
		for (int i = 0; i < Constants.BOARDSIZE; i++) {
			String[] indata = null;
			try {
				indata = br.readLine().split(Constants.FILE_SEPARATOR);
				
			} catch (IOException iex) {
				br.close();
				throw new IOException("Exception reading from file");
				
			}
			
			if(indata.length != Constants.BOARDSIZE){
				br.close();
				throw new IllegalArgumentException("Sudoku row length is: " + indata.length + " expected: " + Constants.BOARDSIZE);
			
			}

			// Parses the content of the line
			int[] values = new int[Constants.BOARDSIZE];
			for (int j = 0; j < Constants.BOARDSIZE; j++) {
				values[j] = Integer.parseInt(indata[j]);
			}

			sudoku[i] = values;
		}

		try {
			br.close();
		} catch (IOException iex) {
			iex.printStackTrace();
			
		}

		return new SudokuModel(sudoku);
	}

	/**
	 * Writes a Sudoku to a plain text file using spaces as separator.
	 * 
	 * @param filename to use. 
	 * @param sudoku to write to file.
	 * @throws IllegalArgumentException
	 */
	public static void writeSudoku(File filename, Sudoku sudoku){
		if(filename == null){
			throw new IllegalArgumentException("writeSudoku invoked with null argument: filename");
		
		}
		
		if(sudoku == null){
			throw new IllegalArgumentException("writeSudoku invoked with null argument: sudoku");

		}
		
		try {
			PrintWriter writer = new PrintWriter(filename);
			for(int i = 0; i < Constants.BOARDSIZE; i++){
				for(int j = 0; j < Constants.BOARDSIZE; j++){
					writer.print(sudoku.getPosition(i, j));
					writer.print(" ");
				}
				writer.print('\n');
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		
	}
	
}