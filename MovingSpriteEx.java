import java.awt.*;
import javax.swing.*;

public class MovingSpriteEx extends JFrame {
    private int hunger = 50;
    private int energy = 50;
    private int happiness = 50;

    private JLabel hungerLabel;
    private JLabel energyLabel;
    private JLabel happinessLabel;
    public MovingSpriteEx() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(400, 300);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MovingSpriteEx ex = new MovingSpriteEx();
            ex.setVisible(true);
        });
    }
}