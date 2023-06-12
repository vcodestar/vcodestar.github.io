import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;


public class UCS
{
    private int nodesExplored = 0;

    public void solve(Node initialState, GameBoard board)
    {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> visited = new HashSet<>();
        float cost = 0;
        boolean found = false;

        Node root = initialState;
        frontier.add(root);

        while(!frontier.isEmpty())
        {
            Node node = frontier.poll();

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

            ArrayList<Node> children = node.getNextStates();

            for(Node child : children)
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
    
    public void printPath(Node node)
    {
        ArrayList<Node> path = new ArrayList<>();

        while(node != null)
        {
            path.add(node);
            node = node.getParent();
        }

        Collections.reverse(path);
        System.out.println("=========PATH=================");
        for(Node pth : path)
        {
            System.out.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            pth.printState();
            System.out.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        }
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Node root = new Node(board.getState(), board.getK(), 0, 0);
        UCS ucs = new UCS();
        ucs.solve(root, board);
    }
}