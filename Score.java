import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{
    int p1Score;
    int p2Score;

    Score(int xPos, int yPos, int size){
        super (xPos, yPos, 2 * size, size);
        p1Score = 0;
        p2Score = 0;
    }

    public void draw(Graphics g){
        Font font = new Font("Arial", Font.BOLD, 30);
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(String.valueOf(p1Score));
        int textHeight = metrics.getHeight();
        // TODO: Center xText and yText
        int xTextP1 = x + (width / 3 - textWidth) / 2;
        int yTextP1 = y + (height - textHeight) / 2 + metrics.getAscent();
        int xTextP2 = x + (3 * width / 2 - textWidth) / 2;
        int yTextP2 = y + (height - textHeight) / 2 + metrics.getAscent();
        int xDash = x + (width - textWidth) / 2;
        int yDash = y + (height - textHeight) / 2 + metrics.getAscent();

        g.setFont(font);
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
        g.setColor(Color.red);
        g.drawString(String.valueOf(p1Score), xTextP1, yTextP1);
        g.drawString(String.valueOf(p2Score), xTextP2, yTextP2);
        g.drawString("-", xDash, yDash);

    }

    public void inceaseP1(){
        p1Score ++;
    }

    public void inceaseP2(){
        p2Score ++;
    }

}