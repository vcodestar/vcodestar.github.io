import java.util.Scanner;

import javax.print.attribute.standard.PDLOverrideSupported;

class HumanPlayer
{

    private String nameP;
    private int playerPoints;

    public HumanPlayer(String nameP)
    {
        this.nameP=nameP;
    }

    public  void play(Board player)
    {
        Scanner input=new Scanner(System.in);

        System.out.println("");
        System.out.println(nameP+" pick Card 1:");
        int input1=input.nextInt();
        System.out.println("");
        System.out.println(nameP+" pick Card 2:");
        int input2=input.nextInt();

        
        while(input1==input2 || !player.containsCard(input1) && !(player.allPairsFound()) || !player.containsCard(input2) && !(player.allPairsFound()))
        {
            System.out.println("Invalid Selection!");
            System.out.println(nameP+" pick Card 1:");
            input1=input.nextInt();
            System.out.println(nameP+" pick Card 2:");
            input2=input.nextInt();
        }

        System.out.println(nameP+" selected positions: "+input1+" "+input2);


        boolean ToF=(player.openPositions(input1,input2));

        if(ToF)
        {  
            playerPoints++;
        }
           
        else{}

        

    }

    
    public int getPointsP()
    {
        return playerPoints;
        
    }

    public String toString()
    {
        return nameP;
    }


}