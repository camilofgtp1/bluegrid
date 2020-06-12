import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MouseSolver extends JPanel implements ActionListener, KeyListener {

    private BufferedImage spritesheet;
    private ArrayList<BufferedImage> walk;
    private int width;
    private int height;
    private BufferedImage mouse;
    private int xPos;
    private int yPos;
    private int speed;

    public Timer timer;

    public MouseSolver(int cellSize) {

        this.spritesheet = this.loadImage("spritesheet.png");
        width = spritesheet.getWidth();
        height = spritesheet.getHeight();
        xPos = 0;
        yPos = 0;
        walk = new ArrayList<>();
        mouse = this.spritesheet.getSubimage(1, 1, width / 8, ((height / 2) / 5));

        //add sequence of walking mouse to walk array
        walk.add(mouse);
        timer = new Timer(4, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.setMaximumSize(new Dimension(cellSize, cellSize));
    }

    public BufferedImage loadImage(String path) {
        try {
            this.spritesheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {

            e.printStackTrace();
        }
        return this.spritesheet;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i=0;i < walk.size(); i++) {
            g1.drawImage(walk.get(i), xPos, yPos, this);
            if(i== walk.size()){
                i=0;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xPos+=this.speed;
        yPos+=this.speed;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

       int keyCode = e.getKeyCode();
        System.out.println(keyCode);
       switch (keyCode) {
            case KeyEvent.VK_UP:
                this.yPos -= 2;
                System.out.println("UP");
                break;
            case KeyEvent.VK_RIGHT:
                this.xPos += 2;
                System.out.println("RIGHT");
                break;
            case KeyEvent.VK_DOWN:
                this.yPos += 2;
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                this.xPos -= 2;
                System.out.println("LEFT");
                break;
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
