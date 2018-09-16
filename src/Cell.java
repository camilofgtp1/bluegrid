import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;

public class Cell extends JPanel {

    private Line2D top;
    private Line2D right;
    private Line2D bottom;
    private Line2D left;
    private int size;

    private boolean[] walls;
    public boolean visited;
    public boolean marked;

    Cell(int size) {

        top = new Line2D.Float(0, 0, size, 0);
        right = new Line2D.Float(size, 0, size, size);
        bottom = new Line2D.Float(size, size, 0, size);
        left = new Line2D.Float(0, size, 0, 0);

        this.size=size;

        walls=new boolean[]{true, true, true, true };
        visited =false;
        marked= false;

        this.setPreferredSize(new Dimension(size, size));

    }

    public void checkNeighbours(Cell ref){

    }

    public void paintComponent(Graphics g) {


        /*to this point the cell is just lines, now lets give a colored area*/

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

            if(visited==true) {
                g1.setColor(Color.CYAN);
                g1.fillRect(2, 2, size-3, size-3);
            }
            if(marked==true) {
                g1.setColor(Color.red);
                g1.fillRect(2, 2, size-3, size-3);
            }


    }


}

