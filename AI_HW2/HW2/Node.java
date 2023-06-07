import java.util.ArrayList;

public class Node 
{

    private ArrayList<Integer> state;
    private ArrayList<Node> children;
    private Node parent; 
    private int value;

    private boolean isMaximizing;

    public Node(ArrayList<Integer> state) 
    {
        this.state = state;
        this.children = new ArrayList<>();
    }

    public ArrayList<Integer> getState() 
    {
        return state;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public boolean isMaximizing()
    {
        return isMaximizing;
    }

    public void setMaximizing(boolean isMaximizing)
    {
        this.isMaximizing = isMaximizing;
    }

    public ArrayList<Node> getChildren() 
    {
        return children;
    }

    public Node getChild()
    {
        return children.get(0);
    }

    public void addChild(Node child) 
    {
        children.add(child);
        child.setParent(this);
    }

    public void setParent(Node node)
    {
        this.parent = node;
    }

    public Node getParent()
    {
        return this.parent;
    }

    public boolean isTerminal() 
    {
 
        for (int i = 0; i < state.size(); i++) 
        {
            if (state.get(i) != 0) 
            {
                return false;
            }
        }

        return true;
    }
    
}
