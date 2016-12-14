package com.scannell.sudoku;

import java.util.Scanner;
import java.io.*;

public class Solver {
    public static void main(String[] args) {
        String inputFilePath = SudokuFileReader.queryFileName();
        int[][] board = SudokuFileReader.interpretFile(inputFilePath);
        AStarProblem startState = new SudokuPuzzle(board);
        long startTime = System.currentTimeMillis();
        System.out.println("\n\n\tWorking...");
        AStarProblem solutionState = AStarProblemSolver.findSolution(startState);
        long endTime = System.currentTimeMillis();
                
        if (solutionState != null) {
            System.out.println("\nSolution time " + (endTime - startTime) + " ms");
            System.out.println("The most efficient solution : \n");
            System.out.println(solutionState.toString());        
        } else {
            for (int clear = 0; clear < 50; clear++) {
                System.out.println("\b") ;
            }
            
            System.out.println("The provided puzzle has no solutions.");
        }
    }
}