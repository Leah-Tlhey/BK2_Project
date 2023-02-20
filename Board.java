{

    private final int ICRAFT_X = 350;
    private final int ICRAFT_Y = 260;
    private final int DELAY = 10;
    private Timer timer;
    private Player player;

    private int hunger = 50;
    private int energy = 50;
    private int happiness = 50;

    private JLabel hungerLabel;
    private JLabel energyLabel;
    private JLabel happinessLabel;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

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
                player.doYoga();
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
        add(hungerPanel);
        add(energyPanel);
        add(happinessPanel);
        add(feedButton);
        add(playButton);
        add(sleepButton);


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
