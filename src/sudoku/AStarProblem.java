package sudoku;

public interface AStarProblem {
    public abstract int getF();
    
    public abstract int getG();
    
    public abstract int getH();
    
    public abstract boolean isGoalState();
}
