import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Objective extends Rectangle{
    int size;
    int value;


    Objective(int xPos, int yPos, int size){
        /* 
         * This class will show the objective 
         * number the user must sum up to
         */
        super (xPos, yPos, size, size);
    }

    public void move(){
        /* Update x and y if needed */
    }

    public void draw(Graphics g){
        /* Add font for showing the number in the box */
        Font font = new Font("Arial", Font.BOLD, 60);
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(String.valueOf(value));
        int textHeight = metrics.getHeight();

        /* Center the text label on the box */
        // TODO: Correcly center xText and yText
        int xText = x + (width - textWidth) / 2;
        int yText = y + (height - textHeight) / 2 + metrics.getAscent();
        
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.drawString(String.valueOf(value), xText, yText);

    }

    public void setValue(int newVal){
        value = newVal;
    }

}