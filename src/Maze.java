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


//@todo: define end of generating
    public Maze(int cols, int row, int size, int startX, int startY, int strokeWidth) {

        /*
        * appWindow --> JFrame
        *
        * background --> JPanel
        * JLayeredPanel --> JLayeredPanel
        *
        * mouse -> Jpanel
        * */
        rows = row;
        columns = cols;
        cellSize = size;
        windowSize = new Dimension((rows * cellSize), (columns * cellSize));

        this.strokeWidth = strokeWidth;

        //window
        appWindow = new JFrame("Grid");

        //background and layoutManager
        backgroundPanel = new JPanel(new GridLayout(rows, columns, 0, 0));
        backgroundPanel.setPreferredSize(windowSize);

        mouse = new MouseSolver();

        appWindow.add(backgroundPanel, 0);
        appWindow.add(mouse, 0);

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
        appWindow.setResizable(true);
        appWindow.setPreferredSize(windowSize);
        appWindow.pack();
        appWindow.setVisible(true);

        currentCell = gridList[currentY][currentX];
    }

    public int getUnvisited(){

        int counter =0;
        for(int i = 0; i<gridList.length;i++ ){
            for (int j =0;j<gridList[i].length;j++){
                if (gridList[i][j].isVisited() == false){
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