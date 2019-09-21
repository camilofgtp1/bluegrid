import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MouseSolver extends JPanel implements ActionListener {

    private BufferedImage mouse;
    private ArrayList<BufferedImage> walk;
    private int width;
    private int height;
    private BufferedImage sprite;
    private int xPos;
    private int yPos;
    public Timer timer;

        public MouseSolver(){

            this.mouse=this.loadImage("spritesheet.png");
            width= mouse.getWidth();
            height= mouse.getHeight();
            xPos=0;
            yPos=0;
            walk= new ArrayList<>();
            sprite = this.mouse.getSubimage((1) , (1) , width/5, ((height/2)/5));
            walk.add(sprite);
            timer = new Timer(4, this);
        }

        public BufferedImage loadImage(String path){
            try{
                this.mouse= ImageIO.read(getClass().getResource(path));
            } catch (IOException e){

            e.printStackTrace();
            }
            return this.mouse;
        }

        public void paintComponent(Graphics g){
            timer.start();

            try{
                Graphics2D g1 = (Graphics2D) g;
                g1.drawImage(walk.get(0),   xPos, yPos, null);
            }catch (NullPointerException e){
                    e.printStackTrace();
            }

        }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
