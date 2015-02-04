### Welcome!

JSudoku is a library written in Java that can generate and solve Sudoku puzzles. It supports reading and writing Sudoku's from plain text files seperated by spaces.

Quick example:
```
import io.oscr.jsudoku.Sudoku;
import io.oscr.jsudoku.SudokuFactory;

public class Main {
	public static void main(String[] args) {
		// Generate a random Sudoku that is solvable
		Sudoku sudoku = SudokuFactory.makeSudoku();

		// Print the Sudoku
		System.out.println(sudoku);

		// Check if the Sudoku is solved
		System.out.println(sudoku.isSolved());

		// Check if the Sudoku is valid
		System.out.println(sudoku.isValid());

		// Try to solve the Sudoku
		sudoku.trySolve();

		// Print the solved Sudoku
		System.out.println(sudoku);

		// Generate a Sudoku that is solvable with only 1 empty box.
		// Works for any value 1 <= n <= 64
		sudoku = SudokuFactory.makeSudoku(1);

		// Print the Sudoku
		System.out.println(sudoku);

		// Check the value of any position
		sudoku.trySolve();

		// Get value from a given position
		System.out.println(sudoku.getPosition(0, 0));
	}
}
```
