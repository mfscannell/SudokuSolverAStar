package sudoku;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class AStar {
    public static SudokuPuzzle findSolutionIntermediate(SudokuPuzzle startState) {
        SudokuPuzzle currentState = startState;
        SudokuPuzzle solution = null;
        Queue<SudokuPuzzle> unexploredStates = new LinkedList<SudokuPuzzle>();
        Map<String, SudokuPuzzle> exploredStates = new HashMap<String, SudokuPuzzle>();

        while (!currentState.isGoalState()) {
            currentState.buildChildren();
              
            List<SudokuPuzzle> children = currentState.getChildren();
              
            for (SudokuPuzzle child : children) {
                if (!exploredStates.containsKey(child.getHashCode())) {
                    if (!unexploredStates.contains(child)) {
                        unexploredStates.add(child);
                    } else {
                        boolean isNewBetter = false;
                        SudokuPuzzle theExisting = null;
        
                        //finding existing in the frontier set
                        for (SudokuPuzzle existing : unexploredStates) {
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

            currentState = unexploredStates.poll();
        }
        
        if (currentState.isGoalState()) {
            solution = currentState;
        }
        
        return solution;
    }
}