
import java.util.Random;

class ComputerPlayer
{

    private String name;
    private int pointsCPU=0;
    private int[] cardsSeen;

    public ComputerPlayer(String name,int n)
    {
        
        this.name=name;
        cardsSeen=new int[2*n];

        for(int posN=0;posN<cardsSeen.length;posN++)
        {
            cardsSeen[posN]= -1;
        }
    }

    public void play(Board ai)
    {
        
        Random random=new Random();

        int f=ai.getRandomPosition();
        
        while(!ai.containsCard(f) && !ai.allPairsFound())
        {
            f=ai.getRandomPosition();
        }
        

        if (ai.containsCard(f))
        {
            int i=0;
            while (i<cardsSeen.length && !ai.allPairsFound())
            {
                

                if(ai.getCard(f)==cardsSeen[i] && f!=i)
                {
                    
                    System.out.println();
                    System.out.println("Computer "+name+" selected positions: "+f+" "+i+".");
                    
                    boolean iff=ai.openPositions(f,i);
                    if(iff)
                    {
                        pointsCPU++;
                    }
                    else{}
                    
                    break;
                }
                else
                {}
                ;
                i++;
            }
               
                
                if(!ai.allPairsFound())
                {
                    cardsSeen[f]=ai.getCard(f);
                    int s=ai.getRandomPosition(f);
                    cardsSeen[s]=ai.getCard(s);
                
                    if(ai.containsCard(f))
                    {
                        System.out.println();
                        System.out.println("Computer "+name+" selected positions: "+f+" "+s+".");
                        boolean e=ai.openPositions(f,s);
                        if(e)
                        {
                            pointsCPU++;
                        }
                        else{} 
                    }
                    else{}
                        
                }
            }
    }
                        
                    

    public int getPointsCPU()
    {
        return pointsCPU;
    } 


    public String toString()
    {
        return name;
    }

    public static void main(String args[]) 
    {
        ComputerPlayer cpu=new ComputerPlayer("CPU",3);
        Board game=new Board(3);
        while(!game.allPairsFound())
        {
            cpu.play(game);
        }
    }


}