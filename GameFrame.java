import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame{
    
    GamePanel panel;
    
    GameFrame(){
        /* 
         * A Game frame is like the wrapper: it contains
         * the close, minimize and those buttons.
         * Form here, we call the GamePanel class which 
         * actually contains all the elements of the game
         */
        System.out.println("Starting game");
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Queue Game");
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
}