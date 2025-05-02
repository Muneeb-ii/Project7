/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: A class that implements the wall follower algorithm to find a path
 * through a maze. The algorithm uses a linked list to keep track of the cells to explore
 * and follows the wall on the left side.
 */

public class MazeWallFollowerSearch extends AbstractMazeSearch {
    private LinkedList<Cell> frontier;   // single-cell frontier
    private Direction facing;      // current heading

    /**
     * Direction enum to represent the four possible directions
     * (NORTH, EAST, SOUTH, WEST) and their corresponding row/column offsets.
     */
    private enum Direction {
        NORTH(-1, 0),  // move up one row
        EAST (0,  1),  // move right one column
        SOUTH(1,  0),  // move down one row
        WEST (0, -1);  // move left one column
    
        final int dr, dc;                     // “delta row” and “delta column”
        Direction(int dr, int dc) {          // constructor stores those offsets
            this.dr = dr;
            this.dc = dc;
        }
    
        Direction turnRight() {               
            // values() = [NORTH, EAST, SOUTH, WEST]
            // ordinal() gives 0,1,2,3 for those
            // (ordinal()+1) % length wraps 3→0
            return values()[ (ordinal() + 1) % values().length ];
        }
    }

    /**
     * Constructor for the MazeWallFollowerSearch class.
     * 
     * @param maze the maze to search
     */
    public MazeWallFollowerSearch(Maze maze) {
        super(maze);
        frontier = new LinkedList<Cell>();
        facing = Direction.EAST;
    }

    /**
     * Adds a cell to the frontier.
     * 
     * @param next the cell to add
     */
    @Override
    public void addCell(Cell next) {
        frontier.clear();
        frontier.add(next);
    }

    /**
     * Finds the next cell to explore by peeking at the top cell in the frontier.
     *
     * @return the next cell to explore
     */
    @Override
    public Cell findNextCell() {
        return frontier.peek();
    }

    /**
     * Updates the cell's priority. In this case, it does nothing because wall
     * follower does not require priority updates.
     */
    @Override
    public void updateCell(Cell next) {
        // No update needed for wall follower
    }

    /**
     * Returns the number of cells remaining in the frontier.
     */
    @Override
    public int numRemainingCells() {
        return frontier.isEmpty() ? 0 : 1;
    }

    /**
     * Searches the maze for a path from the start cell to the target cell using
     * the wall follower algorithm. The search is visualized if the display parameter
     * 
     * @param start the starting cell
     * @param target the target cell
     * @param display whether to display the search process
     * @param delay the delay between each step in milliseconds
     * @return a linked list of cells representing the path from start to target,
     *         or null if no path is found
     */
    @Override
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {
        // Initialize search
        super.setStart(start);
        this.setTarget(target);
        addCell(start);
        setCur(start);
        start.setPrev(start);

        // Create a null display window
        MazeSearchDisplay displayWindow = null;

        // If display is true, create a new display window
        if(display){
            displayWindow = new MazeSearchDisplay(this, 20);
        }

        // Walk until no frontier or target found
        while (numRemainingCells() > 0) {
            Cell cur = findNextCell();
            setCur(cur);

            // Visualization step
            if (display) {
                try { Thread.sleep(delay); } catch (InterruptedException ignored) {}
                displayWindow.repaint();
            }

            // Try moves: forward, right, back, left
            for (int turn = 0; turn < 4; turn++) {
                Direction dir = facing;
                for (int k = 0; k < turn; k++) {
                    dir = dir.turnRight();
                }
                int newRow = cur.getRow() + dir.dr;
                int newCol = cur.getCol() + dir.dc;
                if ((getMaze().getRows() > newRow && newRow >= 0) &&
                (getMaze().getCols() > newCol && newCol >= 0) && 
                getMaze().get(newRow, newCol).getType() == CellType.FREE) 
                {
                    Cell next = getMaze().get(newRow, newCol);
                    if (!next.visited()) {
                        next.setPrev(cur);
                        facing = dir;
                        addCell(next);
                    }
                    break;
                }
            }

            if (cur.equals(target)) {
                return traceback(target);
            }
        }
        return null;
    }
}
