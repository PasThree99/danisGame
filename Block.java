import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Block extends Rectangle{
    int size;
    int value;


    Block(int xPos, int yPos, int size){
        super (xPos, yPos, size, size);

    }

    public void move(){
        // Update x and y if needed
    }

    public void draw(Graphics g){
        Font font = new Font("Arial", Font.BOLD, 60);
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(String.valueOf(value));
        int textHeight = metrics.getHeight();
        // TODO: Center xText and yText
        int xText = x + (width - textWidth) / 2;
        int yText = y + (height - textHeight) / 2 + metrics.getAscent();
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.drawString(String.valueOf(value), xText, yText);

    }

    public void setValue(int newVal){
        value = newVal;
    }
    public int getValue(){
        return value;
    }
}