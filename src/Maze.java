import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Maze implements Runnable, ActionListener {

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

    private Stack<Cell> stack;


    public Maze(int cols, int row, int size, int startX, int startY, int strokeWidth) {

        appWindow = new JFrame("Grid");
        backgroundPanel = new JPanel();
        this.strokeWidth = strokeWidth;
        appWindow.add(backgroundPanel);

        backgroundPanel.setBackground(Color.BLACK);

        columns = cols;  //j
        rows = row;   //i
        cellSize = size;

        currentX = startX;
        currentY = startY;
        this.stack= new Stack<>();

        windowSize = new Dimension((rows * cellSize), (columns * cellSize));
        running = true;

        gridList = new Cell[rows][columns];

        gridLines = new GridLayout(rows, columns, 0, 0);
        System.out.println(gridLines.toString() + " grid string");
        //rows, cols, hgap, vgap
        backgroundPanel.setLayout(gridLines);

        appWindow.add(backgroundPanel);

        for (int i = 0; i < this.rows; i++) { //rows are horizontal and correspond to the y axis
            for (int j = 0; j < columns; j++) { // cols are vertical correspond to the x axis

                Cell clonerCell = new Cell(cellSize, j, i, this.strokeWidth);

                clonerCell.repaint();
                //add newly created cells to the arraylists<Cell>
                gridList[i][j] = clonerCell;

                //add Cell panel to Background Jpanel
                backgroundPanel.add(clonerCell);
            }
        }

        appWindow.setVisible(true);
        appWindow.setLocationRelativeTo(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setResizable(true);
        appWindow.setPreferredSize(windowSize);
        appWindow.pack();

        currentCell = gridList[currentY][currentX];
    }



    @Override
    public void run() throws NullPointerException {

        //@TODO: Optimize loop
        //define and color first cell
        currentCell.markVisited();

        //Choose next random cell, make it current
        while (running) {
            nextCell = currentCell.getNextCell(gridList);
            stack.push(nextCell);

            if (nextCell != null) {
                currentCell.connect(nextCell);
                currentCell.markVisited();
                currentCell.repaint();
                System.out.println("top "+ currentCell.getWalls()[0]);
                System.out.println("right "+ currentCell.getWalls()[1]);
                System.out.println("bottom "+ currentCell.getWalls()[2]);
                System.out.println("left "+ currentCell.getWalls()[3 ]);

            }
            if(stack.contains(nextCell)){
                currentCell = nextCell;
            }

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("******************************End*****************************************");

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //@todo: add on space pause maze generation

    }
}