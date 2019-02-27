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
public class Snake extends Bug
{
    public ArrayList<SnakeTail> tails;
    public boolean gameOver;
    public int speed,gridSizeVar, score, numTails, retVal,wrap;
    public Snake(int g, int s, int w)
    {
        setDirection(Location.RIGHT);
        tails = new ArrayList<SnakeTail>();
        score = 0;
        gameOver = false;
        numTails = 3;
        gridSizeVar = g;
        speed = s;
        wrap = w;
    }

    public void act()
    {
        if(!gameOver)
        {
            moveSnake();
        }
    }

    public void moveSnake()
    {
        Grid<Actor> grid = getGrid();
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
        else
            gameOver = true;
    }

    public void placeFood()
    {
        Grid<Actor> grid = getGrid();
        int r,c;
        Location loc;
        do
        {
            r = (int)(Math.random() * grid.getNumRows());
            c = (int)(Math.random() * grid.getNumRows());
            loc = new Location(r,c);
        } while (grid.get(loc)!=null && grid.getNeighbors(loc)!=null);
        SnakeFood yum = new SnakeFood();
        yum.putSelfInGrid(grid,new Location(r,c));
        sound();
    }

    public boolean containsFood()
    {
        Grid<Actor> grid = getGrid();
        for(int r=0; r<grid.getNumRows(); r++)
        {
            for(int c=0; c<grid.getNumCols(); c++)
            {
                if(grid.get(new Location(r,c))instanceof SnakeFood)
                    return true;
            }
        }
        return false;
    }

    public void addTail(SnakeTail bob)
    {
        tails.add(bob);
    }
    
    public void increaseScore()
    {
        score += 50 + speed * gridSizeVar / wrap;
    }

    public void setGridSize(int s)
    {
        gridSizeVar = s;
    }

    public void setSpeed(int s)
    {
        speed = s;
    }

    public int getScore()
    {
        return score;
    }

    public void easterEgg()
    {
        for(int i=0; i<5; i++)
            placeFood();
    }

    public static void sound()
    {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    public int gameOverMessage()
    {
        ImageIcon icon = new ImageIcon("metal-question.png");
        return JOptionPane.showConfirmDialog(null,  "Game Over! \n Your Score: " + getScore() + 
            " \n Would You Like to Play Again?",  "Game Over!",  
            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icon);  
    }
    
    public int getRetVal()
    {
        return retVal;
    }
    
    public boolean isGameOver()
    {
        return gameOver;
    }
}