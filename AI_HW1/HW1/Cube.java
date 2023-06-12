import java.util.ArrayList;

public class Cube
{
    private int id; // number on cube
    private ArrayList<Integer> xy = new ArrayList<Integer>(); // pos x, pos y

    public Cube(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public void setXY(int x, int y)
    {   
        xy.add(0, x);
        xy.add(1, y);
    }

    public int getX()
    {
        return xy.get(0);
    }

    public int getY()
    {
        return xy.get(1);
    }

    
}
