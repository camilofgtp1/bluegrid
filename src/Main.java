import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {



    public static void main(String[] args){

        JFrame frame = new JFrame("Maze");
        JPanel background = new JPanel();
        frame.add(background);
        //frame.getContentPane().add(background);

        background.setBackground(Color.blue);

        int cols=20;
        int rows = 20;
        int cellSize=40;


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

        Cell current= new Cell(cellSize);

        current=index.get(0);
        current.visited=true;

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();



    }
}
