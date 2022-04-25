import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GrafikTE20Del1 extends Canvas {
    private final int height = 600;
    private final int width = 800;
    Graphics dbg;
    Image image;
    int treeX = 200;
    int treeY = 192;
    int sunX = 700;
    int sunY = 10;
    int houseX = 400;
    int houseY = 200;
    int cloudX = -100;
    int cloudY = 30;
    int treemoveX = 290;
    int treemoveY = 192;

    public GrafikTE20Del1() {
        JFrame frame = new JFrame("Del 1");
        this.setSize(width, height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void draw(Graphics g) {
        drawGround(g, 0, 250);
        drawSky(g, 0, 0);
        drawSun(g, sunX, sunY);
        drawCloud(g, cloudX, cloudY);
        drawTree(g, treeX,treeY);
        drawTree(g, treeX+30,treeY);
        drawTree(g, treeX+60,treeY);
        drawHouse(g, houseX, houseY);
        drawTreeMove(g,treemoveX, treemoveY);
    }

    public void paint(Graphics g) {
        if (image == null) {
            // skapa en andra skärm i minnet som vi kan rita till
            image = createImage(width, height);
            if (image == null) {
                System.out.println("image is still null!");
                return;
            } else {
                dbg = image.getGraphics();
            }
        }
        update();
        draw(dbg);
        // Kopiera innehållet i minnet ut på den fysiska skärmen
        g.drawImage(image, 0, 0, null);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * Öka x-koordinaten med 5 i varje uppdatering och om den kommer ut mot högerkanten så flytta den längst till
     * vänster
     */
    private void update() {
        cloudX += 5;
        if (cloudX > 800)
            cloudX = -120;
    }

    @Override
    public synchronized KeyListener[] getKeyListeners() {
        return super.getKeyListeners();
    }


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            treemoveX = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            treemoveX = 1;
        }
    }
    private void drawSky(Graphics g, int x, int y) {
        g.setColor(new Color(0x0099dd));
        g.fillRect(0,0,800,250);
    }

    private void drawGround(Graphics g, int x, int y) {
        g.setColor(new Color(0x009900));
        g.fillRect(0,0,800,600);
    }

    private void drawCloud(Graphics g, int x, int y) {
        g.setColor(new Color(0xf9f9f9));
        g.fillOval(x,y,70,70);
        g.fillOval(x + 40,y,70,70);
        g.fillOval(x + 80,y,70,70);
        g.fillOval(x + 40,y -40 ,70,70);
    }

    private void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(0x00DD33));
        g.fillRect(x,y,20,40);
        g.setColor(new Color(0x905000));
        g.fillRect(x+8,y+40,4,20);
    }
    private void drawTreeMove(Graphics g, int x, int y) {
        g.setColor(new Color(0x00DD33));
        g.fillRect(x,y,20,40);
        g.setColor(new Color(0x905000));
        g.fillRect(x+8,y+40,4,20);
    }
    private void drawSun(Graphics g, int x, int y) {
        g.setColor(new Color(0xffff00));
        g.fillOval(x,y,70,70);
    }


    private void drawHouse(Graphics g, int x, int y) {
        g.setColor(new Color(0xff0000));
        g.fillRect(x,y,50,50);
        g.setColor(new Color(0x905000));
        int[] xs = {390, 425, 460};
        int[] ys = {200, 160, 200};
        Polygon shape = new Polygon(xs,ys,3);
        g.fillPolygon(shape);
        g.setColor(new Color(0x905000));
        g.fillRect(x+20,y+30,10,20);
    }


    public static void main(String[] args) {
        GrafikTE20Del1 exempel = new GrafikTE20Del1();
    }
}