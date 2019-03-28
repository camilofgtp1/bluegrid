import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Random;

public class Cell extends JPanel implements ActionListener {

    private Line2D top;
    private Line2D right;
    private Line2D bottom;
    private Line2D left;
    private int strokeWidth;
    private int size;
    private int x;
    private int y;

    private boolean[] walls;
    private boolean visited; //paint cyan
    private boolean current; //paint red

    private Timer timer;

    Cell(int size, int x, int y, int stroke) {

        this.x = x;
        this.y = y;
        this.strokeWidth= stroke;

        top = new Line2D.Float(0, 0, size, 0);
        right = new Line2D.Float(size, 0, size, size);
        bottom = new Line2D.Float(size, size, 0, size);
        left = new Line2D.Float(0, size, 0, 0);

        this.size = size;
        this.timer = new Timer(5, this);
        walls = new boolean[]{true, true, true, true};
        visited = false;
        current = false;

        this.setPreferredSize(new Dimension(size, size));
    }

    //return a list of unvisited Cells around this Cell calling it
    public Cell getNextCell(Cell[][] gridList){

        Cell[] freePositions = new Cell[4];
        Cell nextCell;

        int currentX = this.x;
        int currentY = this.y;

        Cell top, right, bottom, left;

        if (currentY == 0) top = null;
        else top = gridList[currentY-1][currentX];

        if(  top!=null && !top.visited) freePositions[0] = top;

        if (currentX == gridList.length-1) right = null;
        else right = gridList[currentY][currentX+1];

        if(right!=null && !right.visited)freePositions[1] = right;

        if (currentY == gridList[0].length-1) bottom = null;
        else bottom = gridList[currentY+1][currentX];

        if(bottom !=null && !bottom.visited)freePositions[2] = bottom;

        if (currentX == 0) left = null;
        else left = gridList[currentY][currentX-1];

        if(left!=null && !left.visited)freePositions[3] = left;

        nextCell= chooseRandom(freePositions);
        return nextCell;
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

    public void markVisited() {
         this.visited = true;
    }

    public void toggleCurrent() {
        if (!this.current) this.current = true;
        else this.current = false;
    }

    /*public void toggleStart() {
        if (!this.current) this.current = true;
        else this.current = false;
    }*/
    public int x(){
        return this.x;
    }
    public int y(){
        return this.y;
    }

    public int getStrokeWidth(){
        return this.strokeWidth;
    }

    public void paintComponent(Graphics g) {
        timer.start();

        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setStroke(new BasicStroke(this.strokeWidth));
        g1.setColor(Color.black);

        if (walls[0]) {
            g1.draw(top);
        }
        if (walls[1]) {
            g1.draw(right);
        }
        if (walls[2]) {
            g1.draw(bottom);
        }
        if (walls[3]) {
            g1.draw(left);
        }

        if (visited == true) {
            g1.setColor(Color.CYAN);
            g1.fillRect(2, 2, size - 3, size - 3);
        }
        if (current == true) {
            g1.setColor(Color.red);
            g1.fillRect(2, 2, size - 3, size - 3);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

