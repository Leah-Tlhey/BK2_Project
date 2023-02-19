import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class MoveBall extends JPanel {
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private static final Color BACKGROUND = Color.black;
    private static final Color BALL_COLOR = Color.yellow;
    private static final int BALL_WIDTH = 30;
    private static final int PREF_WIDTH = 500;
    private static final int PREF_HEIGHT = PREF_WIDTH;
    private static final Dimension PREF_SIZE = new Dimension(PREF_WIDTH, PREF_HEIGHT);
    private int x = PREF_WIDTH / 2;
    private int y = PREF_HEIGHT / 2;

    public MoveBall() {
        setBackground(BACKGROUND);

        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
        actionMap.put(UP, new AbstractAction() {
            public void actionPerformed(ActionEvent arg0) {
                y--;
                repaint();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
        actionMap.put(DOWN, new AbstractAction() {
            public void actionPerformed(ActionEvent arg0) {
                y++;
                repaint();
            }
        });

        // etc for left and right

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(BALL_COLOR);
        g2.fillOval(x - BALL_WIDTH / 2, y - BALL_WIDTH / 2,
                BALL_WIDTH, BALL_WIDTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return PREF_SIZE;
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Move Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MoveBall());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}