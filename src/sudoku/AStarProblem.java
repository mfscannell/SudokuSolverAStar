package sudoku;

import java.util.List;

public interface AStarProblem {
    public abstract int getF();
    
    public abstract int getG();
    
    public abstract int getH();
    
    public abstract boolean isGoalState();
    
    public abstract List<AStarProblem> getValidChildren();
    
    public abstract String getHashCode();
}
