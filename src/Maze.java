import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    private MouseSolver mouse;
    private JLayeredPane jLayeredPane;

    public Maze(int cols, int row, int size, int startX, int startY, int strokeWidth) {

        rows = row;
        columns = cols;
        cellSize = size;

        windowSize = new Dimension((rows * cellSize), (columns * cellSize));
        Dimension backSize =  new Dimension((rows * cellSize+80), (columns * cellSize+80));
        Dimension layeredSize =  new Dimension((rows * cellSize+80), (columns * cellSize+80));
        Dimension mouseSize = new Dimension(cellSize, cellSize);

        this.strokeWidth = strokeWidth;

        //window
        appWindow = new JFrame("Grid");
        appWindow.setPreferredSize(windowSize);

        jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(layeredSize);

        //background and layoutManager
        GridLayout gridLayout = new GridLayout(rows, columns, 0, 0);

        backgroundPanel = new JPanel(gridLayout);

        backgroundPanel.setPreferredSize(backSize);

        //mouse = new MouseSolver(cellSize);

        appWindow.add(jLayeredPane);
        appWindow.add(backgroundPanel);
        //jLayeredPane.add(backgroundPanel);
        //jLayeredPane.add(mouse, JLayeredPane.PALETTE_LAYER);
        //backgroundPanel.add(mouse);

        currentX = startX;
        currentY = startY;
        this.stack= new Stack<>();

        running = true;
        gridList = new Cell[rows][columns];

        for (int i = 0; i < this.rows; i++) { //rows are horizontal and correspond to the y axis
            for (int j = 0; j < columns; j++) { // cols are vertical correspond to the x axis

                Cell clonerCell = new Cell(cellSize, j, i, this.strokeWidth);

                //add newly created cells to the arraylists<Cell>
                gridList[i][j] = clonerCell;

                //add Cell panel to Background Jpanel
                backgroundPanel.add(clonerCell);
            }
        }

        appWindow.setLocationRelativeTo(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setResizable(false);
        appWindow.setPreferredSize(windowSize);
        appWindow.pack();
        appWindow.setVisible(true);

        currentCell = gridList[currentY][currentX];
    }

    public int getUnvisited(){

        int counter =0;
        for (Cell[] cells : gridList) {
            for (Cell cell : cells) {
                if (!cell.isVisited()) {
                    counter++;
                }
            }
        }

        return counter;
    }


    @Override
    public void run() throws NullPointerException {

        //Step 1 mark first cell as visited
        currentCell.markVisited();

        // 2. while there are unvisited cells
        while (this.getUnvisited()>0) {

            //3. choose random neighbor
            try {

                nextCell = currentCell.getNextCell(gridList);
                nextCell.toggleCurrent();
            if (nextCell != null) {
                //4.push current to stack
                 stack.push(nextCell);

                //5. remove walls between neighbors
                currentCell.connect(nextCell);

                //6. make the chosen cell the current cell and mark it as visited
                currentCell=nextCell;

                currentCell.markVisited();

               //7. if stack is not empty pop a cell and make it the current
            } else if(!stack.empty()) {
               currentCell = stack.pop();

               nextCell = currentCell.getNextCell(gridList);
            }
            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //@todo: add on space pause maze generation

    }
}