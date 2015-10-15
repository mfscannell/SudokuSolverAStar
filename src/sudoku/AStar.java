package sudoku;

/* ----------------------------------------------------------------------------

                      A* Sudoku Solver

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class AStar {
    public static State findSolutionIntermediate(State startState) {
        State currentState = startState;
        State solution = null;
        Queue<State> unexploredStates = new LinkedList<State>();
        Map<String, State> exploredStates = new HashMap<String, State>();

        while (!currentState.isGoalState()) {
            currentState.buildChildren();
              
            List<State> children = currentState.getChildren();
              
            for (State child : children) {
                if (!exploredStates.containsKey(child.getHashCode())) {
                    if (!unexploredStates.contains(child)) {
                        unexploredStates.add(child);
                    } else {
                        boolean isNewBetter = false;
                        State theExisting = null;
        
                        //finding existing in the frontier set
                        for (State existing : unexploredStates) {
                            if (existing.equals(child)) {
                                if (child.getG() < existing.getG()) {
                                    isNewBetter = true;
                                    theExisting = existing;
                                }
                                
                                break;
                            }
                        }
                        
                        if (isNewBetter) {
                            unexploredStates.remove(theExisting);
                            unexploredStates.add(child);
                        }
                    }
                }
            }
            
            exploredStates.put(currentState.getHashCode(), currentState);

            if (unexploredStates.isEmpty()) {
                break; 
            }

            if (!unexploredStates.isEmpty()) {
                currentState = unexploredStates.poll();
            }
        }
        
        if (currentState.isGoalState()) {
            solution = currentState;
        }
        
        return solution;
    }
}