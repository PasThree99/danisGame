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
        super (xPos, yPos, size, size);
        actBlock = 1;

    }

    public void setGlobals(int wid, int hei, int numblk, int blksz){
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
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("AAAAAAAAAA");
            if(actBlock > 1){
                setYPos(y - (BLOCKS_SIZE + 10));
                actBlock --;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("AAAAAAAAAA");
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