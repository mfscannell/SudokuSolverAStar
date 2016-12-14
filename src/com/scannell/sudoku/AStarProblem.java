package com.scannell.sudoku;

import java.util.List;

/**
 * An interface representing a problem to be solved using the A-Star algorithm.
 * @author mscannell
 *
 */
public interface AStarProblem {
    /**
     * Get the F-value for the A-Star problem.
     * @return  The F-value.
     */
    public abstract int getF();
    
    /**
     * Get the G-value for the A-Star problem.
     * @return  The G-value.
     */
    public abstract int getG();
    
    /**
     * Get the H-value for the A-Star problem.
     * @return  The H-value.
     */
    public abstract int getH();
    
    /**
     * Queries if the problem is in a goal state.
     * @return  True if the problem is in a goal state.
     */
    public abstract boolean isGoalState();
    
    /**
     * Get a list of valid children of the A-Star problem.
     * @return  A list of valid children problems.
     */
    public abstract List<AStarProblem> getValidChildren();
    
    /**
     * Get the hash code.
     * @return  The hash code.
     */
    public abstract String getHashCode();
}
