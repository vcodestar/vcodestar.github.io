/*
The objective of the game is to find the most matching pairs of cards.
The game supports both Player vs Player amd Player vs AI

NOTE : To execute write : java MemoryGame n
                            where "n" is the number of pair of cards 
 */
import java.util.Scanner;

class MemoryGame
{
    public static void main(String args[])
    {
        int round=0;

        
        Board run=new Board(Integer.valueOf(args[0]));
        System.out.println("Do you want a Player vs Player game(enter 1) or a Player vs AI game(enter 2)?");
        Scanner answer=new Scanner(System.in);
        int answerInput=answer.nextInt();
        while (answerInput!=1 && answerInput!=2)
        {
            System.out.println("Do you want a Player vs Player game(enter 1) or a Player vs AI game(enter 2)?");
            answer=new Scanner(System.in);
            answerInput=answer.nextInt();
        }
        

        if(answerInput==1)
        {
            System.out.println("Enter player's 1 name:");
            String name1=answer.next();
            System.out.println("Enter player's 2 name:");
            String name2=answer.next();
            HumanPlayer player1=new HumanPlayer(name1);
            HumanPlayer player2=new HumanPlayer(name2);


            while(!run.allPairsFound())
            {
                System.out.println("Round "+ Math.abs(round+1));
                player1.play(run);
                if(!run.allPairsFound())
                {
                    player2.play(run);
                    System.out.println();
                    System.out.println(player1.toString()+" points :"+player1.getPointsP());
                    System.out.println(player2.toString()+" points :"+player2.getPointsP());
                    round++;
                }
                else
                {
                    System.out.println();
                    System.out.println(player1.toString()+" points :"+player1.getPointsP());
                    System.out.println(player2.toString()+" points :"+player2.getPointsP());
                }
            }

            if(player1.getPointsP()>player2.getPointsP())
            {
                System.out.println(player1.toString()+" is the winner!!!");
            }

            else if(player1.getPointsP()<player2.getPointsP())
            {
                System.out.println(player2.toString()+" is the winner!!!");
            }

            else
            {
                System.out.println();
                System.out.println("Tie!!!");
            }

        }


        else if(answerInput==2)
        {
            System.out.println("Enter player's  name:");
            String name1=answer.next();
            System.out.println("Enter AI's name:");
            String name2=answer.next();
            HumanPlayer player1=new HumanPlayer(name1);
            ComputerPlayer aiPlayer=new ComputerPlayer(name2,Integer.valueOf(args[0]));


            while(!run.allPairsFound())
            {
                System.out.println("Round "+ Math.abs(round+1));
                player1.play(run);
                aiPlayer.play(run);
                System.out.println();
                System.out.println(player1.toString()+" points: "+player1.getPointsP());
                System.out.println(aiPlayer.toString()+" points :"+aiPlayer.getPointsCPU());
                round++;
            }
               

            
            if(player1.getPointsP()>aiPlayer.getPointsCPU())
            {
                System.out.println(player1.toString()+" is the winner!!!");
            }

           
            else if(player1.getPointsP()<aiPlayer.getPointsCPU())
            {
                System.out.println(aiPlayer.toString()+" is the winner!!!");
                
            }
            
            else
            {
                System.out.println("Tie!!!");
            }


        

        
        }
    }
}
