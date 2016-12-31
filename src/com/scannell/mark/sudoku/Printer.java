package com.scannell.mark.sudoku;

public class Printer {
    public Printer() {}
    
    public static String lineSeparator() {
        String separator = "-------------------------------\n";
        return separator;
    }

    public static String boxSeparator(int column) {
        if (column != 0 && ((column + 1) % 3 == 0)) {
            return " |";
        }
    
        return " ";
    }
}