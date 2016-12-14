package com.scannell.sudoku;
import java.util.ArrayList;
import java.util.List;

public class SudokuPuzzle implements AStarProblem {

    // A* Variables 
    private int gAStar = 0;
    private int hAStar = 0;
    private final Printer printer = new Printer();
    
    private int[][] board;
    
    public SudokuPuzzle(String board) {
        this.board = new int[9][9];
        this.gAStar = 0;
        
        this.loadSudokuFromString(board);
        this.calcHeuristic();
    }
    
    public SudokuPuzzle(SudokuPuzzle parent) {
        this.board = new int[9][9];
        this.gAStar = parent.getG();
        this.hAStar = parent.getH();
        
        int[][] parentSudoku = parent.getBoard();
        
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                this.board[row][column] = parentSudoku[row][column];
            }
        }
    }
    
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
    
    // Value of g : Cost of reaching this state from the initial state. 
    public int getG() {
        return gAStar;
    }

    // Value of h : Heuristic grade.
    public int getH() {
        return hAStar;
    }

    public int getF() {
        return gAStar + hAStar;
    }

    public boolean isGoalState() {
        return hAStar == 0;
    }
    
    public String getHashCode() {        
        return Integer.toString(this.hashCode());
    }
    
    public int[][] getBoard() {
        return board;
    }
    
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
    
    public boolean isValid() {
        return isEachRowValid() && isEachColumnValid() && isEachSubBoxValid();
    }
    
    //Sets value of row and column in Sudoku board
    private void setNumber(int row, int column, int value) {
        try {
            board[row][column] = value;
            this.gAStar++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("row " + row + " column " + column + " value " + value);
            throw e;
        }
    }
    
    //Loads Sudoku from string, values should be separated by ','
    private void loadSudokuFromString(String sudokuData) {
        if (sudokuData == null) {
            throw new NullPointerException();
        }

        board = new int[9][9];
        int row = 0;
        int column = 0;
       
        // Create an array of 81 pieces of the String separated by commas.
        String[] tokens = sudokuData.split(",");
        
        int toBeAdded = 0;
        // 81 items to be added to the board and translated to integers.
        for (int i = 0; i < 81; i++) {
            try {
                toBeAdded = Integer.parseInt(tokens[i]);    
            } catch (NumberFormatException e) {
                //ignoring
            }
            
            board[row][column] = toBeAdded;
            
            if (toBeAdded > 0) {
                this.gAStar++;
            }
            column++;
            
            //After using up all the 9 columns, go to next row and start at column 0.
            if (column == 9) {
                row = row + 1;
                column = 0;     
            }
        }
    }
   
    /*************** Heuristic Function *********************
    *   This function is designed to calculate the distance 
    *   between the current state and the goal state. The 
    *   function iterates throught the entire board to count
    *   the number of 0's. The larger the number of 0's the
    *   further away you are from the solution. The h of the
    *   current state is set to the heuristic value and it is
    *   returned to the caller as well.
    */
    private double calcHeuristic() {
        int counter = 0;
        
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column] == 0) {
                    counter++;
                }
            }
        }
        
        hAStar = counter;
        return counter;
    }
    
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

    // 
    // Check if a number is repeated in a given set
    // of numbers. The caller function must send in
    // the data in the format of an integer array.
    //
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

    /**
     * 
     * @param board
     * @return
     */
    public boolean isSolved(final int[][] board) {
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
        
        for (int k = 0; k < 81; k++) {
            row = k / 9;
            column = k % 9;
            
            if (board[row][column] == 0) {
                break;
            }
        }
        
        for (int k = 1; k <= 9; k++) {
            SudokuPuzzle child = new SudokuPuzzle(this);
            child.setNumber(row, column, k);
            
            if (child.isValid()) {
                child.calcHeuristic();
                children.add(child);
            }
        }
        
        return children;
    }
}