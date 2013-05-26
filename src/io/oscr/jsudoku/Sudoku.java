package io.oscr.jsudoku;

public interface Sudoku {
	
	/**
	 * Checks if the Sudoku is valid and solved. If both are true the method will return true. Otherwise false.
	 *  
	 * @return true if valid and solved Sudoku, otherwise false.
	 */
	public boolean isSolved();

	/**
	 * Checks if a Sudoku is valid. A valid Sudoku is any Sudoku that doesn't violate the rules for how a Sudoku is solved.
	 * For example should a Sudoku have two 1's on the same row, box or column it is not valid and the method will return false. 
	 * 
	 * @return true if valid Sudoku, otherwise false
	 */
	public boolean isValid();
	
	/**
	 * Returns value for coordinate on the Sudoku.
	 * 
	 * The top right corner is (0,0) and the bottom right corner is (8,8). Given an (x,y) coordinate it will return the value
	 * of the coordinate. If the box is empty it will return 0. If the coordinate has a value, that value will be returned. 
	 * Return values will therefore range from minimum 0 to maximum 9.
	 * 
	 * @param x for row. 0 indexed.
	 * @param y for file. 0 indexed.
	 * @return 0 if empty. Otherwise value of box.
	 */
	public int getPosition(int x, int y);
	
	/**
	 * Sets the value for coordinate on the Sudoku 
	 * 
	 * Given an coordinate (x,y) will set that coordinate to the value that is given as an argument.
	 * 
	 * @param x coordinate.
	 * @param y coordinate.
	 * @param value to be set.
	 */
	public void setPosition(int x, int y, int value);
	
	/**
	 * Attempt to solve the Sudoku.
	 * 
	 * Warning this will change the Sudoku! You should make a copy if you want to be able to revert the original.
	 * 
	 * @return true if solved succeeded, false otherwise.
	 */
	public boolean trySolve();
	
}