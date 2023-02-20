import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private int hunger = 50;
    private int energy = 50;
    private int happiness = 50;

    private JLabel hungerLabel;
    private JLabel energyLabel;
    private JLabel happinessLabel;

    public Main() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(800, 600);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}