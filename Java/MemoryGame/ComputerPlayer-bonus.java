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
        int count=0;//metraei poses fores epai3e o paiktis otan count==round exei kanei tin kinisi toy gia ton gyro an count<round kanei tin epomeni kinisi
        int round=0;

        round++;

        if(!ai.allPairsFound())
        {
            for(int a=0;a<cardsSeen.length;a++)
            {
                for (int b=0;b<cardsSeen.length;b++)
                {
                    if(cardsSeen[a]==cardsSeen[b] && cardsSeen[a]!=-1 && a!=b &&ai.containsCard(a)&&ai.containsCard(b))
                    {
        
                        ai.openPositions(a, b);
                        cardsSeen[a]=-1;
                        cardsSeen[b]=-1;
                        count++;
                        pointsCPU++;
                        break;
                    }
                    else{}
                }
            }
        }
        
        if (count<round)
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
                        count++;
                        if(iff)
                        {
                            pointsCPU++;
                        }
                        else{}
                        //for (int j=0;j<cardsSeen.length;j++)
                        
                        break;
                    }

                    else
                    {}

                    
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
                            count++;
                            if(e)
                            {
                                pointsCPU++;
                            }
                            else{} 
                        }
                        else{}
                            
                    }
                }
                else{}
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


}