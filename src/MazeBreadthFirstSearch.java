/** 
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: A class that implements the breadth-first search algorithm to find a path
 * through a maze. The algorithm uses a queue to keep track of the cells to explore and
 * backtracks when it reaches a dead end. 
*/

public class MazeBreadthFirstSearch extends AbstractMazeSearch {
    private LinkedList<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze){
        super(maze);
        queue = new LinkedList<Cell>();
    }

    /**
     * Returns the number of cells remaining in the queue.
     * 
     * @return the number of cells remaining in the queue
     */
    @Override
    public int numRemainingCells(){
        return queue.size();
    }

    /**
     * Adds a cell to the queue.
     * 
     * @param cell the cell to add
     */
    @Override
    public void addCell (Cell cell){
        queue.offer(cell);
    }

    /**
     * Finds the next cell to explore by popping the top cell from the queue.
     * 
     * @return the next cell to explore, or null if the queue is empty
     */
    @Override
    public Cell findNextCell(){
        if (queue.isEmpty()){
            return null;
        }
        Cell nextCell = queue.poll();
        return nextCell;
    }

    /**
     * Updates the cell's priority. In this case, it does nothing because BFS
     * does not require priority updates.
     * 
     * @param cell the cell to update
     */
    @Override
    public void updateCell(Cell cell){
        // No priority update needed for BFS
    }

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10, 0.2);
        MazeBreadthFirstSearch bfs = new MazeBreadthFirstSearch(maze);
        bfs.search(maze.getStart(), maze.getTarget(), true, 100);
    }
}
