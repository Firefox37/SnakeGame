import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.util.Properties;
import java.util.Enumeration;
import info.gridworld.gui.*;
import info.gridworld.world.*;
import javax.swing.*;
import java.awt.Dialog;
import java.awt.Container;
public class MAIN_Runner
{
    static int firstAns;
    static int secondAns;
    static boolean ans2;
    public static void main()
    {
        ActorWorld test;
        String name = System.getProperties().getProperty("user.name");
        JOptionPane.showMessageDialog(null,
            "Welcome, " + name + " to Snake Game created by: Ryan Hawrylew",
            "Snake Game",
            JOptionPane.PLAIN_MESSAGE);
        int speed = showFirstMenu();
        int size = showSecondMenu();
        if(!showWrapMessage())
            test = new SnakeGameWorld(new BoundedGrid<Actor>(size,size),speed,size);
        else
            test = new SnakeWrapWorld(new BoundedGrid<Actor>(size,size),speed,size);
        showPlayScreen();
        test.show();
    }

    public static int showFirstMenu()
    {
        Object[] options = {5,4,3,2,1};
        ImageIcon icon = new ImageIcon("metal-question.png");
        String question = "What speed do you want for snake? \n1 is slow(4 grids/sec)  |  5 is fast(20 grids/sec)";
        JOptionPane pane = new JOptionPane(question,
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,icon,options,options[2]);
        JDialog dialog = pane.createDialog(null, "Snake Game Speed Selection");
        dialog.setSize(450,150);
        dialog.show();
        try {
            firstAns = (int)pane.getValue();
        } catch (NullPointerException e) {
            showErrorMenu();
            showFirstMenu();
        }
        return firstAns;
    }

    public static int showSecondMenu()
    {
        ImageIcon icon = new ImageIcon("metal-question.png");
        String question = "What grid size do you want for snake? \nUse integers from 10-100 \nBeware grids bigger than 50 might not fit on the screen or be too small!";
        Object choice = JOptionPane.showInputDialog(null,question,"Snake Game Grid Size Selection",JOptionPane.PLAIN_MESSAGE,icon,null,"20");
        try {
            secondAns = Integer.parseInt((String)choice);
        } catch (NumberFormatException e) {
            showErrorMenu();
            showSecondMenu();
        }
        return secondAns;
    }

    public static void showErrorMenu()
    {
        JOptionPane.showMessageDialog(null,
            "Your choice was not recognized or you closed the window. \nPlease redo.",
            "Snake Game Error",
            JOptionPane.ERROR_MESSAGE);
    }

    public static void showPlayScreen()
    {
        ImageIcon icon = new ImageIcon("metal-info.png");
        JOptionPane.showMessageDialog(null,
            "Get ready to play Snake Game now!",
            "Snake Game Play",
            JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static boolean showWrapMessage()
    {
        String ans="";
        Object[] options = {"Yes","No"};
        ImageIcon icon = new ImageIcon("metal-question.png");
        String question = "Do you want the snake to be able to wrap-around? \nIf yes, snake will go off one side of the screen and come back on the other.";
        JOptionPane pane = new JOptionPane(question,
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,icon,options,null);
        JDialog dialog = pane.createDialog(null, "Snake Game Wrap-Around Selection");
        dialog.show();
        ans = (String)pane.getValue();
        try {
            ans2 = ans.equals("Yes");
        } catch (NullPointerException e) {
            showErrorMenu();
            showWrapMessage();
        }
        return ans2;
    }
}
