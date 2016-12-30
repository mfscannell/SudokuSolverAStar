package com.scannell.sudoku;
import java.util.ArrayList;
import java.util.List;

public class SudokuPuzzle implements AStarProblem {
    private int gAStar = 0;
    private int hAStar = 0;
    
    private int[][] board;
    
    /**
     * Creates a new instance of a sudoku puzzle.
     */
    public SudokuPuzzle() {
        this.board = new int[9][9];
        this.gAStar = 0;
        this.hAStar = 81;
    }
    
    /**
     * Creates a new instance of a sudoku puzzle.
     * @param board  An array representing the numbers on a board.
     */
    public SudokuPuzzle(int[][] board) {
        this.board = new int[9][9];
        this.gAStar = 0;
        this.hAStar = 81;
        
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                this.board[row][column] = board[row][column];
                if (board[row][column] > 0) {
                    this.gAStar++;
                    this.hAStar--;
                }
            }
        }
    }
    
    /**
     * Gets the A-Star's G-value.
     */
    public int getG() {
        return gAStar;
    }

    /**
     * Gets the A-Star's H-value.
     */
    public int getH() {
        return hAStar;
    }

    /**
     * Gets the A-Star's F-value.
     */
    public int getF() {
        return gAStar + hAStar;
    }

    public boolean isGoalState() {
        return hAStar == 0;
    }
    
    public String getHashCode() {        
        return Integer.toString(this.hashCode());
    }
    
    /**
     * Returns a string representation of the puzzle.
     */
    public String toString() {
        String toBePrinted = Printer.lineSeparator();
        
        for (int i = 0; i < 9; i++) {
            toBePrinted += "|";
            
            for (int j = 0; j < 9; j++) {
                toBePrinted += String.format("%2s", board[i][j]);
                toBePrinted += Printer.boxSeparator(j);     
            }
  
            toBePrinted += "\n";
            
            if (i != 0 && ((i + 1) % 3 == 0)) {
                toBePrinted += Printer.lineSeparator();
            }
        }
        
        return toBePrinted;
    }
    
    /**
     * Checks if the puzzle is valid.  A valid puzzle does not have repeated numbers
     * in any row, column, or 3 x 3 sub-box.  A valid puzzle allows for empty cells.
     * @return  True if the puzzle is valid.
     */
    public boolean isValid() {
        return isEachRowValid() && isEachColumnValid() && isEachSubBoxValid();
    }
    
    /**
     * Checks if the puzzle is solved.
     * @return  True if the puzzle is solved.
     */
    public boolean isSolved() {
        boolean solved = true;
        
        if (isValid()) {
            for (int row = 0; row < 9 && solved; row++) {
                for (int column = 0; column < 9 && solved; column++) {
                    if (board[row][column] == 0) {
                        solved = false;
                    }
                }
            }
        } else {
            solved = false;
        }

        return solved;
    }
    
    /***
     * Gets a list of children puzzles.
     */
    public List<AStarProblem> getValidChildren() {
        List<AStarProblem> children = new ArrayList<AStarProblem>();
        
        int row = 0;
        int column = 0;
        boolean emptyCellFound = false;
        
        for (int i = 0; i < 9 && !emptyCellFound; i++) {
            for (int j = 0; j < 9 && !emptyCellFound; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    column = j;
                    emptyCellFound = true;
                }
            }
        }
        
        for (int k = 1; k <= 9; k++) {
            SudokuPuzzle child = new SudokuPuzzle(this.board);
            child.setNumber(row, column, k);
            
            if (child.isValid()) {
                children.add(child);
            }
        }
        
        return children;
    }
    
    /**
     * Sets the value for a cell at a particular row and column combination.
     * @param row  The row of the cell to set the value at.
     * @param column  The column of the cell to set the value at.
     * @param value  The value to set the cell to.
     */
    private void setNumber(int row, int column, int value) {
        try {
            board[row][column] = value;
            this.gAStar++;
            this.hAStar--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("row " + row + " column " + column + " value " + value);
            throw e;
        }
    }
    /**
     * Checks if each row in the sudoku puzzle is valid.  A valid row does not have
     * numbers 1 through 9 repeated and does allow for empty cells in a row.
     * @return  True if each row is valid.
     */
    private boolean isEachRowValid() {
        boolean eachRowValid = true;
        int[] sudokuRow = new int[9];
        
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sudokuRow[column] = board[row][column];
            }
            
            if (isDuplicatePresent(sudokuRow)) {
                eachRowValid = false;
                break;
            }
        }
        
        return eachRowValid;
    }
    
    /**
     * Checks if each column in the sudoku puzzle is valid.  A valid column does not have
     * numbers 1 through 9 repeated and does allow for empty cells in a column.
     * @return  True if each column is valid.
     */
    private boolean isEachColumnValid() {
        boolean eachColumnValid = true;
        int[] sudokuColumn = new int[9];
        
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sudokuColumn[column] = board[column][row];
            }
            
            if (isDuplicatePresent(sudokuColumn)) {
                eachColumnValid = false;
                break;
            }
        }
        
        return eachColumnValid;
    }
    
    /**
     * Checks if each sub-box of the sudoku puzzle is valid.  A valid sub-box does not have
     * nnumbers 1 through 9 repeated and does allow for empty cells in a sub-box.
     * @return  True if each sub box is valid.
     */
    private boolean isEachSubBoxValid() {
        boolean eachSubBoxValid = true;
        
        for (int box = 0; box < 9; box++) {
            int [] threeBythreeBox = new int[9];
            int boxRow = box / 3;
            int boxColumn = box % 3;
            int indexRowOffset = boxRow * 3;
            int indexColumnOffset = boxColumn * 3;
            
            for (int index = 0; index < 9; index++) {
                threeBythreeBox[index] = board[indexRowOffset + (index / 3)][indexColumnOffset +(index % 3)];
            }
            
            if (isDuplicatePresent(threeBythreeBox)) {
                eachSubBoxValid = false;
                break;                     
            }            
        }
        
        return eachSubBoxValid;
    }

    /**
     * Check if a number is repeated in a given set of numbers.
     * @param data  The particular row, column, or 3 x 3 box.
     * @return  True if a duplicate present.
     */
    private boolean isDuplicatePresent(int[] data) {
        boolean duplicatePresent = false;
        
        for (int i = 0; i < data.length && !duplicatePresent; i++) {
            int value = data[i];
            
            if (value != 0) {
                for (int j = i + 1; j < data.length && !duplicatePresent; j++) {
                    if (value == data[j]) {
                        duplicatePresent = true;
                        break;
                    }
                }
            }
        }
        return duplicatePresent;
    }
}