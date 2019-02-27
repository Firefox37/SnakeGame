//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Lab  -

import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.event.KeyEvent;
public class SnakeGameWorldRunner
{
    public static void main()
    {
        //System.setProperty("info.gridworld.gui.watermark", "hide");  
        SnakeWrapWorld test = new SnakeWrapWorld(new BoundedGrid<Actor>(20,20),5,20);
        test.show();
        //test.setMessage("Snake Game! Use Arrow Keys to control the Snake! \nScore: 0");
    }

    
}