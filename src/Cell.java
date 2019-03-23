import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Cell extends JPanel {

    private Line2D top;
    private Line2D right;
    private Line2D bottom;
    private Line2D left;
    private int size;
    private int x;
    private int y;

    private boolean[] walls;
    private boolean visited; //paint cyan
    private boolean starting; //paint red
    private boolean active;


    Cell(int size, int x, int y) {

        this.x = x;
        this.y = y;

        top = new Line2D.Float(0, 0, size, 0);
        right = new Line2D.Float(size, 0, size, size);
        bottom = new Line2D.Float(size, size, 0, size);
        left = new Line2D.Float(0, size, 0, 0);

        this.size = size;

        walls = new boolean[]{true, true, true, true};
        visited = false;
        starting = false;
        active = false;

        this.setPreferredSize(new Dimension(size, size));
    }

    //if cell is marked as active then get unvisited neighbors

    /*The main index ArrayList contains another list for each col
     *each of them contains the Cells created in the loop.
     * Each cell can be visited and only one cell can be the active cell
     * */

    /*needs to know the list to check the indexes and give back the neighbors indexes back
     * active cell indexes as parameters and then add to 2d arraylist an arraylist of indexes of posible neighbors
     * [[x, y], [x1,y1], [x2, y2]]*/

    //return a list of unvisited Cells around this Cell calling it
    public Cell[] getOpenPositions(Cell[][] gridList) {

        Cell[] freePositions = new Cell[4];

        int currentX = this.x;
        int currentY = this.y;

        Cell top, right, bottom, left;

        if (currentY == 0) top = null;
        else top = gridList[currentY-1][currentX];
        freePositions[0] = top;

        if (currentX == gridList.length) right = null;
        else right = gridList[currentY][currentX+1];
        freePositions[1] = right;

        if (currentY == gridList[0].length) bottom = null;
        else bottom = gridList[currentY+1][currentX];
        freePositions[2] = bottom;

        if (currentX == 0) left = null;
        else left = gridList[currentX - 1][currentY + 1];
        freePositions[3] = left;

        return freePositions;
    }

    public void toggleVisited() {
        if (!this.visited) this.visited = true;
        else this.visited = false;
    }

    public void toggleActive() {
        if (!this.active) this.active = true;
        else this.active = false;
    }

    public void toggleStart() {
        if (!this.starting) this.starting = true;
        else this.starting = false;
    }
    public int x(){
        return this.x;
    }
    public int y(){
        return this.y;
    }

    public void paintComponent(Graphics g) {

        Graphics2D g1 = (Graphics2D) g;
        g1.setStroke(new BasicStroke(3));
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
        if (starting == true) {
            g1.setColor(Color.red);
            g1.fillRect(2, 2, size - 3, size - 3);
        }
    }

}

