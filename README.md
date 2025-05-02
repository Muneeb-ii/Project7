# MazeSearch

## Project Overview

MazeSearch implements and visualizes three classic pathfinding algorithms, Depth-First Search (DFS), Breadth-First Search (BFS), and A* Search, on randomly generated grid mazes with obstacles. It includes a Swing-based GUI for interactive exploration, plus a “wall-follower” human-style walker extension. A companion simulation framework runs automated experiments to study how obstacle density and grid size affect reachability, path length, and search effort.

## Core Features

- **Grid & Cell Model**  
  - `CellType` enum (`FREE`, `OBSTACLE`) and `Cell` class track location, obstacle-status, and visited/prev pointers.  
  - `Maze` class builds a 2D grid, supports random obstacle density, start/target selection, neighbor lookup, reset, and drawing.

- **Search Algorithms**  
  - **DFS** (`MazeDepthFirstSearch`): stack-backed deep dive.  
  - **BFS** (`MazeBreadthFirstSearch`): queue-backed level-by-level expansion.  
  - **A\*** (`MazeAStarSearch`): priority-queue with Manhattan-distance heuristic for shortest-path.  
  - **Wall-Follower** (`MazeWallFollowerSearch`): always-turn-right human-style walker with backtracking.

- **Visualization**  
  - Interactive GUI (`MazeSearchApp`) prompts for rows, cols, density, and algorithm, then animates the search and displays:  
    1. Cells explored  
    2. Path length  
    3. Time taken (ms)

- **Simulation & Experiments**  
  - `Simulation.java` runs three analyses over 10 densities (0.0–0.9) or grid sizes (10–100):  
    1. Reachability vs. density  
    2. Path lengths  
    3. Cells explored  
  - Prints the experiment results in the terminal.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Git (optional)

### Compilation

```bash
git clone https://github.com/Muneeb-ii/Searching_on_a_Grid.git
cd Searching_on_a_Grid/src
javac *.java 
```

### Running the Simulation
```bash
# Analysis only, no GUI:
cd Searching_on_a_Grid/src
java Simulation

# Launch interactive GUI:
cd Searching_on_a_Grid/extension
java MazeSearchApp
```

## Code Organization
```Bash
src/
├── CellType.java           # FREE / OBSTACLE enum
├── Cell.java               # Cell model with visited/prev
├── Maze.java               # Grid builder, neighbors, draw/reset
├── AbstractMazeSearch.java # Base for DFS/BFS/A*
├── MazeDepthFirstSearch.java
├── MazeBreadthFirstSearch.java
├── MazeAStarSearch.java
├── MazeSearchDisplay.java  # Swing canvas for animation
└── Simulation.java         # Automated experiments & reporting

extension/
├── MazeWallFollowerSearch.java
├── MazeSearchApp.java      # Swing GUI launcher
```
## Extensions
- Wall-Follower: human-style right-hand rule walker with backtracking.
- GUI: Swing interface to configure and visualize any search.

## Acknowledgments
- Original lab specifications and starter code by CS231 instructors.
- Oracle Swing Tutorial series:
  - [Trail: Creating a GUI With Swing – Oracle Java™ Tutorials](https://docs.oracle.com/javase/tutorial/uiswing/)
  - [How to Use BorderLayout – Oracle Java™ Tutorials](https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html)
  - [How to Use Combo Boxes – Oracle Java™ Tutorials](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
  - [How to Use Text Fields – Oracle Java™ Tutorials](https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html)
- [What does SwingUtilities.invokeLater do? – Stack Overflow](https://stackoverflow.com/questions/6567870/what-does-swingutilities-invokelater-do)
- [Enum (Java Platform SE 8) – Oracle Help Center](https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html)
- [Pausing Execution with Sleep – Oracle Java™ Tutorials](https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html)
- [Maze-solving algorithm – Wikipedia](https://en.wikipedia.org/wiki/Maze-solving_algorithm)

# License
This project is released under the [MIT License](LICENSE.MD)
