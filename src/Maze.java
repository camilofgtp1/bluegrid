import com.sun.javafx.scene.control.skin.CellSkinBase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Maze {

    //
    public static void checkNeighbours(Cell current, ArrayList<Cell> index){

        if(current.marked){

        }
    }

    public static void main(String[] args){

        JFrame frame = new JFrame("Maze");
        JPanel background = new JPanel();
        frame.add(background);
        //frame.getContentPane().add(background);

        background.setBackground(Color.blue);

        int cols=10;
        int rows = 10;
        int cellSize=50;

        GridLayout grid = new GridLayout(rows,cols,0,0);
        background.setLayout(grid);

        frame.add(background);
        ArrayList<Cell> index= new ArrayList<>();

        for(int i=0;i<rows;i++){
            for (int j =0; j<cols;j++){

                Cell c = new Cell(cellSize);
                background.add(c);
                index.add(c);

            }
        }

        Cell current=index.get(0);

        current.marked=true;

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
    }
}