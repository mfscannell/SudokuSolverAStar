package sudoku;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class AStar {
    public static AStarProblem findSolution(AStarProblem startState) {
        AStarProblem currentState = startState;
        AStarProblem solution = null;
        Queue<AStarProblem> unexploredStates = new LinkedList<AStarProblem>();
        Map<String, AStarProblem> exploredStates = new HashMap<String, AStarProblem>();

        while (!currentState.isGoalState()) {
            currentState.buildChildren();
              
            List<AStarProblem> children = currentState.getChildren();
            System.out.println("----------------------------------");
            System.out.println("Current State:");
            System.out.println(currentState.toString());
              
            for (AStarProblem child : children) {
                //System.out.println("child");
                //System.out.println(child.toString());
                if (!exploredStates.containsKey(child.getHashCode())) {
                    if (!unexploredStates.contains(child)) {
                        unexploredStates.add(child);
                    } else {
                        boolean isNewBetter = false;
                        AStarProblem theExisting = null;
        
                        //finding existing in the frontier set
                        for (AStarProblem existing : unexploredStates) {
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