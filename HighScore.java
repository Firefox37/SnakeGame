import java.text.*;
import chn.util.*;
public class HighScore
{
    public static void main(int score)
    {
        if(checkScore(score))
        {
            String fileName = "highscore.txt";
            FileOutput out = new FileOutput(fileName);
            out.println(score);
            out.close();
        }
    }

    public static boolean checkScore(int newScore)
    {
        FileInput  infile = new FileInput("highscore.txt");
        int oldScore = infile.readInt();
        infile.close();
        if(oldScore<newScore)
            return true;
        else
            return false;
    }

    public static void reset()
    {
        String fileName = "highscore.txt";
        FileOutput out = new FileOutput(fileName);
        out.println(0);
        out.close();
    }
    
    public static int getScore()
    {
        FileInput  infile = new FileInput("highscore.txt");
        return infile.readInt();
    }
}
