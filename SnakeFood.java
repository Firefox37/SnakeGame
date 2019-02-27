//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Lab  -
import java.awt.event.*;
import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;

public class SnakeFood extends Flower
{
    public SnakeFood()
    {
        setColor(Color.GREEN);
    }

    public void act()
    {
    }

    public boolean containsFood()
    {
        Grid<Actor> grid = getGrid();
        boolean yes = false;
        for(int r=0; r<grid.getNumRows(); r++)
        {
            for(int c=0; c<grid.getNumCols(); c++)
            {
                if(grid.get(new Location(r,c))instanceof SnakeFood)
                    yes = true;
            }
        }
        return yes;
    }
}