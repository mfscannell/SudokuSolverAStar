package sudoku;

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