import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class MouseSolver {

    private BufferedImage mouse;
    /*private Vector positionX;
    private Vector positionY;
    private Vector speedX;
    private Vector speedY;
*/
        public MouseSolver(String path){

            this.mouse=this.loadImage(path);
       /*     positionX= new Vector();
            positionY= new Vector();
            speedX= new Vector();
            speedY= new Vector();

            positionX.addElement(0.0);
            positionY.addElement(0.0);
            speedX.addElement(2.0);
            speedY.addElement(2.0);*/

        }

        public BufferedImage loadImage(String path){
            try{
                this.mouse= ImageIO.read(getClass().getResource(path));
            } catch (IOException e){

            e.printStackTrace();
            }
            return this.mouse;
        }

        public BufferedImage grabSprite(int col, int row, int width, int height ){
            BufferedImage img = this.mouse.getSubimage((col*width) -width, (row*height) -height, width, height);

            return img;

        }


}
