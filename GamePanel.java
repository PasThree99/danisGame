import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
// import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1400;
    static final int GAME_HEIGHT = 800;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int NUM_OF_BLOCKS = 4;
    static final int BLOCKS_SIZE = 100;
    int objectiveNumber;


    Thread gameThread;
    Image image;
    Random random;
    Graphics graphics;
    Block[] block;
    Selector selector;
    Objective objective;
    int userSelection1;
    int userSelection2;

    GamePanel(){


        // Create and instanciate the array of blocks
        block = new Block[NUM_OF_BLOCKS];
        for(int i = 0; i < NUM_OF_BLOCKS; i++ ){
            block[i] = new Block(GAME_WIDTH / 2 - BLOCKS_SIZE, i *  (BLOCKS_SIZE + 10) + 100, BLOCKS_SIZE);
        }

        objective = new Objective(0, 0, BLOCKS_SIZE);

        // Create the selector and pass the globals
        selector = new Selector(GAME_WIDTH / 2 - BLOCKS_SIZE, 100, BLOCKS_SIZE);
        selector.setGlobals(GAME_WIDTH, GAME_HEIGHT, NUM_OF_BLOCKS, BLOCKS_SIZE);
       
        random = new Random();
        setNewNumbers();


        this.setPreferredSize(SCREEN_SIZE);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.gameThread = new Thread(this);
        this.gameThread.start();

    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }


    public void setNewNumbers(){
        int solIndex1;
        int solIndex2;
        int sol1;
        int sol2;
        int num;

        /* initTheBlocks and objective */
        objectiveNumber = random.nextInt(11);
        solIndex1 = random.nextInt(NUM_OF_BLOCKS);
        solIndex2 = random.nextInt(NUM_OF_BLOCKS);
        
        /* Make sure indexes are different */
        while(solIndex1 == solIndex2)
            solIndex2 = random.nextInt(NUM_OF_BLOCKS);
        
        /* Get a random int between 1 and objective number inclusive
         * To ensure a solution, subtract that number to the objetive 
         * to get the second solution
         */
        sol1 = random.nextInt(objectiveNumber) + 1;
        sol2 = objectiveNumber - sol1;
    
        // Fill the blocks and objective
        for (int i = 0; i < NUM_OF_BLOCKS; i++){
            if(i == solIndex1){
                block[i].setValue(sol1);
            }
            else if(i == solIndex2){
                block[i].setValue(sol2);
            }
            else{
                num = random.nextInt(11);
                block[i].setValue(num);
            }

        }

        
        objective.setValue(objectiveNumber);

        userSelection1 = -1;
        userSelection2 = -1;
        
    }

    public void draw(Graphics g){

        for (int i = 0; i < NUM_OF_BLOCKS; i++){
            block[i].draw(g);
        }
        selector.draw(g);
        objective.draw(g);
    }

    public void move(){
        // paddle1.move();
        // paddle2.move();
        // ball.move();
        selector.move();
    }
    
    public void run(){
        // game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; // nano seconds
        double delta = 0;

        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                move();
                repaint();
                delta --;
                System.out.println("test");
            }
        }

    }

    public void  validateAnswer(){
        if(userSelection1 + userSelection2 == objectiveNumber){
            showMessageDialog(null, "Correct!");
            // Increase score
        }
        else{
            showMessageDialog(null, "Error :(");
        }
    }

    public void doOnEnter(KeyEvent e){
        int idx;
        System.out.println("Enter here!");

        if(e.getKeyCode() != KeyEvent.VK_ENTER){
            return;
        }

        /* 
         * TODO: add restriction so user can't 
         * select the same index twice
         */
        
        idx = selector.getActualBlock();
        if(userSelection1 < 0){
            userSelection1 = block[idx].getValue();
        }
        else{
            userSelection2 = block[idx].getValue();
            validateAnswer();
            setNewNumbers();
        }
    }

    // Action listener
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            // paddle1.keyPressed(e);
            // paddle2.keyPressed(e);
            System.out.println("Key pressed!");
            selector.keyPressed(e);
            // this.keyPressed(e);
            // doOnEnter(e);

        }
        public void keyReleased(KeyEvent e){
            // paddle1.keyReleased(e);
            // paddle2.keyReleased(e);

        }
    }
}