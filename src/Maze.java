import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Maze implements Runnable {

    private int columns;  //j
    private int rows;   //i
    private int cellSize;

    private int currentX;
    private int currentY;

    private JFrame appWindow;
    private Cell[][] gridList;
    private GridLayout gridLines;
    private JPanel backgroundPanel;
    private boolean running;
    private Dimension windowSize;

    public Maze(int cols, int row, int size, int startX, int startY) {

        appWindow = new JFrame("Grid");
        backgroundPanel = new JPanel();

        appWindow.add(backgroundPanel);
        //appWindow.getContentPane().add(backgroundPanel);

        backgroundPanel.setBackground(Color.blue);

        columns = cols;  //j
        rows = row;   //i
        cellSize = size;

        currentX = startX;
        currentY = startY;

        windowSize = new Dimension(columns*cellSize, row*cellSize);
        running = true;




        gridList = new Cell[rows][columns];

        gridLines = new GridLayout(rows, columns, 0, 0); //rows, cols, hgap, vgap
        backgroundPanel.setLayout(gridLines);

        appWindow.add(backgroundPanel);

        appWindow.setVisible(true);
        appWindow.setLocationRelativeTo(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setResizable(true);
        appWindow.setPreferredSize(windowSize);
        appWindow.pack();

        for (int i = 0; i < this.rows; i++) { //rows are horizontal and correspond to the y axis
            for (int j = 0; j < columns; j++) { // cols are vertical correspond to the x axis

                Cell clonerCell = new Cell(cellSize, j, i);

                //add newly created cells to the arraylists<Cell>
                gridList[i][j] = clonerCell;

                //add Cell panel to Background Jpanel
                backgroundPanel.add(clonerCell);
            }
        }

    }

    public static Cell chooseRandom(Cell[] list) {
        Random r = new Random();

        int rand = r.nextInt(4);
        while (list[rand] == null) {
            rand = r.nextInt(4);
            return list[rand];
        }
        return list[rand];
    }

    @Override
    public void run() {

        Cell currentCell = gridList[currentY][currentX];
        currentCell.toggleStart();
        currentCell.markVisited();
        currentCell.toggleActive();
        Cell[] freeNeighbors = currentCell.getOpenPositions(gridList);

        Cell nextCell = chooseRandom(freeNeighbors);
        if (nextCell != null) {
            nextCell.markVisited();
            currentCell = nextCell;
        }
    }
}