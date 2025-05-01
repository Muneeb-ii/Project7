/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: An abstract class that is used to represent different maze searching
 * algorithms. 
 * 
*/

abstract class AbstractMazeSearch {
    
    /**
     * Maze object that is used to represent the maze
     */
    private Maze maze;

    /**
     * The starting cell of the maze
     */
    private Cell start;

    /**
     * The target cell of the maze
     */
    private Cell target;

    /**
     * The current cell being explored
     */
    private Cell cur;

    /**
     * Constructor for the AbstractMazeSearch class.
     * 
     * @param maze
     */
    public AbstractMazeSearch(Maze maze){
        this.maze = maze;
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    /**
     * Returns the next Cell to explore and removes it from the data structure.
     *  
     * @param cur    the current Cell being explored
     * @param target the target Cell
     * @param isDFS  true if the search is a depth-first search, false if it is a breadth-first
     * @param depth  the depth of the search
     * @return the next Cell to explore
     */
    public abstract Cell findNextCell(Cell cur, Cell target, boolean isDFS, int depth);

    /**
     * Adds the given Cell to the data structure.
     * 
     * @param next the Cell to add
     */
    public abstract void addCell(Cell next);

    /**
     * Updates the given Cell priority in the priority queue (used only in A* search).
     * 
     * @param next the Cell to update
     */
    public abstract void updateCell(Cell next);

    /**
     * Returns the number of cells remaining in the data structure.
     * 
     * @return the number of cells remaining in the data structure
     */
    public abstract int numRemainingCells();
}
