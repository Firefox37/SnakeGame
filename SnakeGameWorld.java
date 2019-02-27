//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Lab  -
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.world.World;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class SnakeGameWorld extends ActorWorld
{
    Snake dude;
    Grid<Actor> grid;
    boolean playing;
    Timer timer;
    int speed2, gridS, highscore;
    HighScore high;
    public SnakeGameWorld(Grid<Actor> grd, int speed, int gridSize)
    {

        super(grd);
        high = new HighScore();
        //make a super call
        //add a RunningDude to the world
        playing = false; speed2 = speed; gridS = gridSize;
        gridSize = 11 - (gridSize/10);
        dude = new Snake(speed,gridSize,1);
        grid = getGrid();
        highscore = high.getScore();
        dude.putSelfInGrid(grid,new Location(grid.getNumRows()/2 ,grid.getNumRows()/2 ));
        SnakeTail bob1 = new SnakeTail();
        SnakeTail bob2 = new SnakeTail();
        SnakeTail bob3 = new SnakeTail();
        bob1.putSelfInGrid(grid,new Location(grid.getNumRows()/2,grid.getNumRows()/2-1)); dude.addTail(bob1);
        bob2.putSelfInGrid(grid,new Location(grid.getNumRows()/2,grid.getNumCols()/2-2)); dude.addTail(bob2);
        bob3.putSelfInGrid(grid,new Location(grid.getNumRows()/2,grid.getNumCols()/2-3)); dude.addTail(bob3);
        setMessage("Snake Game! Use Arrow Keys to control the Snake! \nPress Space to Play!  If you want to pause press Space.");
        int r = (int)(Math.random() * grid.getNumRows());
        int c = (int)(Math.random() * grid.getNumRows());
        Location loc = new Location(r,c);
        while(grid.get(loc)!=null && grid.getNeighbors(loc)!=null)
        {
            r = (int)(Math.random() * grid.getNumRows());
            c = (int)(Math.random() * grid.getNumRows());
        }
        SnakeFood yum = new SnakeFood();
        yum.putSelfInGrid(grid,new Location(r,c));
        timer = new Timer(calculateSpeed(speed), new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    step();
                }
            });
    }

    public boolean keyPressed(String description, Location loc)
    {  
        if(description.equals("LEFT")&&dude.getDirection()!=90&&dude.getGrid().isValid(dude.getLocation().getAdjacentLocation(Location.WEST)))
            dude.setDirection(-90);
        if(description.equals("RIGHT")&&dude.getDirection()!=270&&dude.getGrid().isValid(dude.getLocation().getAdjacentLocation(Location.EAST)))
            dude.setDirection(90);
        if(description.equals("UP")&&dude.getDirection()!=180&&dude.getGrid().isValid(dude.getLocation().getAdjacentLocation(Location.NORTH)))
            dude.setDirection(0);
        if(description.equals("DOWN")&&dude.getDirection()!=0&&dude.getGrid().isValid(dude.getLocation().getAdjacentLocation(Location.SOUTH)))
            dude.setDirection(180);
        if(description.equals("ctrl Q")&&dude.getGrid().isValid(dude.getLocation().getAdjacentLocation(Location.SOUTH)))
            dude.easterEgg();
        if(description.equals("SPACE"))
        {
            if(timer.isRunning())
                timer.stop();
            else
                timer.start();
        }
        return true;
    }

    public void step()
    {
        super.step();
        int score = dude.getScore();
        setMessage("Snake Game! Use Arrow Keys to control the Snake! \nScore: " + score + "     |     Highscore: " + highscore);
        doPlay();
    }

    public void doPlay()
    {
        playing = true;
        if(dude.isGameOver())
        {
            timer.stop();
            if(dude.gameOverMessage()==JOptionPane.YES_OPTION)
            {
                high.main(dude.getScore());
                clearBoard();
                getFrame().dispose();
                new SnakeGameWorld(grid,speed2,gridS).show();
            }
            else
            {
                high.main(dude.getScore());
                System.exit(0);
            }
        }
    }

    public int calculateSpeed(int s)
    {
        int finalSpeed;
        s = 6 - s;
        finalSpeed = 50 + (int)(50*(s-1));
        return finalSpeed;
    }

    public void clearBoard()
    {
        Grid<Actor> grid = getGrid();
        ArrayList<Location> locs = grid.getOccupiedLocations();
        for (Location l : locs) 
            grid.get(l).removeSelfFromGrid();
    }
}
