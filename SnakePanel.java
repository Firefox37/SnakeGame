import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;
public class SnakePanel extends JFrame implements ActionListener
{
    CheckboxGroup cg1=new CheckboxGroup();
    Checkbox c1=new Checkbox("1", cg1, false);
    Checkbox c2=new Checkbox("2", cg1, false);
    Checkbox c3=new Checkbox("3", cg1, true);
    Checkbox c4=new Checkbox("4", cg1, false);
    Checkbox c5=new Checkbox("5", cg1, false);

    CheckboxGroup cg2=new CheckboxGroup();
    Checkbox b1=new Checkbox("Yes", cg2, false);
    Checkbox b2=new Checkbox("No", cg2, true);

    JTextArea t1=new JTextArea("",1,400);
    JTextArea t2=new JTextArea("",1,400);
    JTextArea t3=new JTextArea("",1,400);
    JTextArea t4=new JTextArea("",1,400);

    JButton ok = new JButton("DONE");
    JButton cancel = new JButton("Cancel");

    int speed;
    int size;
    boolean wrap;
    ActorWorld test;
    public SnakePanel()
    {
        super("Snake Game Selections");
        Container c = getContentPane();
        setLayout(null);
        setSize(400,600);
        String question = "What speed do you want for snake? \n \n1 is slow(4 grids/sec)  |  5 is fast(20 grids/sec)";
        String question2 = "What grid size do you want for snake? \nUse integers from 10-100. \nBeware grids bigger than 50 might not fit on the screen or be too small!";
        String question3 = "Do you want the snake to be able to wrap-around? \nIf yes, snake will go off one side of the screen \n       and come back on the other.";

        add(c1);    add(b1);
        add(c2);    add(b2);
        add(c3);    add(t1);
        add(c4);    add(t2);
        add(c5);    add(t3);
        add(t4);    add(ok);
        add(cancel);

        cancel.addActionListener(this);
        ok.addActionListener(this);

        JScrollPane j1=new JScrollPane(t3,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        c.add(j1);

        t1.setBounds(25,25,350,60);     t1.setEditable(false);
        t1.setText(question);           

        c1.setBounds(25,85,75,20);     c2.setBounds(100,85,75,20);     c3.setBounds(175,85,75,20);
        c4.setBounds(250,85,75,20);     c5.setBounds(325,85,75,20);

        t2.setBounds(25,200,350,60);    t2.setEditable(false);
        t2.setText(question2);

        j1.setBounds(100,260,200,30);   t3.setEditable(true);
        t3.setText("20");

        t4.setBounds(25,390,350,60);    t4.setEditable(false);
        t4.setText(question3);

        b1.setBounds(100,450,75,20);     b2.setBounds(250,450,75,20);

        ok.setBounds(100,525,75,25);     cancel.setBounds(250,525,75,25);

        setLocationRelativeTo(null);
        t1.setBackground(c.getBackground());
        t2.setBackground(c.getBackground());
        t3.setBackground(Color.white);
        t4.setBackground(c.getBackground());
        setVisible(true);
        validate();
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource()==cancel)
            System.exit(1);
        if(evt.getSource()==ok)
        {
            try
            {
                size = Integer.parseInt(t3.getText());
            }
            catch(NumberFormatException e)
            {
                showErrorMenu();
                return;
            }
            if(size<10||size>100)
            {
                showErrorMenu();
                return;
            }
            speed = Integer.parseInt(cg1.getSelectedCheckbox().getLabel());
            String wrapString = cg2.getSelectedCheckbox().getLabel();
            if(wrapString.equals("Yes"))
                wrap = true;
            else if(wrapString.equals("No"))
                wrap = false;
            dispose();
            if(!wrap)
                test = new SnakeGameWorld(new BoundedGrid<Actor>(size,size),speed,size);
            else
                test = new SnakeWrapWorld(new BoundedGrid<Actor>(size,size),speed,size);
            showPlayScreen();
            test.show();
        }
    }

    public static void showErrorMenu()
    {
        JOptionPane.showMessageDialog(null,
            "Please enter an Integer value from 10-100 for Grid-Size Selection.",
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

    public static void main()
    {
        new SnakePanel();
    }
}
