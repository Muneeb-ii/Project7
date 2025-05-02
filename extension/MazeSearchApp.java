/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: A GUI application that allows users to visualize different maze search algorithms.
 */

import java.awt.*;
import javax.swing.*;

public class MazeSearchApp extends JFrame {

    private JTextField rowsField;
    private JTextField colsField;
    private JTextField densityField;
    private JComboBox<String> algoBox;
    private JButton runButton;
    private JTextArea resultArea;

    /**
     * Constructor for the MazeSearchApp class.
     * Initializes the GUI components and layout.
     */
    public MazeSearchApp() {
        setTitle("Maze Search Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Rows:"));
        rowsField = new JTextField("20");
        inputPanel.add(rowsField);

        inputPanel.add(new JLabel("Columns:"));
        colsField = new JTextField("20");
        inputPanel.add(colsField);

        inputPanel.add(new JLabel("Obstacle Density (0-1):"));
        densityField = new JTextField("0.3");
        inputPanel.add(densityField);

        inputPanel.add(new JLabel("Algorithm:"));
        algoBox = new JComboBox<>(new String[]{"Depth-First Search", "Breadth-First Search", "A* Search"});
        inputPanel.add(algoBox);

        add(inputPanel, BorderLayout.NORTH);

        // Result area
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Run button
        runButton = new JButton("Run Visualization");
        runButton.addActionListener(e -> startSearch());
        add(runButton, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Starts the maze search based on user input.
     * Validates the input and runs the selected algorithm in a separate thread.
     */
    private void startSearch() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            double density = Double.parseDouble(densityField.getText());
            String algo = (String) algoBox.getSelectedItem();

            resultArea.setText("Running " + algo + "...\n");
            runButton.setEnabled(false);

            // Run search in background
            new Thread(() -> {
                long startTime = System.currentTimeMillis();

                Maze maze = new Maze(rows, cols, density);
                AbstractMazeSearch searcher;
                switch (algo) {
                    case "Breadth-First Search":
                        searcher = new MazeBreadthFirstSearch(maze);
                        break;
                    case "A* Search":
                        searcher = new MazeAStarSearch(maze);
                        break;
                    default:
                        searcher = new MazeDepthFirstSearch(maze);
                }

                // visualize with delay 20ms
                LinkedList<Cell> path = searcher.search(
                    maze.getStart(), maze.getTarget(), true, 20);
                long endTime = System.currentTimeMillis();

                int explored = maze.countVisitedCells();
                int pathSize = (path == null) ? 0 : path.size();
                long elapsed = endTime - startTime;

                SwingUtilities.invokeLater(() -> {
                    resultArea.append(String.format(
                        "Cells Explored: %d\nPath Length: %d\nTime (ms): %d\n", 
                        explored, pathSize, elapsed));
                    runButton.setEnabled(true);
                });
            }).start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.",
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeSearchApp::new);
    }
}