import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class Click_Me_To_Play here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Click_Me_To_Play
{
    public static void main(String[] args)
    {
        String name = System.getProperties().getProperty("user.name");
        JOptionPane.showMessageDialog(null,
            "Welcome, " + name + " to Snake Game created by: Ryan Hawrylew",
            "Snake Game",
            JOptionPane.PLAIN_MESSAGE);
        SnakePanel bob = new SnakePanel();
    }
}
