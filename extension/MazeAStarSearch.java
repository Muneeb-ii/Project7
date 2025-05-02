/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A class that implements the A* search algorithm to find a path
 * through a maze. The algorithm uses a priority queue to keep track of the cells to explore.
 * 
 */

import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch {
    
    // Priority queue to keep track of the cells to explore
    private PriorityQueue<Cell> priorityQueue;

    /**
     * Comparator to compare the cells based on their f(n) values.
     * f(n) = g(n) + h(n)
     * where g(n) is the steps to the start and h(n) is the steps to the target
     */
    Comparator<Cell> cellComparator = new Comparator<>() {
        @Override
        public int compare(Cell c1, Cell c2) {
            // Compare the f(n) values of the cells
            int fn1 = traceback(c1).size()+Math.abs(c1.getRow() - getTarget().getRow()) + Math.abs(c1.getCol() - getTarget().getCol());
            int fn2 = traceback(c2).size()+Math.abs(c2.getRow() - getTarget().getRow()) + Math.abs(c2.getCol() - getTarget().getCol());
            if (fn1 < fn2) {
                return -1;
            } else if (fn2 < fn1) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    /**
     * Constructor for the MazeAStarSearch class.
     * 
     * @param maze the maze to search
     */
    public MazeAStarSearch(Maze maze){
        super(maze);
        priorityQueue = new Heap<Cell>(cellComparator);
    }

    /**
     * Returns the number of cells remaining in the queue.
     * 
     * @return the number of cells remaining in the queue
     */
    @Override
    public int numRemainingCells(){
        return priorityQueue.size();
    }

    /**
     * Adds a cell to the queue.
     * 
     * @param cell the cell to add
     */
    @Override
    public void addCell (Cell cell){
        priorityQueue.offer(cell);
    }

    /**
     * Finds the next cell to explore by popping the bottom cell from the queue.
     * 
     * @return the next cell to explore, or null if the queue is empty
     */
    @Override
    public Cell findNextCell(){
        if (priorityQueue.size() == 0){
            return null;
        }
        Cell nextCell = priorityQueue.poll();
        return nextCell;
    }

    /**
     * Updates the cell's priority. 
     * 
     * @param cell the cell to update
     */
    @Override
    public void updateCell(Cell cell){
        priorityQueue.updatePriority(cell);
    }

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10, 0.2);
        MazeAStarSearch aStar = new MazeAStarSearch(maze);
        aStar.search(maze.getStart(), maze.getTarget(), true, 100);
    }
}
