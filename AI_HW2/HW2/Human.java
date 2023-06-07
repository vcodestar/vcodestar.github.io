public class Human 
{
    GameBoard board;

    public Human(GameBoard board) {
        this.board = board;
    }

    public void remove()
    {
        System.out.println("------ Player Plays ------");
        board.remove();
    }

    
}
