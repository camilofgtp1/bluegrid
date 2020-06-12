import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cell extends JPanel implements ActionListener {

    private int x;
    private int y;
    private boolean[] walls;
    private boolean visited; //paint cyan

    private Line2D top;
    private Line2D right;
    private Line2D bottom;
    private Line2D left;

    private int strokeWidth;
    private int size;
    private boolean current; //paint red
    private ArrayList<Line2D> cellSides;
    private Timer timer;

    Cell(int size, int x, int y, int stroke) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.strokeWidth = stroke;
        cellSides= new ArrayList<>();
        this.timer = new Timer(5, this);

        //add nodes object to connect the lines that make the cell
        top = new Line2D.Float(0, 0, this.size, 0);
        right = new Line2D.Float(this.size, 0, this.size, this.size);
        bottom = new Line2D.Float(this.size, this.size, 0, this.size);
        left = new Line2D.Float(0, this.size, 0, 0);
        cellSides.add(top);
        cellSides.add(right);
        cellSides.add(bottom);
        cellSides.add(left);

        walls = new boolean[]{true, true, true, true};
        visited = false;
        current = false;

        this.setMinimumSize(new Dimension(this.size, this.size));
        this.setPreferredSize(new Dimension(this.size, this.size));
    }

    //return a list of unvisited Cells around this Cell calling it
    public Cell getNextCell(Cell[][] gridList) {

        Cell[] freePositions = new Cell[4];
        Cell nextCell = null;

        Cell top, right, bottom, left;

        if (this.y == 0) top = null;
        else top = gridList[this.y - 1][this.x];

        if (top != null && !top.visited) freePositions[0] = top;

        if (this.x == gridList[0].length - 1) right = null;
        else right = gridList[this.y][this.x + 1];

        if (right != null && !right.visited) freePositions[1] = right;

        if (this.y == gridList[0].length - 1) bottom = null;
        else bottom = gridList[this.y + 1][this.x];
        if (bottom != null && !bottom.visited) freePositions[2] = bottom;

        if (this.x == 0) left = null;
        else left = gridList[this.y][this.x - 1];
        if (left != null && !left.visited) freePositions[3] = left;


        nextCell = chooseRandom(freePositions);

        return nextCell;
    }


    public static Cell chooseRandom(Cell[] list) {

        Random r = new Random();
        int rand;

        for(int i=0; i<10;i++){
            rand = r.nextInt(4);
            if(list[rand] != null) {
                return  list[rand];
            }
        }
        return null;

    }

    public void markVisited() {
        this.visited = true;
    }

    public void toggleCurrent() {
        this.current = !this.current;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {

        this.walls = walls;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        timer.start();

        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setStroke(new BasicStroke(this.strokeWidth));


        if (current) {
            g1.setColor(Color.red);
            g1.fillRect(2, 2, size - 3, size - 3);
        }
        if (visited) {
            g1.setColor(Color.CYAN);
            g1.fillRect(0, 0, size , size );

        }


        for (int i =0; i <walls.length;i++){

            if(walls[i]){
                g1.setColor(Color.blue);
            } else {
                g1.setColor(Color.cyan);
            }
            g1.draw(cellSides.get(i));
        }
    }


    public void connect(Cell next) {

        //top
        if (this.y - next.y == 1 && this.x - next.x == 0) {
            this.walls[0]=false;
            next.walls[2]=false;
        }
        //right
        if (this.x - next.x == -1 && this.y -next.y == 0 ) {
            this.walls[1]=false;
            next.walls[3]=false;
        }
        //bottom
        if (this.y - next.y == - 1 && this.x - next.x == 0) {
            this.walls[2]=false;
            next.walls[0]=false;
        }

        //left
        if (this.x - next.x == 1 && this.y -next.y == 0) {
            this.walls[3]=false;
            next.walls[1]=false;
        }

        this.repaint();
        next.repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

