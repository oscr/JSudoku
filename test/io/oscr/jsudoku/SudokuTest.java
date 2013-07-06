package io.oscr.jsudoku;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class SudokuTest {

	private Sudoku sudoku;
	
	@Before
	public void setUp(){
		sudoku = SudokuFactory.makeSudoku();
		
	}
	
	@AfterClass
	public static void cleanUp(){
		File sudokufile = new File("testWriteSudoku.txt");
		sudokufile.delete();
		
	}
	
	@Test
	public void setValueAndPositionChanges() {
		sudoku.setPosition(0, 0, 1);
		int result = sudoku.getPosition(0, 0);
		assertEquals(1, result);
		
	}
	
	@Test
	public void copyIsNotSameAfterChange(){
		Sudoku copySudoku = SudokuFactory.copySudoku(sudoku);
		assertEquals(sudoku, copySudoku);
		
		// Make sure that we don't set the same value again
		int value = 9;
		if(value == sudoku.getPosition(0, 0)){
			value = 8;
		}
		
		copySudoku.setPosition(0, 0, value);
		assertFalse(sudoku.equals(copySudoku));
		
	}

	@Test
	public void repeatedNumberRowIsInvalidSudoku() {
		sudoku.setPosition(0, 0, 1);
		sudoku.setPosition(0, 1, 1);
		assertFalse(sudoku.isValid());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void readSudokuNullArgumentGivesException() throws Exception {
		SudokuFactory.readSudoku(null);
		
	}
	
	@Test(expected = FileNotFoundException.class)
	public void readSudokuDoesntExistGivesException() throws Exception {
		SudokuFactory.readSudoku(new File("FILE_THAT_DOES_NOT_EXIST"));
	
	}
	
	@Test
	public void readSolvedSudokuCheckProperties() {
		File sudokufile = new File("resources" + File.separator + "solvedSudoku1.txt");
		if(!sudokufile.exists()){
			fail("Missing file: " + sudokufile.getAbsolutePath());

		}
		
		Sudoku sudoku = null;
		try {
			sudoku = SudokuFactory.readSudoku(sudokufile);
		
		} catch (IOException ioe) {
			fail("IOException should not occurr");
			
		}
		
		assertNotNull(sudoku);
		assertTrue(sudoku.isValid());
		assertTrue(sudoku.isSolved());
		
	}
	
	@Test
	public void readAndSolveIncompleteSudoku() throws Exception {
		File sudokufile = new File("resources" + File.separator + "sudokuExample1.txt");
		if(!sudokufile.exists()){
			fail("Missing file: " + sudokufile.getAbsolutePath());

		}
		
		Sudoku sudoku = SudokuFactory.readSudoku(sudokufile);
		
		assertNotNull(sudoku);
		assertTrue(sudoku.isValid());
		assertFalse(sudoku.isSolved());
		
		if(!sudoku.trySolve()){
			fail("Sudoku file: " + sudokufile.getAbsolutePath() + " is solveable!");
			
		}
		
		assertTrue(sudoku.isValid());
		assertTrue(sudoku.isSolved());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void readInvalidLineLengthSudokuGivesException() throws Exception {
		File sudokufile = new File("resources" + File.separator + "sudokuInvalidExample1.txt");
		if(!sudokufile.exists()){
			fail("Missing file: " + sudokufile.getAbsolutePath());

		}
		
		SudokuFactory.readSudoku(sudokufile);
		
	}
	
	@Test(expected = NumberFormatException.class)
	public void readInvalidCharSudokuGivesException() throws Exception {
		File sudokufile = new File("resources" + File.separator + "sudokuInvalidExample2.txt");
		if(!sudokufile.exists()){
			fail("Missing file: " + sudokufile.getAbsolutePath());
			
		}
		
		SudokuFactory.readSudoku(sudokufile);
		
	}
	
	@Test
	public void generateRandomSudokuAndSolveAgain(){
		sudoku = SudokuFactory.makeSudoku();
		assertTrue(sudoku.isValid());
		assertFalse(sudoku.isSolved());
		sudoku.trySolve();
		assertTrue(sudoku.isSolved());
		
	}
	
	@Test
	public void writeSudokuAndReadBackSolved() {
		File sudokufile = new File("testWriteSudoku.txt");
		Sudoku copySudoku = SudokuFactory.copySudoku(sudoku);
		SudokuFactory.writeSudoku(sudokufile, sudoku);
		try {
			sudoku = SudokuFactory.readSudoku(sudokufile);
		
		} catch (IOException e) {
			fail("IOException in writeSudokuAndReadBackSolved");
			
		}
		
		assertEquals(copySudoku, sudoku);
		
	}
	
	@Test
	public void create30EmptyBoxesSudokuAndSolve() {
		int emptyPositions = 30;
		Sudoku sudoku = SudokuFactory.makeSudoku(emptyPositions);
		
		for(int i = 0; i < Constants.BOARDSIZE; i++){
			for(int j = 0; j < Constants.BOARDSIZE; j++){
				if(sudoku.getPosition(i, j) == Constants.EMPTY_POSITION){
					emptyPositions--;
				}
			}
		}
		
		assertEquals(0, emptyPositions);
		assertTrue(sudoku.isValid());
		assertFalse(sudoku.isSolved());
		
		assertTrue(sudoku.trySolve());
		assertTrue(sudoku.isSolved());
		assertTrue(sudoku.isValid());
	}

}