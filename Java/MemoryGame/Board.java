
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

class Board
{
    private int n;
    private static int[] board;
    private static int[] cards;
    private static Scanner scanner= new Scanner(System.in);
    private ArrayList<Integer> numbers=new ArrayList<Integer>();
    private HashSet<Integer> tableNumbers=new HashSet<Integer>();
    private ArrayList<Integer> numbersArray=new ArrayList<Integer>();
    private static Random random=new Random();

    public Board(int n)
    {
        this.n=n;
        board=new int[2*n];
        cards=new int[2*n];

        for (int i=0;i<n;i++)
        {   

            int j=random.nextInt();
            numbers.add(j);
            numbers.add(j);
           
        }

        int index;
       
        for(int i=0;i<2*n;i++)
        {
            index=random.nextInt(numbers.size());
            cards[i]=numbers.get(index);
            
            numbers.remove(index);
        }
        for (int i=0;i<board.length;i++)
        {
            tableNumbers.add(cards[i]);
            numbersArray.add(cards[i]);
        }
    }


    public  void print()
    {
        for (int i=0;i<board.length;i++)
        {
            System.out.print(i+"            ");
        }

        System.out.println(" ");


        for (int i=0;i<board.length;i++)
        {
            
            if(tableNumbers.contains(cards[i]))
            {
                System.out.print('*'+"            ");
            }
            else
            {
                System.out.print("            ");
                
            }

        }
    }


    public void flash(int numb1,int numb2)
    {
         for (int i=0;i<board.length;i++)
         {
             System.out.print(" "+i+"           ");
         }

         System.out.println(" ");

        for (int i=0;i<board.length;i++)
        {
            System.out.print("  ");
            if(i==numb1 || i==numb2)
            {
                System.out.print(cards[i]);
            }
            else if(tableNumbers.contains(cards[i]))
            {
                System.out.print("   "+"*"+"       ");
            }
            else
            {
                System.out.print("            ");
            }
        
        }
        
        delay(4);

        System.out.print("\r");

        for (int i=0;i<board.length;i++)
        {     

            if(tableNumbers.contains(cards[i]))
            {
                System.out.print('*'+"            ");
            }
            else
            {
                System.out.print("            ");
            }

        }

    }

    private void delay(int sec)
    {
        try {
            Thread.currentThread().sleep(1000*sec);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }	
    }

    public boolean openPositions(int cards1,int cards2)
    {

        if(cards[cards1]==cards[cards2]  && tableNumbers.contains(cards[cards1]) && cards1!=cards2)
        {
            
            System.out.println("");
            System.out.println("Found pair ("+cards[cards1]+","+cards[cards2]+")");
            System.out.println();
            
            tableNumbers.remove(cards[cards2]);
            tableNumbers.remove(cards[cards1]);
          
            print();
            return true;

        }

        
        else if(cards[cards1]!=cards[cards2] && cards.length>0 && tableNumbers.contains(cards[cards1])&& tableNumbers.contains(cards[cards2]))
        {
            
            System.out.println();
            System.out.println("Not a pair");

            flash(cards1,cards2);
            return false;

        }

        else
        {
            
            System.out.println();
            System.out.println("Invalid selection");
            
            return false;

        }

    }


    public  int getRandomPosition()
    {

       int randomPosition=random.nextInt(numbersArray.size()) ;
       return randomPosition;

    }
    
    public int getRandomPosition(int pos)
    {

        int returnPos=random.nextInt(numbersArray.size());
        int r=returnPos;

        while (r==pos || !containsCard(r))
        {
            r=random.nextInt(numbersArray.size());
        }
        return r;
        
    }

    public boolean containsCard(int cardPos)
    {
       
        if (cardPos<cards.length && tableNumbers.contains(numbersArray.get(cardPos))&& tableNumbers.size()!=0)
        {
            return true;
        }

        else return false;

    }


    public int getCard(int getPos)
    {
        return numbersArray.get(getPos);
    }


    public boolean allPairsFound()
    {
        
        if (tableNumbers.size()==0)
        {
            return true;
        }

        else return false;

    }

    
    public static void main(String args[])
    {
        
        Board test=new Board(3);
        test.print();
        /*test.print();
        System.out.println("");
        System.out.print(test.getCard(0));
        System.out.print(" ");
        System.out.print(test.getCard(1));
        System.out.print(" ");
        System.out.print(test.getCard(2));
        System.out.print(" ");
        System.out.print(test.getCard(3));
        System.out.print(" ");
        System.out.print(test.getCard(4));
        System.out.print(" ");
        System.out.print(test.getCard(5));
        test.getRandomPosition(1);
        System.out.println("");
        test.getRandomPosition(2);
        System.out.println("");
        test.flash(1,2);
        System.out.println("");
        test.openPositions(3,4);
        System.out.println("");
        System.out.print(test.containsCard(1));*/
        System.out.println("");
        System.out.print(test.getCard(0));
        System.out.print(" ");
        System.out.print(test.getCard(1));
        System.out.print(" ");
        System.out.print(test.getCard(2));
        System.out.print(" ");
        System.out.print(test.getCard(3));
        System.out.print(" ");
        System.out.print(test.getCard(4));
        System.out.print(" ");
        System.out.print(test.getCard(5));
        System.out.println();
        int first=test.getRandomPosition();
        int second=test.getRandomPosition(first);
        test.flash(first, second);
        System.out.println();
        int testNumber1=random.nextInt(board.length);
        int testNumber2=random.nextInt(board.length);
        while(testNumber1==testNumber2)
        {
            testNumber2=random.nextInt(board.length);
        }

        boolean testoP=test.openPositions(testNumber1, testNumber2);
        while(!testoP)
        {
            testNumber1=random.nextInt(board.length);
            testNumber2=random.nextInt(board.length);
            testoP=test.openPositions(testNumber1,testNumber2);
        }
        System.out.println();
        test.print();

        boolean testoP2=test.openPositions(testNumber1, testNumber2);
        while(testoP2)
        {
            testNumber1=random.nextInt();
            testNumber2=random.nextInt();
            testoP2=test.openPositions(testNumber1, testNumber2);
        }
        System.out.println();
        test.print();

        boolean testcCard=test.containsCard(testNumber1);
        while(!testcCard)
        {
            testNumber1=random.nextInt(board.length);
            testcCard=test.containsCard(testNumber1);
        }

        while(testcCard)
        {
            testNumber1=random.nextInt(board.length);
            testcCard=test.containsCard(testNumber1);
        }

        boolean testoP3=test.openPositions(testNumber1, testNumber2);
        while(!testoP3)
        {
            testNumber1=random.nextInt(board.length);
            testNumber2=random.nextInt(board.length);
            testoP3=test.openPositions(testNumber1,testNumber2);
        }

        int third=test.getRandomPosition();
        int fourth=test.getRandomPosition(third);

        boolean testoP4=test.openPositions(testNumber1, testNumber2);
        while(!testoP4)
        {
            testNumber1=random.nextInt(board.length);
            testNumber2=random.nextInt(board.length);
            testoP4=test.openPositions(testNumber1,testNumber2);
        }

        test.allPairsFound();


        /*test.openPositions(0,1);
        test.openPositions(0,2);
        test.openPositions(0,3);
        test.openPositions(0,4);
        test.openPositions(0,5);
        test.openPositions(1,2);
        test.openPositions(1,3);
        test.openPositions(1,4);
        test.openPositions(1,5);
        test.openPositions(2,3);
        test.openPositions(2,4);
        test.openPositions(2,5);
        test.openPositions(3,4);
        test.openPositions(3,5);
        test.openPositions(4,5);
        System.out.println(test.allPairsFound());
        


        
        //test.openPositions(1,2);
        //test.flash(3,4);
        /*int t=test.getRandomPosition();
        test.getRandomPosition(1);
        test.print();
        test.openPositions(1,2);
        test.getRandomPosition();
        System.out.println(test.flash(t,1));*/
    }
}


