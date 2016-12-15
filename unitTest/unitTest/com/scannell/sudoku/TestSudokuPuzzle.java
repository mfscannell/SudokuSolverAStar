package unitTest.com.scannell.sudoku;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.scannell.sudoku.AStarProblem;
import com.scannell.sudoku.SudokuPuzzle;

public class TestSudokuPuzzle {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void TestGetGEmptyPuzzle() {
       int[][] board = {
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0}
       };
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(board);
       
       assertSame(0, sudokuPuzzle.getG());
    }

    @Test
    public void TestGetGIncompletePuzzle() {
       int[][] board = {
               {1, 0, 2, 0, 0, 0, 0, 0, 0},
               {0, 3, 0, 4, 0, 5, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 6, 0, 0, 0, 7, 0, 0},
               {0, 0, 0, 0, 8, 0, 0, 9, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 1, 0, 2, 0, 0, 0, 0, 0},
               {0, 0, 3, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0}
       };
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(board);
       
       assertSame(12, sudokuPuzzle.getG());
    }
    
    @Test
    public void TestGetGFullPuzzle() {
       int[][] board = {
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9},
               {1, 2, 3, 4, 5, 6, 7, 8, 9}
       };
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(board);
       
       assertSame(81, sudokuPuzzle.getG());
    }
    
    @Test
    public void TestGetHEmptyPuzzle() {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(board);
       
       assertSame(81, sudokuPuzzle.getH());
    }

    @Test
    public void TestGetHIncompletePuzzle() {
        int[][] board = {
                {1, 0, 2, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 4, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 6, 0, 0, 0, 7, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(board);
       
       assertSame(69, sudokuPuzzle.getH());
    }
    
    @Test
    public void TestGetHFullPuzzle() {
        int[][] board = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9}
        };
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(board);
       
       assertSame(0, sudokuPuzzle.getH());
    }
}
