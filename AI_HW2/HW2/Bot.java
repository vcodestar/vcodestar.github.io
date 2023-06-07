import java.util.ArrayList;

public class Bot
{

    private GameBoard board;
    


    public Bot(GameBoard board)
    {
        this.board = board;
    }


    public void remove2(GameTree tree)
    {
        tree.minimax(tree.getRoot());
        tree.pickChildToMakeMove();
    }

    
}
