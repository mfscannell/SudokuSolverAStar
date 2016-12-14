package com.scannell.sudoku;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SudokuFileReader {
    public static String queryFileName() {
        System.out.print("\n\nPlease enter the name of the file containing the Start State : ");
        Scanner scanner = new Scanner(System.in);
        String inputFilePath = scanner.nextLine();
        scanner.close();
        
        return inputFilePath;
    }
    
    public static int[][] interpretFile(String fileName) {
        int[][] sudokuBoard = new int[9][9];
        
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            for (int i = 0; i < 9; i++) {
                String strLine = br.readLine();
                String[] elements = strLine.split(" ");
                
                for (int j = 0; j < 9; j++) {
                    sudokuBoard[i][j] = Integer.parseInt(elements[j]);
                }
            }
            
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }
        
        return sudokuBoard;
    }

}
