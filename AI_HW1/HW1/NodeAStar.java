import java.util.*;

public class NodeAStar implements Comparable<NodeAStar>
{
    private NodeAStar parent;
    private int k;
    private ArrayList<ArrayList<Cube>> state;
    private float cost;
    private int depth;
    private ArrayList<NodeAStar> children;
    private String reset = "\u001B[0m";
    private String red = "\u001B[31m";
    private String green = "\u001B[32m";
    private String yellow = "\u001B[33m";

    public NodeAStar(ArrayList<ArrayList<Cube>> state, int k, float cost, int depth)
    {
        this.k = k;
        this.state =state;
        this.cost = cost;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public ArrayList<NodeAStar> getChildren()
    {
        return children;
    }

    public int getDepth()
    {
        return depth;
    }

    public ArrayList<ArrayList<Cube>> getState()
    {
        return state;
    }

    public void setState(GameBoard board)
    {
        state = board.getState();
    }

    public void setParent(NodeAStar parent)
    {
        this.parent = parent;
    }

    public NodeAStar getParent()
    {
        return parent;
    }

    public void setCost(float cost)
    {
        this.cost = cost;
    }

    public float getCost()
    {
        return cost;
    }

 
    public void printState()
    {
        System.out.print("Level 3 : " );
        for (Cube cube : state.get(2)) {
            if(cube != null)
            {
                if(this.isInPlace(cube))
                {
                    System.out.print(green + cube.getID()+ " " + reset); 
                }
                else
                {
                    System.out.print(red + cube.getID()+ " " + reset); 
                }
            }
            else
                System.out.print(" X");
        }
        System.out.println();
        System.out.print("Level 2 : " );
        for (Cube cube : state.get(1)) {
            if(cube != null)
            {
                if(this.isInPlace(cube))
                {
                    System.out.print(green + cube.getID()+ " " + reset); 
                }
                else
                {
                    System.out.print(red + cube.getID()+ " " + reset); 
                }
            }
            else
                System.out.print(" X");
        }
        System.out.println();
        System.out.print("Level 1 : " );
        for (Cube cube : state.get(0)) {
            if(cube != null)
            {
                if(this.isInPlace(cube))
                {
                    System.out.print(green + cube.getID()+ " " + reset); 
                }
                else
                {
                    System.out.print(red + cube.getID()+ " " + reset); 
                }
            }
            else
                System.out.print(" X");
        }
    }

    public boolean isTerminal()
    {
        for(int i = 0; i < k ; i++)
        {
            if(state.get(0).get(i) == null || state.get(1).get(i) == null || state.get(2).get(i) == null)
            {
                return false;
            }

            if(state.get(0).get(i).getID() != i + 1)
            {
                return false;
            }

            if(state.get(1).get(i).getID() != (k + i + 1))
            {
                return false;
            }

            if(state.get(2).get(i).getID() != 2 * k + i + 1)
            {
                return false;
            }


        }

        return true;
    }

    public boolean isValid(Cube cube, int x, int y)
    {
        if(y > 3)
        {
            return false;
        }
        if((x > k && y > 1) || (x > 4 * k && y == 1))
        {
            return false;
        }
        if(y == 2)
        {
            if(state.get(0).get(x - 1) == null || state.get(0).get(x-1) == cube)
            {
                return false;
            }
        }
        if(y == 3)
        {
            if(state.get(1).get(x - 1) == null || state.get(1).get(x-1) == cube)
            {
                
                return false;
            }
        }
        if(y == 1 && state.get(0).get(x - 1) != null)
        {
            return false;
        }
        if(y == 2 && state.get(1).get(x - 1) != null)
        {
            return false;
        }
        if(y == 3 && state.get(2).get(x - 1) != null)
        {
            
            return false;
        }
        return true;
    }

    public boolean isFree(Cube cube)
    {
        if(cube.getX() > k)
        {
            return true;
        }
        if(cube.getY() == 1)
        {

            if(state.get(1).get(cube.getX() - 1) != null) 
            {
                return false;
            }

        }
        if(cube.getY() == 2)
        {

            if(state.get(2).get(cube.getX() - 1) != null) 
            {
                return false;
            }

        }
        return true;
    }


    // cube to be moved, x and y position to be moved to
    public void makeMove(Cube cube, int x, int y)
    {
        if(isFree(cube) && isValid(cube, x, y))
        {
            this.cost += calculateCost(cube, x, y);
            state.get(cube.getY() - 1).set(cube.getX() - 1, null);
            cube.setXY(x, y);
            state.get(y - 1).set(x - 1, cube);
        }
        else
        {
            System.out.println("Move cannot be made!");
        }
    }

    public float calculateCost(Cube cube, int x, int y)
    {
        float cost = 0;

        if(cube.getY() == y)
        {
            cost = (float) 0.75;
        }
        else if(cube.getY() < y )
        {
            cost = (float)(y - cube.getY());
        }
        else if(cube.getY() > y )
        {
            cost = (float) (0.5 * (cube.getY() - y));
        }

        return cost;
    }

    public boolean isInPlace(Cube cube)
    {
        if(cube == null)
        {
            return false;
        }
        if(cube.getX() <= k && cube.getID() - (cube.getY() - 1) * k - cube.getX() == 0)
        {
            return true;
        }
        return false;
    }

    public boolean isBlocking(Cube cube)
    {
        for(int i = 0; i < cube.getY() - 1; i++)
        {
            // prepei na valoume kapoio se thesi pou einai apo katw tou, opote blockarei to na ginei ayto, afoy prepei na metakinithei
            if(!isInPlace(state.get(i).get(cube.getX() - 1)))
            {
                return true;
            }
        }
        if(cube.getX() <= k && cube.getID() - (cube.getY() - 1) * k - cube.getX() != 0) // to x > k mono stin 1i row opou oi theseis einai eleftheres na akoumpan oi cubes
        {

            return true;
        }

        return false;
    }

    public boolean willBlock(int x, int y)
    {
        for(int i = 0; i < y - 1; i++)
        {
            // prepei na valoume kapoio se thesi pou einai apo katw tou, opote blockarei to na ginei ayto, afoy prepei na metakinithei
            if(!isInPlace(state.get(i).get(x - 1)))
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<NodeAStar> getNextStates()
    {
        ArrayList<NodeAStar> nextStates = new ArrayList<>();

        for (ArrayList<Cube> level : state) 
        {
            for (Cube cube : level) 
            {   
                if(cube == null || !this.isFree(cube) || (this.isInPlace(cube) && !isBlocking(cube))){continue;}

                if(this.isBlocking(cube))
                {
                    boolean found = false;

                    for(int y = 1; y <= 3 ; y++)
                    {
                        for(int x = 1; x <= state.get(y - 1).size(); x++)
                        {
                            if(isValid(cube, x, y))
                            {

                                ArrayList<ArrayList<Cube>> childState = new ArrayList<>(); // state to deep copy current state and generate next states
                                childState = this.copyState(state);
    
                                float cost = 0;
                                cost += this.getCost();
                                int depth = 0;
                                depth += this.getDepth();
    
                                NodeAStar child = new NodeAStar(childState, k, cost, depth + 1);
                                int cubeX = cube.getX();
                                int cubeY = cube.getY();
    
                                Cube temp = new Cube(cube.getID());
                                temp.setXY(cubeX, cubeY);
    
                                child.makeMove(temp, x, y);
                                child.RemoveCube(cubeX, cubeY);
    
                                found = true;
                                nextStates.add(child);
    
    
                                break;
                            }
                        }
                        if(found) break;
                    }


                    continue;
                }

                boolean foundPos = false;
                for(int y = 1; y <= 3; y++)
                {
                    for(int x = 1; x <= state.get(y - 1).size(); x++)
                    {
                        if(willBlock(x, y)){continue;}

                        if((y > cube.getY() || cube.getID() <= k) && cube.getX() >= k && cube.getID() - (y - 1) * k - x == 0 && isValid(cube, x, y))
                        {
                            
                            ArrayList<ArrayList<Cube>> childState = new ArrayList<>(); // state to deep copy current state and generate next states
                            childState = this.copyState(state);

                            float cost = 0;
                            cost += this.getCost();
                            int depth = 0;
                            depth += this.getDepth();

                            NodeAStar child = new NodeAStar(childState, k, cost, depth + 1);
                            int cubeX = cube.getX();
                            int cubeY = cube.getY();

                            Cube temp = new Cube(cube.getID());
                            temp.setXY(cubeX, cubeY);

                            child.makeMove(temp, x, y);
                            child.RemoveCube(cubeX, cubeY);

                            nextStates.add(child);

                            foundPos = true;


                            break;

                        }
                            
                        
                    }

                    if(foundPos) break;
                }

            
            }
        
        }

        return nextStates;
    }

    // make deep copy of state
    public ArrayList<ArrayList<Cube>> copyState(ArrayList<ArrayList<Cube>> state)
    {
        ArrayList<ArrayList<Cube>> copy = new ArrayList<>();

        for(int i = 0; i < state.size(); i++)
        {
            ArrayList<Cube> temp = new ArrayList<Cube>(state.get(i));
            copy.add(temp);
        }

        return copy;
    }

    // remove Cube from last position
    private void RemoveCube(int cubeX, int cubeY)
    {
        //System.out.println("removing : " + state.get(cubeY - 1).get(cubeX - 1));
        state.get(cubeY - 1).set(cubeX - 1, null);
    }

    // find how many cubes are not inPlace()
    public int getNumberOfCubesNotInPlace()
    {
        int num = 0;

        for(ArrayList<Cube> level : state)
        {
            for(Cube cube : level)
            {
                if(cube != null && !isInPlace(cube))
                {
                    num++;
                }
            }
        }

        return num;
    }


    @Override
    public int compareTo(NodeAStar other) {
        return Float.compare(this.getCost() + this.getNumberOfCubesNotInPlace(), other.getCost() + other.getNumberOfCubesNotInPlace());
    }

}
