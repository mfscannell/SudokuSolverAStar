package sudoku;
/* ----------------------------------------------------------------------------
      This project is authored by 
     
    Joseph Champion         (2462781)   
    Chinmay Ratnaparkhi     (2736356)

    EECS 649 - Project 04
    Dr. Frank Brown
    05/04/2015
 * ---------------------------------------------------------------------------- 
                            A* Sudoku Solver --> Solver

    This project uses the A* algorithm to solve a given Sudoku puzzle. These
    *.java files can be compiled with the provided makefile and run by typing 
    "java Solver" in the terminal after compilation.
    
    The Solver requests a text file name to read the Start State to begin
    computation. 4 Test files (easy.txt, medium.txt, hard.txt and insane.txt) 
    are provided in the 'test' folder. These files can be loaded by entering
    their names in the format : "xyz.txt" after running the Solver, since
    
    The Solver searches IN THE "TEST" FOLDER BY DEFAULT.

    If additional test files need to be added, they can be added to the "test"
    folder.     
*  ----------------------------------------------------------------------------     
*/
import java.util.Scanner;
import java.io.*;

public class Solver {
    public static void main(String[] args) {
        // Timer variables
        long startTime = 0;
        long endTime = 0;
        
        /* On launch the system requests the name of the file that contains the Start State of the Sudoku 
         * puzzle. The file MUST BE space/tab delimited. The string is formatted according to the input 
         * requirements of the subsequently called methods. 
         */ 
        String finalInput = "";
        String inputFilePath = "";
        System.out.print("\n\nPlease enter the name of the file containing the Start State : ");
        Scanner scanner = new Scanner(System.in);
        inputFilePath = scanner.nextLine();
        scanner.close();
        
        try {
            FileInputStream fstream = new FileInputStream(inputFilePath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
                  
            while ((strLine = br.readLine()) != null) {
                if (strLine.trim().length() != 0) {
                    /*  An array called 'elements' is created to contain every number in a 
                     *  single line, a comma is added after each number.
                     */
                    String [] elements = strLine.split(" ");
                    
//                    what I prefer to do
//                    for (int i = 0; i < elements.length; i++) {
//                        final_input += elements[i];
//                        final_input += ","; 
//                    }
                    
                    int i = 0;
                    do {
                        finalInput += elements[i];
                        finalInput +=",";   
                        i++;
                    } while (i < elements.length);
                }
            }

            in.close();
            /* The very last comma is removed by creating a substring of length one less than
             * the length of the original string.
             */
            finalInput = finalInput.substring(0, finalInput.length() - 1);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }
    
        State startState = new State(null);
        startState.loadSudokuFromString(finalInput);

        startTime= System.currentTimeMillis();
        System.out.println("\n\n\tWorking...");
        State solutionState = AStar.findSolutionIntermediate(startState);
        endTime = System.currentTimeMillis();
                
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