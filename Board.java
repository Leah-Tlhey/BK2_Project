import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int ICRAFT_X = 350;
    private final int ICRAFT_Y = 260;
    private final int DELAY = 10;
    private Timer timer;
    private Player player;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        player = new Player(ICRAFT_X, ICRAFT_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            doDrawing(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) throws IOException {

        Graphics2D g2d = (Graphics2D) g;
        BufferedImage background = ImageIO.read(new File("pics/myHouse-1.png"));
        g2d.drawImage(background,0,0,this);
        g2d.drawImage(player.getImage(), player.getX(),
                player.getY(), this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updatePlayer();
        repaint();
    }



    private void updatePlayer() {
        player.move();
        player.updateImage();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            try {
                player.keyReleased(e);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}