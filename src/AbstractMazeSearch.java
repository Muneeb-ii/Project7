/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: An abstract class that is used to represent different maze searching
 * algorithms. 
 * 
*/

public class AbstractMazeSearch {
    
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
}
