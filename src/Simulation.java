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
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.analysisOne();
    }
}
