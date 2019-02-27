import java.awt.*;
import java.awt.Color;
import info.gridworld.gui.*;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.world.*;
import java.util.ArrayList;
import javax.swing.*;
import java.net.*;
public class SnakeWrap extends Snake
{
    public SnakeWrap(int s, int g, int w)
    {
        super(s,g,w);
    }
    
    public void moveSnake()
    {
        int r,c;
        Grid<Actor> grid = getGrid();
        int dir = getDirection();
        Location loc = getLocation();
        Location loc1 = loc.getAdjacentLocation(getDirection());
        if(canMove())
        {
            moveTo(loc1);
            SnakeTail bob = new SnakeTail();
            bob.putSelfInGrid(grid, loc);
            tails.add(0,bob);
            if(containsFood())
            {
                tails.remove(numTails).removeSelfFromGrid();
            }
            else
            {
                placeFood();
                increaseScore();
                numTails++;
            }
        }
        else if (!grid.isValid(loc1))
        {
            if(dir==Location.SOUTH)
            {
                c = loc.getCol();
                moveTo(new Location(0,c));
            }
            if(dir==Location.NORTH)
            {
                c = loc.getCol();
                moveTo(new Location(grid.getNumRows()-1,c));
            }
            if(dir==Location.WEST)
            {
                r = loc.getRow();
                moveTo(new Location(r,grid.getNumCols()-1));
            }
            if(dir==Location.EAST)
            {
                r = loc.getRow();
                moveTo(new Location(r,0));
            }
        }
        else
            gameOver = true;
    }
}