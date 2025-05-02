/** 
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: A class that implements the depth-first search algorithm to find a path
 * through a maze. The algorithm uses a stack to keep track of the cells to explore and
 * backtracks when it reaches a dead end. 
*/

public class MazeDepthFirstSearch extends AbstractMazeSearch {
    private LinkedList<Cell> stack;

    public MazeDepthFirstSearch(Maze maze){
        super(maze);
        stack = new LinkedList<Cell>();
    }

    /**
     * Returns the number of cells remaining in the stack.
     * 
     * @return the number of cells remaining in the stack
     */
    @Override
    public int numRemainingCells(){
        return stack.size();
    }

    /**
     * Adds a cell to the stack.
     * 
     * @param cell the cell to add
     */
    @Override
    public void addCell (Cell cell){
        stack.push(cell);
    }

    /**
     * Finds the next cell to explore by popping the top cell from the stack.
     * 
     * @return the next cell to explore, or null if the stack is empty
     */
    @Override
    public Cell findNextCell(){
        if (stack.isEmpty()){
            return null;
        }
        Cell nextCell = stack.pop();
        return nextCell;
    }

    /**
     * Updates the cell's priority. In this case, it does nothing because DFS
     * does not require priority updates.
     * 
     * @param cell the cell to update
     */
    @Override
    public void updateCell(Cell cell){
        // No priority update needed for DFS
    }

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10, 0.2);
        MazeDepthFirstSearch dfs = new MazeDepthFirstSearch(maze);
        dfs.search(maze.getStart(), maze.getTarget(), true, 100);
    }
}
