import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ElectronicPetSimulator extends JFrame {
    private int hunger = 50;
    private int energy = 50;
    private int happiness = 50;

    private JLabel hungerLabel;
    private JLabel energyLabel;
    private JLabel happinessLabel;

    public ElectronicPetSimulator() throws IOException {
        super("Electronic Pet Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        //setLayout(new FlowLayout());

        // Create hunger panel
        JPanel hungerPanel = new JPanel();
        hungerPanel.setPreferredSize(new Dimension(100, 100));
        hungerPanel.setBorder(BorderFactory.createTitledBorder("Hunger"));
        hungerLabel = new JLabel(String.valueOf(hunger));
        hungerLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        hungerPanel.add(hungerLabel);

        // Create energy panel
        JPanel energyPanel = new JPanel();
        energyPanel.setPreferredSize(new Dimension(100, 100));
        energyPanel.setBorder(BorderFactory.createTitledBorder("Energy"));
        energyLabel = new JLabel(String.valueOf(energy));
        energyLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        energyPanel.add(energyLabel);

        // Create happiness panel
        JPanel happinessPanel = new JPanel();
        happinessPanel.setPreferredSize(new Dimension(100, 100));
        happinessPanel.setBorder(BorderFactory.createTitledBorder("Happiness"));
        happinessLabel = new JLabel(String.valueOf(happiness));
        happinessLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        happinessPanel.add(happinessLabel);

        // Create action buttons
        JButton feedButton = new JButton("Feed");
        feedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hunger += 10;
                if (hunger > 100) {
                    hunger = 100;
                }
                hungerLabel.setText(String.valueOf(hunger));
            }
        });

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                energy -= 10;
                if (energy < 0) {
                    energy = 0;
                }
                happiness += 10;
                if (happiness > 100) {
                    happiness = 100;
                }
                energyLabel.setText(String.valueOf(energy));
                happinessLabel.setText(String.valueOf(happiness));
            }
        });

        JButton sleepButton = new JButton("Sleep");
        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                energy += 10;
                if (energy > 100) {
                    energy = 100;
                }
                hunger -= 10;
                if (hunger < 0) {
                    hunger = 0;
                }
                energyLabel.setText(String.valueOf(energy));
                hungerLabel.setText(String.valueOf(hunger));
            }
        });

//        BufferedImage myPicture = ImageIO.read(new File("pics/house.png"));
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        picLabel.setBounds(0,0,800,800);
//        BufferedImage myPicture1 = ImageIO.read(new File("pics/charFront1.png"));
//        JLabel picLabel1 = new JLabel(new ImageIcon(myPicture1));
//        picLabel1.setBounds(10,10,100,100);

        BufferedImage large=null;
        large = ImageIO.read(new File("pics/house.png"));

        BufferedImage small=null;

        small = ImageIO.read(new File("pics/charFront1.png"));

        int w = Math.max(large.getWidth(), small.getWidth());
        int h = Math.max(large.getHeight(), small.getHeight());

        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels

        JLabel myImage = new JLabel(new ImageIcon(small));
        myImage.setBounds(10,10,100,100);
        JLabel myImage1 = new JLabel(new ImageIcon(large));
        myImage1.setBounds(0,0,800,800);



        // Add components to frame
        add(hungerPanel);
        add(energyPanel);
        add(happinessPanel);
        add(feedButton);
        add(playButton);
        add(sleepButton);
        //add(picLabel);
        //add(picLabel1);
        add(myImage);
        add(myImage1);


        // Set frame properties
        setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new ElectronicPetSimulator();
    }
}

