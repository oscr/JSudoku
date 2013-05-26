### Welcome!

JSudoku is a library written in Java that can generate and solve Sudoku's. It supports reading and writing Sudoku's from plain text files seperated by spaces. 

Quick example:
```
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
```
