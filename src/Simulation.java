/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: This class is used to run simulations for the maze searching algorithms. 
 */

public class Simulation {

    /**
     * Finds the relationship between density of obstacles and 
     * probability of reaching the target - Analysis 1
     */
    public void analysisOne(){

        for(int j = 0; j < 10; j++){
            double density = j/10.0;
            int count = 0;
            for(int i = 0; i < 10; i++){
                Maze maze = new Maze(20,20, density);
                MazeAStarSearch astar = new MazeAStarSearch(maze);
                if(astar.search(maze.getStart(), maze.getTarget(), false, 0) != null){
                    count++;
                }
            }
            System.out.println("Density: " + density + " - Paths Found: " + count + " out of 10");
            System.out.println("Probability: " + count/10.0 +"\n");
        }
    }

    /**
     * Compares the length of paths found by DFS, BFS, and A* search algorithms
     * for different grid sizes - Analysis 2
     */
    public void analysisTwo() {
        for (int i = 10; i <= 100; i += 10) {
            int size = i;
            double density = 0.2;

            Maze maze = new Maze(size, size, density);

            // Run DFS on the maze
            LinkedList<Cell> pathDfs = new MazeDepthFirstSearch(maze)
                .search(maze.getStart(), maze.getTarget(), false, 0);
            int lenDfs = (pathDfs == null) ? 0 : pathDfs.size();
            maze.reset();

            // Run BFS on the same maze
            LinkedList<Cell> pathBfs = new MazeBreadthFirstSearch(maze)
                .search(maze.getStart(), maze.getTarget(), false, 0);
            int lenBfs = (pathBfs == null) ? 0 : pathBfs.size();
            maze.reset();

            // Run A* on the same maze
            LinkedList<Cell> pathAstar = new MazeAStarSearch(maze)
                .search(maze.getStart(), maze.getTarget(), false, 0);
            int lenAstar = (pathAstar == null) ? 0 : pathAstar.size();

            System.out.println(
                "Grid Size: " + size + " x " + size +
                "\n Length of path (DFS): " + lenDfs +
                "\n Length of path (BFS): " + lenBfs +
                "\n Length of path (A*): " + lenAstar + "\n"
            );
        }
    }

    /**
     * Compares the number of cells explored by DFS, BFS, and A* search algorithms
     * for different grid sizes - Analysis 3
     */
    public void analysisThree() {
        for (int i = 10; i <= 100; i += 10) {
            int size = i;
            double density = 0.2;

            Maze maze = new Maze(size, size, density);

            // Run DFS on the maze
            LinkedList<Cell> pathDfs = new MazeDepthFirstSearch(maze)
                .search(maze.getStart(), maze.getTarget(), false, 0);
            int cellsExploredDfs = maze.countVisitedCells();
            maze.reset();

            // Run BFS on the same maze
            LinkedList<Cell> pathBfs = new MazeBreadthFirstSearch(maze)
                .search(maze.getStart(), maze.getTarget(), false, 0);
            int cellsExploredBfs = maze.countVisitedCells();
            maze.reset();

            // Run A* on the same maze
            LinkedList<Cell> pathAstar = new MazeAStarSearch(maze)
                .search(maze.getStart(), maze.getTarget(), false, 0);
            int cellsExploredAstar = maze.countVisitedCells();

            System.out.println(
                "Grid Size: " + size + " x " + size +
                "\n Number of cells explored (DFS): " + cellsExploredDfs +
                "\n Number of cells explored (BFS): " + cellsExploredBfs +
                "\n Number of cells explored (A*): " + cellsExploredAstar + "\n"
            );
        }
    }
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        System.out.println("Analysis 1: Density vs Probability of reaching target");
        simulation.analysisOne();
        System.out.println("Analysis 2: Length of paths found by DFS, BFS, and A*");
        simulation.analysisTwo();
        System.out.println("Analysis 3: Number of cells explored by DFS, BFS, and A*");
        simulation.analysisThree();
    }
}