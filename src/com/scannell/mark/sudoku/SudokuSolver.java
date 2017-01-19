package com.scannell.mark.sudoku;

import com.scannell.mark.artificialIntelligence.AStarProblem;
import com.scannell.mark.artificialIntelligence.AStarProblemSolver;

public class SudokuSolver {
    public static void main(String[] args) {
        String inputFilePath = SudokuFileReader.queryFileName();
        int[][] board = SudokuFileReader.interpretFile(inputFilePath);
        AStarProblem startState = new SudokuPuzzle(board);
        System.out.println("\n\n\tWorking...");
        AStarProblem solutionState = AStarProblemSolver.findSolution(startState);
                
        if (solutionState != null) {
            System.out.println("The most efficient solution : \n");
            System.out.println(solutionState.toString());        
        } else {
            System.out.println("The provided puzzle has no solutions.");
        }
    }
}