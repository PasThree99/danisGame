import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
// import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    /* Window width in pixels */
    static final int GAME_WIDTH = 1400;
    
    /* Window height in pixels */
    static final int GAME_HEIGHT = 800;

    /* Game Dimension */
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    
    /* Num of blocks in the game (change BLOCK_SIZE if they no longer fit) */
    static final int NUM_OF_BLOCKS = 6;

    /* Block size in pixels (square with side BLOCKS_SIZE px) */
    static final int BLOCKS_SIZE = 100;
    
    /* This is the higgest number that will appear in the blocks and objective */
    static final int MAX_NUMBER = 10;
    
    /* Target number */
    int objectiveNumber;

    /* Game thread in charge of repaint the window and helper objects */
    Thread gameThread;
    Image image;
    Graphics graphics;

    /* Random number generator */
    Random random;

    /* Array of blocks */
    Block[] block;

    /* User selector */
    Selector selector;

    /* Incharge of displaying objectiveNumber in screen */
    Objective objective;

    /* First number picked by the user. If no selection has been made it equals -1 */
    int userSelection1;

    /* Second number picked by the user. If no selection has been made it equals -1 */
    int userSelection2;
    
    /* Score p1 vs p2 */
    Score score;

    /* Who´s turn is it? p1 or p2? */
    int player;

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
        
        score = new Score(GAME_WIDTH - 2 * BLOCKS_SIZE, 0, BLOCKS_SIZE);
       
        random = new Random();
        setNewNumbers();

        player = 1;

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
        objectiveNumber = random.nextInt(MAX_NUMBER + 1);
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
                num = random.nextInt(MAX_NUMBER + 1);
                block[i].setValue(num);
            }
        }
        
        objective.setValue(objectiveNumber);

        userSelection1 = -1;
        userSelection2 = -1;
        
    }

    public void draw(Graphics g){
        /* 
         * Re-draw echch element of the game in case 
         * the position has changed
         */
        for (int i = 0; i < NUM_OF_BLOCKS; i++){
            block[i].draw(g);
        }
        selector.draw(g);
        objective.draw(g);
        score.draw(g);
    }

    public void move(){
        selector.move();
    }
    
    public void run(){
        /* Game main loop */
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

    public void changePlayer(){
        if(player == 1){
            player ++;
        }
        else{
            player --;
        }
    }

    public void increaseScore(){
        if(player == 1){
            score.inceaseP1();
        }
        else{
            score.inceaseP2();
        }
        changePlayer();
    }

    public void validateAnswer(){
        if(userSelection1 + userSelection2 == objectiveNumber){
            showMessageDialog(null, "Correct!");
            increaseScore();
        }
        else{
            showMessageDialog(null, "Error :(");
            changePlayer();
        }
    }

    public void doOnEnter(KeyEvent e){
        int idx;

        if(e.getKeyCode() != KeyEvent.VK_ENTER){
            return;
        }

        /* 
         * TODO: add restriction so user can't 
         * select the same index twice
         */
        
        idx = selector.getActualBlock() - 1;
        if(userSelection1 < 0){
            userSelection1 = block[idx].getValue();
        }
        else{
            userSelection2 = block[idx].getValue();

            /* Check if the user is correct, update score and change player */
            validateAnswer();

            /* Create new case for next player */
            setNewNumbers();
        }
    }

    // Action listener
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            System.out.println("Key pressed!");
            selector.keyPressed(e);
            doOnEnter(e);

        }
        public void keyReleased(KeyEvent e){   
            /* May be usefull */
        }
    }
}