import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int dx;
    private int dy;

    private String direction = "DOWN";
    private int animate;
    private int animateTimer = 1;

    private boolean moving = false;

    private String action = "NONE";
    private int actionImage = 0;
    
    public int energyLevel = 50;
    public int hungerLevel = 50;
    public int happinessLevel = 50;


    private final String[] myPics = {"pics/front1-1.png","pics/front2-1.png","pics/frontw1-1.png","pics/frontw2-1.png",
            "pics/back1-1.png","pics/back2-1.png","pics/backw1-1.png","pics/backw2-1.png",
            "pics/left1-1.png","pics/left2-1.png","pics/leftw1-1.png","pics/leftw2-1.png",
            "pics/right1-1.png","pics/right2-1.png","pics/rightw1-1.png","pics/rightw2-1.png"};
    private final String[] yogaFrames = {"pics/front1-1.png","pics/front2-1.png","pics/front1-1.png","pics/frontw1-1.png",
            "pics/frontw2-1.png","pics/front1-1.png","pics/front2-1.png","pics/stretchL.png","pics/front2-1.png",
            "pics/front1-1.png","pics/bend.png","pics/front1-1.png","pics/front2-1.png", "pics/stretchR.png",
            "pics/front2-1.png","pics/front1-1.png", "pics/frontw1-1.png", "pics/frontw2-1.png", "pics/front1-1.png"};
    public Player(int x, int y) {
        super(x, y);
        initPlayer();
    }

    private void initPlayer() {

        loadImage(myPics[0]);
        getImageDimensions();
    }
    public void updateImage(){
        if(action.equals("YOGA")){
            if(actionImage<yogaFrames.length){
                loadImage(yogaFrames[actionImage]);
                getImageDimensions();
                if (animateTimer % 80 == 0) {
                    actionImage++;
                }
                incrementTimer(80);
            }else{
                action="NONE";
                actionImage = 0;
            }
        }
        if(action.equals("NONE")) {
            int i;
            if (moving) {
                i = 2;
            } else {
                i = 0;
            }
            switch (direction) {
                case ("DOWN"):
                    break;
                case ("UP"):
                    i += 4;
                    break;
                case ("LEFT"):
                    i += 8;
                    break;
                case ("RIGHT"):
                    i += 12;
                    break;
            }
            if (animate == i || animate == i + 1) {
                if (animateTimer % 40 == 0) {
                    if (animate == i) {
                        animate++;
                    } else {
                        animate--;
                    }
                }
            } else {
                animate = i;
            }
            if(moving) {
                incrementTimer(40);
            }else{
                incrementTimer(60);
            }
            loadImage(myPics[animate]);
            getImageDimensions();
        }
    }
    private void incrementTimer(int n){
        animateTimer = animateTimer+1;
        if(animateTimer>n){
            animateTimer=1;
        }
    }
    private void addAnimate(int i){
        animate = animate+i;
    }
    private void setDirection(String direct){
        direction = direct;
        changeMoving(true);
    }
    private void changeMoving(boolean move){
        moving = move;
    }
    private boolean canMove(){
        if(!action.equals("NONE")){
            return false;
        }
        if(x<100){
            x=100;
            return false;
        }
        if(x>600){
            x=600;
            return false;
        }
        if(y<100){
            y=100;
            return false;
        }
        if(y>340){
            y=340;
            return false;
        }
        if(x<250&&y<205){
            if(direction=="LEFT"){
                x=250;
                return false;
            }
            if(direction=="UP"){
                y=205;
                return false;
            }
        }
        if(x>405&&y<200){
            if(direction=="RIGHT"){
                x=405;
                return false;
            }
            if(direction=="UP"){
                y=200;
                return false;
            }
        }
        return true;
    }
    public void move() {
        if(canMove()){
            x += dx;
            y += dy;
        }
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                setDirection("LEFT");
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
                setDirection("RIGHT");
                dx = 1;
                break;
            case KeyEvent.VK_UP:
                setDirection("UP");
                dy = -1;
                break;
            case KeyEvent.VK_DOWN:
                setDirection("DOWN");
                dy = 1;
                break;
            case KeyEvent.VK_Y:
                doYoga();
                break;
            case KeyEvent.VK_E:
                eat();
                break;
            case KeyEvent.VK_S:
                sleep();
                break;
        }

    }
    public void doYoga(){
        action = "YOGA";
        energyLevel -= 10;
        if (energyLevel < 0) {
            energyLevel = 0;
        }
        happinessLevel += 10;
        if (happinessLevel > 100) {
            happinessLevel = 100;
        }
    }
    public void sleep(){
        //action="SLEEP";
        energyLevel += 10;
        if (energyLevel > 100) {
            energyLevel = 100;
        }
        hungerLevel -= 10;
        if (hungerLevel < 0) {
            hungerLevel = 0;
        }
    }
    public void eat(){
        //action="EAT";
        hungerLevel += 10;
        if (hungerLevel > 100) {
            hungerLevel = 100;
        }
    }

    public void keyReleased(KeyEvent e) throws InterruptedException {
        changeMoving(false);
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT:
                dx = 0;
                break;
            case KeyEvent.VK_UP,KeyEvent.VK_DOWN:
                dy = 0;
                break;
        }
    }
}
