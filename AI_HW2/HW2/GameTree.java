import java.util.ArrayList;

public class GameTree 
{
    private Node root;
    private GameBoard board;

    public GameTree(ArrayList<Integer> initialState, GameBoard board) 
    {
        this.board = board;
        this.root = new Node(initialState);
        root.setMaximizing(true);
        generateTree(root);
    }

    public Node getRoot()
    {
        return root;
    }

    private void generateTree(Node node) 
    {
        if (node.isTerminal()) 
        {
            if(node.isMaximizing())
            {
                node.setValue(-1);
                node.getParent().setValue(-1);
            }
            if(!node.isMaximizing())
            {
                node.setValue(1);
                node.getParent().setValue(1);
               // getWinningPath(node);
            }

            return;
        }

        ArrayList<Integer> state = node.getState();
        for (int i = 0; i < state.size(); i++) 
        {

            if (state.get(i) > 0) 
            {
                //for j < getB(i) nextState.set(i, nextState.get(i) - j)
                for(int j = 1; j <= board.getB(i); j++)
                {
                    ArrayList<Integer> nextState = new ArrayList<>(state);
                    if(nextState.get(i) - j < 0)
                    {
                        nextState.set(i, 0);
                    }
                    else
                    {
                        nextState.set(i, nextState.get(i) - j);
                    }
                    Node child = new Node(nextState);
                    child.setMaximizing(!node.isMaximizing());
                    node.addChild(child);
                    generateTree(child);
                }
            }
        }
    }

    public int minimax(Node node)
    {
        if(node.isTerminal())
        {
            //System.out.println("Found terminal node with value : " + node.getValue());
            return node.getValue();
        }

        if(node.isMaximizing())
        {
            int bestValue = -1;
            for(Node child : node.getChildren())
            {
                int value = minimax(child);
                bestValue = Math.max(bestValue, value);
            }
            node.setValue(bestValue);
            return bestValue;
        }
        else
        {
            int bestValue = 1;
            for(Node child : node.getChildren())
            {
                int value = minimax(child);
                bestValue = Math.min(bestValue, value);
            }
            node.setValue(bestValue);
            return bestValue;
        }
    }

    public void pickChildToMakeMove()
    {
        for(Node child : root.getChildren())
        {
            if(child.getValue() == 1)
            {
                makeMove(child);
                return;
            }
        }
        // add code to make move if every child is losing 
        makeRiskyMove(root.getChild());
    }

    public void makeMove(Node rootChild)
    {
        System.out.println("----- Bot Plays -----");

        ArrayList<Integer> childList = rootChild.getState();
        ArrayList<Integer> boardList = board.getCurrentBoardInt();
        ArrayList<Integer> moveList = new ArrayList<Integer>();

        System.out.println("Current board : " + board.getCurrentBoardInt());
        //System.out.println("Root child : " + childList);
        //System.out.println("Board : "  + boardList);

        for(int i = 0; i < board.getTeamListSize(); i++)
        {
            if(childList.get(i) != boardList.get(i))
            {
                moveList.add(i);
                moveList.add(boardList.get(i) - childList.get(i));
                break;
            }
        }

        board.botRemove(moveList.get(0), moveList.get(1));
    }

    // make move when losing in every current state scenario
    public void makeRiskyMove(Node rootChild)
    {
        System.out.println("----- Bot Plays -----");

        ArrayList<Integer> boardList = board.getCurrentBoardInt();
        ArrayList<Integer> moveList = new ArrayList<Integer>();

        int moreCards = 0;

        for(int i = 0; i < boardList.size(); i++)
        {
            if(boardList.get(i) > moreCards)
            {
                moreCards = boardList.get(i);
                moveList.add(0, i);
            }
        }

        board.botRemove(moveList.get(0), 1);
    }

}
