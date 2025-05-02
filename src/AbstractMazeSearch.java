/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: An abstract class that is used to represent different maze searching
 * algorithms. 
 * 
*/

import java.awt.Color;
import java.awt.Graphics;

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
     * @return the next Cell to explore
     */
    public abstract Cell findNextCell();

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

    /**
     * Return the maze object.
     * 
     * @return the maze object 
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * Returns the starting Cell of the maze.
     * 
     * @return the starting Cell of the maze
     */
    public Cell getStart() {
        return start;
    }

    /**
     * Returns the target Cell of the maze.
     * 
     * @return the target Cell of the maze
     */
    public Cell getTarget() {
        return target;
    }

    /**
     * Returns the current Cell being explored.
     * 
     * @return the current Cell being explored
     */
    public Cell getCur() {
        return cur;
    }

    /**
     * Sets the current Cell being explored.
     * 
     * @param cur the current Cell being explored
     */
    public void setCur(Cell cur) {
        this.cur = cur;
    }

    /**
     * Resets the search by setting the starting and target cells to null.
     */
    public void reset(){
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    /**
     * Returns the path from the starting cell to the target cell if it exists.
     * 
     * @param cell the target cell
     * @return the path from the starting cell to the target cell
     */
    public LinkedList<Cell> traceback(Cell cell){
        LinkedList<Cell> path = new LinkedList<Cell>();
        while (cell!=null){
            path.add(cell); // add the cell to the path
            cell = cell.getPrev();
        }
        return path;
    }

    /**
     * Searches the maze for the target cell using the given starting cell.
     * 
     * @param start  the starting cell
     * @param target the target cell
     * @return the path from the starting cell to the target cell if it exists, null otherwise
     */
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay){
        // Set the starting and target cells
        this.start = start;
        this.target = target;
        
        setCur(start);

        // This line is just to make the drawing work correctly
        start.setPrev(start);

        addCell(start);

        // While there are still cells to explore
        while(numRemainingCells()>0){
            // Set the current cell to the next cell to explore
            setCur(findNextCell());

            for(Cell neighbor: maze.getNeighbors(cur)){
                // if the neighbor has not been visited set its previous cell to the current cell
                // and add it to the data structure
                if (neighbor.getPrev() == null){
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                }
                // if the neighbor has been visited and the we find a shorter path to it
                // set its previous cell to the current cell and update its position in the data structure
                else if (traceback(neighbor).size()>traceback(cur).size()){
                    neighbor.setPrev(cur);
                    updateCell(neighbor);
                }

                // if the neighbor is the target cell, we found the target
                if(neighbor == target){
                    return traceback(target);
                }
            }
        }

        return null; // we couldn't find the target, but we're done
    }

    /**
     * Draws the maze and the path taken by the searcher.
     * 
     * @param g     the Graphics object used to draw the maze
     * @param scale the scale at which to draw the maze
     */
    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }

}
