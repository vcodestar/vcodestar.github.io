import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;


public class AStar
{
    private int nodesExplored = 0;

    public void solve(NodeAStar initialState, GameBoard board)
    {
        PriorityQueue<NodeAStar> frontier = new PriorityQueue<>();
        HashSet<NodeAStar> visited = new HashSet<>();
        float cost = 0;
        boolean found = false;

        NodeAStar root = initialState;
        frontier.add(root);

        while(!frontier.isEmpty())
        {
            NodeAStar node = frontier.poll();

            if(node.isTerminal())
            {
                System.out.println("\nFound terminal");
                cost = node.getCost();
                System.out.println("Cost : " + cost);
                System.out.println("Depth : " + node.getDepth());
                System.out.println("Nodes explored : " + nodesExplored);
                found = true;
                printPath(node);
                return;
            }

            ArrayList<NodeAStar> children = node.getNextStates();

            for(NodeAStar child : children)
            {
            

                if(!visited.contains(child))
                {
                    child.setParent(node);
                    nodesExplored ++ ;
                    visited.add(child);
                    frontier.add(child);
                }
            }


        }

        return; // no solution found
    }
    
    public void printPath(NodeAStar node)
    {
        ArrayList<NodeAStar> path = new ArrayList<>();

        while(node != null)
        {
            path.add(node);
            node = node.getParent();
        }

        Collections.reverse(path);
        System.out.println("=========PATH=================");
        for(NodeAStar pth : path)
        {
            System.out.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            pth.printState();
            System.out.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        }
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        NodeAStar root = new NodeAStar(board.getState(), board.getK(), 0, 0);
        AStar a = new AStar();
        a.solve(root, board);
    }
}