import java.util.Scanner;

public class CubeGame
{ 
    public static void main(String[] args) 
    {
        GameBoard board = new GameBoard();
        
        System.out.println("\n>>>>>>>>>>>>>>> A-STAR <<<<<<<<<<<<<<<<<<");
        
        NodeAStar root1 = new NodeAStar(board.getState(), board.getK(), 0, 0);
        AStar a = new AStar();
        a.solve(root1, board);
        
        System.out.println("\n>>>>>>>>>>>>>>> END OF A-STAR <<<<<<<<<<<<<<<<<<");


        System.out.println("\n>>>>>>>>>>>>>>> UCS <<<<<<<<<<<<<<<<<<");
        
        Node root2 = new Node(board.getState(), board.getK(), 0, 0);
        UCS ucs = new UCS();
        ucs.solve(root2, board);
        
        System.out.println("\n>>>>>>>>>>>>>>> END OF UCS <<<<<<<<<<<<<<<<<<");

    }
}
