import java.util.Scanner;
import java.util.InputMismatchException;

public class CardGame
{
    static boolean humanPlayedLast = false;
    public static void main(String[] args) {

        GameBoard board = new GameBoard();
        Human p = new Human(board);
        Bot b = new Bot(board);
        boolean endGame = false;

        System.out.println("=========================");
        System.out.println("Game Started ! ");
        System.out.println("=========================");
     
        
   
        while(!endGame)
        {
            GameTree tree = new GameTree(board.getCurrentBoardInt(), board);
            board.printCurrentBoard();
            b.remove2(tree);

            endGame = board.endGame();

            if(board.endGame())
            {
                humanPlayedLast = false;
                board.printCurrentBoard();
                printWinner();
                return;
            }
            
            board.printCurrentBoard();
            p.remove();
            
            if(board.endGame())
            {
                humanPlayedLast = true;
                board.printCurrentBoard();
                printWinner();
                return;
            }
        }
    }


    // print who wins the game
    public static void printWinner()
    {
        if(humanPlayedLast)
        {
            System.out.println("+++++++--------- GAME OVER -------+++++++++");
            System.out.println("+++++++--------- PLAYER WINS -------+++++++++");
            return;
        }
        else
        {
            System.out.println("+++++++--------- GAME OVER -------+++++++++");
            System.out.println("+++++++--------- BOT WINS -------+++++++++");
            return;
        }
    }

}
