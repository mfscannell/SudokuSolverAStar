package unitTest.sudoku;

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
       String text = "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0";
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(text);
       
       assertSame(0, sudokuPuzzle.getG());
    }

    @Test
    public void TestGetGIncompletePuzzle() {
       String text = "0,9,3,0,0,0,0,0,0,";
       text = text + "5,0,8,6,0,4,0,0,7,";
       text = text + "0,0,4,0,9,3,1,0,0,";
       
       text = text + "2,3,0,0,0,8,9,7,0,";
       text = text + "0,0,0,7,0,2,0,0,0,";
       text = text + "0,6,7,1,0,0,0,2,5,";
       
       text = text + "0,0,2,9,7,0,4,0,0,";
       text = text + "8,0,0,3,0,5,7,0,9,";
       text = text + "0,0,0,0,0,0,2,3,0";
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(text);
       
       assertSame(34, sudokuPuzzle.getG());
    }
    
    @Test
    public void TestGetGFullPuzzle() {
       String text = "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9";
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(text);
       
       assertSame(81, sudokuPuzzle.getG());
    }
    
    @Test
    public void TestGetHEmptyPuzzle() {
       String text = "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0,";
       text = text + "0,0,0,0,0,0,0,0,0";
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(text);
       
       assertSame(81, sudokuPuzzle.getH());
    }

    @Test
    public void TestGetHIncompletePuzzle() {
       String text = "0,9,3,0,0,0,0,0,0,";
       text = text + "5,0,8,6,0,4,0,0,7,";
       text = text + "0,0,4,0,9,3,1,0,0,";
       
       text = text + "2,3,0,0,0,8,9,7,0,";
       text = text + "0,0,0,7,0,2,0,0,0,";
       text = text + "0,6,7,1,0,0,0,2,5,";
       
       text = text + "0,0,2,9,7,0,4,0,0,";
       text = text + "8,0,0,3,0,5,7,0,9,";
       text = text + "0,0,0,0,0,0,2,3,0";
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(text);
       
       assertSame(47, sudokuPuzzle.getH());
    }
    
    @Test
    public void TestGetHFullPuzzle() {
       String text = "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9,";
       text = text + "1,2,3,4,5,6,7,8,9";
       
       AStarProblem sudokuPuzzle = new SudokuPuzzle(text);
       
       assertSame(0, sudokuPuzzle.getH());
    }
}
