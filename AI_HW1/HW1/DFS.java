/*
    DFS always returns a solution but it is not optimal. A* and UCS will not work for complex situation due to heap space problem.
*/

import java.util.ArrayList;
import java.util.Collections;

public class DFS 
{
    private static float cost = 0;
    private static boolean found = false;
    private static int exploredNodes = 0;
    
    public static void solve(Node node,  GameBoard board)
    {

        if(node.getDepth() > 6 * board.getK() + (board.getK() + 1))
        {
            return;
        }

        if(node.isTerminal())
        {
            cost = node.getCost();
            found = true;
            System.out.println("\nFound terminal");
            node.printState();
            System.out.println("\nCost : " + node.getCost());
            System.out.println("\nExplored nodes : " + exploredNodes);
            System.out.println("\n//////////////////////////");
            printPath(node);
            return;
        }

        if(found && node.getCost() >= cost){return;}
        ArrayList<Node> successors = node.getNextStates();

        for(Node succ : successors)
        {
            exploredNodes++;

            if(found && succ.getCost() >= cost){return;}
            node.getChildren().add(succ);
            succ.setParent(node);
            solve(succ, board);
            return;
        }
    }

    public static void printPath(Node node)
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
        DFS.solve(root, board);
    }
}
