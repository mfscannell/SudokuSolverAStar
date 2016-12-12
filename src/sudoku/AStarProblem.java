package sudoku;

import java.util.List;

public interface AStarProblem {
    public abstract int getF();
    
    public abstract int getG();
    
    public abstract int getH();
    
    public abstract boolean isGoalState();
    
    public abstract String getHashCode();
    
    public abstract void buildChildren();
    
    public abstract List<AStarProblem> getChildren();
    
    public abstract String toString();
}
