import javax.swing.*;
import java.awt.*;

public class Maze {

    public static void main(String[] args) {

        JFrame appWindow = new JFrame("Grid");
        JPanel backgroundPanel = new JPanel();

        appWindow.add(backgroundPanel);
        //appWindow.getContentPane().add(backgroundPanel);

        backgroundPanel.setBackground(Color.blue);

        int columns = 10;  //j
        int rows = 10;   //i
        int cellSize = 50;

        int currentX = 0;
        int currentY = 0;

        Cell[][] gridList;
        gridList = new Cell[rows][columns];

        GridLayout gridLines = new GridLayout(rows, columns, 0, 0); //rows, cols, hgap, vgap
        backgroundPanel.setLayout(gridLines);

        appWindow.add(backgroundPanel);

        //Cloner cell populates the grid with cells
        for (int i = 0; i < rows; i++) { //rows are horizontal and correspond to the y axis
            for (int j = 0; j < columns; j++) { // cols are vertical correspond to the x axis

                Cell clonerCell = new Cell(cellSize, j, i);

                //add newly created cells to the arraylists<Cell>
                gridList[i][j] = clonerCell;

                //add Cell panel to Background Jpanel
                backgroundPanel.add(clonerCell);
            }
        }

        Cell currentCell = gridList[currentY][currentX];
        currentCell.toggleStart();
        currentCell.markVisited();
        currentCell.toggleActive();

        Cell[] freeNeighbors = currentCell.getOpenPositions(gridList);


        /****Test the freeNeighbours**/
        System.out.println("free neighbors: ");
        if (freeNeighbors != null) {
            for (Cell c : freeNeighbors) {
                if(c!=null) System.out.println("X= " +c.x() + "Y= " + c.y() + "\n");
            }
        }

        appWindow.setVisible(true);
        appWindow.setLocationRelativeTo(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setResizable(false);
        appWindow.pack();
    }
}