package sudoku;
import java.util.ArrayList;
import java.util.List;

public class State {

    // A* Variables 
    private int gAStar = 0;
    private int hAStar = 0;
    private final Printer printer = new Printer();
    
    private int[][] board;
    
    // Children State Functions
    private List<State> children = null;
    
    public State(State parent) {
        this.board = new int[9][9];
        
        if (parent != null) {
            int[][] parentSudoku = parent.getBoard();
            
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    this.board[row][column] = parentSudoku[row][column];
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
    
    public List<State> getChildren() {
        return children;
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public String toString() {
        return printer.printSudoku(board);
    }
    
    public boolean isValid() {
        return isValid(board);
    }
    
    //Sets value of row and column in Sudoku board
    public void setNumber(int row, int column, int value) {
        try {
            board[row][column] = value; 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("row " + row + " column " + column + " value " + value);
            throw e;
        }
    }
    
    //Gets number in row and column in Sudoku board
    public int getNumber(int row, int column) {
        return board[row][column];
    }
    
    //Loads Sudoku from string, values should be separated by ','
    public void loadSudokuFromString(String sudokuData) {
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
            
            // place toBeAdded at the proper row and column
            setNumber(row, column, toBeAdded);
            column++;
            
            //After using up all the 9 columns, go to next row and start at column 0.
            if (column == 9) {
                row = row + 1;
                column = 0;     
            }
        }
        getHeuristic();
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
    public double getHeuristic() {
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
    
    private boolean isValid(final int[][] board) {
        return (isEachRowValid() && isEachColumnValid() && isEachSubBoxValid());
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

    public boolean isSolved(final int[][] board) {
        boolean solved = true;
        
        if (isValid(board)) {
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
    
    public void buildChildren() {
        int row = 0;
        int column = 0;
        
        for (int k = 0; k < 81; k++) {
            row = k / 9;
            column = k % 9;
            
            if (board[row][column] == 0) {
                break;
            }
        }
        
        List<State> children = new ArrayList<>();
        
        for (int k = 0; k < 9; k++) {
            State child = new State(this);
            child.setNumber(row, column, (k + 1));
            
            if (child.isValid()) {
                child.getHeuristic();
                children.add(child);
            }
            
            this.children = children;
        }
    }
}