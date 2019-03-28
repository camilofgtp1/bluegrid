import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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
    private int strokeWidth;
    private Cell currentCell;
    private Cell nextCell;

    public Maze(int cols, int row, int size, int startX, int startY, int strokeWidth) {

        appWindow = new JFrame("Grid");
        backgroundPanel = new JPanel();
        this.strokeWidth = strokeWidth;
        appWindow.add(backgroundPanel);
        //appWindow.getContentPane().add(backgroundPanel);

        backgroundPanel.setBackground(Color.blue);

        columns = cols;  //j
        rows = row;   //i
        cellSize = size;

        currentX = startX;
        currentY = startY;

        windowSize = new Dimension(rows * cellSize + (row), columns * cellSize + (columns));
        running = true;

        gridList = new Cell[rows][columns];

        gridLines = new GridLayout(rows, columns, 0, 0); //rows, cols, hgap, vgap
        backgroundPanel.setLayout(gridLines);

        appWindow.add(backgroundPanel);

        for (int i = 0; i < this.rows; i++) { //rows are horizontal and correspond to the y axis
            for (int j = 0; j < columns; j++) { // cols are vertical correspond to the x axis

                Cell clonerCell = new Cell(cellSize, j, i, this.strokeWidth);

                //add newly created cells to the arraylists<Cell>
                gridList[i][j] = clonerCell;

                //add Cell panel to Background Jpanel
                backgroundPanel.add(clonerCell);
            }
        }

        appWindow.setVisible(true);
        appWindow.setLocationRelativeTo(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setResizable(false);
        appWindow.setPreferredSize(windowSize);
        appWindow.pack();

        currentCell = gridList[currentY][currentX];
    }

    @Override
    public void run() {

        //define and colour first cell
        currentCell.toggleCurrent();
        currentCell.markVisited();

        int i = 0;
        //Choose next random cell, make it current


        while (running) {
            try {
                nextCell = currentCell.getNextCell(gridList);
                if (currentCell != null) {
                    currentCell = nextCell;
                    currentCell.toggleCurrent();
                    currentCell.toggleCurrent();
                    currentCell.markVisited();
                }
                currentCell.repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (NullPointerException e){
                    e.printStackTrace();
            }
        }
    }

}