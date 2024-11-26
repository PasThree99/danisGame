import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Selector extends Rectangle{
    int size;
    int value;
    int yPos = 100;

    // GLOBALS

    int GAME_WIDTH;
    int GAME_HEIGHT;
    Dimension SCREEN_SIZE;
    int NUM_OF_BLOCKS;
    int BLOCKS_SIZE;
    int actBlock;
    


    Selector(int xPos, int yPos, int size){
        /*
         * The selector is used by the user to choose which block
         * he's going to pick to sup up to the objective value
         */
        super (xPos, yPos, size, size);
        actBlock = 1;

    }

    public void setGlobals(int wid, int hei, int numblk, int blksz){
        /* Get all the globals from GamePanel*/
        GAME_HEIGHT = hei;
        GAME_WIDTH = wid;
        NUM_OF_BLOCKS = numblk;
        BLOCKS_SIZE = blksz;
        SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }

    public void move(){
        // Update x and y if needed
        y = yPos;
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.drawRect(x, y, width, height);
    }

    public void setYPos(int newYPos){
        yPos = newYPos;
    }

    public void keyPressed(KeyEvent e){
        System.out.println("here");
        /* If the key up is pressed the user
         * wants to go up. The array of blocks is
         * displayed in a descending order, meaning that,
         * if we want to go up, that means that we must 
         * move to the previuos index.
         * This also applies to the down key, but 
         * viceversa, if the user wants to go down we
         * must go to the next block, increasing the block
         * count. 
         * Also, for both cases, check if we are still inside 
         * the limits that the game marks (aka, we are not al block 0 or
         * bloc NUM_OF_BLOCKS + 1)
         * */
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(actBlock > 1){
                setYPos(y - (BLOCKS_SIZE + 10));
                actBlock --;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(actBlock < NUM_OF_BLOCKS){
                setYPos(y + BLOCKS_SIZE + 10);
                actBlock ++;
            }
        }
    }

    public int getActualBlock(){
        return actBlock;
    }
}