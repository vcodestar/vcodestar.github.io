import java.util.Scanner;
import java.util.ArrayList;


public class GameBoard
{

    private Scanner scanner = new Scanner(System.in);

    private int m; // number of cards
    private int k; // number of teams 

    private ArrayList<Team> teamList = new ArrayList<Team>(); // o 
    private ArrayList<Integer> b = new ArrayList<Integer>(); // number of cards to be removed per team

    public GameBoard()
    {


        System.out.println("\nEnter number of cards(M) : ");
        m = getValidInput();

        System.out.println("\nEnter number of teams that cards will be separated into(K) : ");
        k = getValidInput();

        while(this.checkK(k, m))
        {
            System.out.println("WRONG INPUT !!! \nNOTE : K <= (M / 2)");
            System.out.println("Enter number of teams that cards will be separated into(K) : ");
            k = scanner.nextInt();
        }

        this.pickNumberOfCardsPerTeam(k, m);
        this.pickNumberOfCardToBeRemovedPerTeam();

    }

    // get teamList size
    public int getTeamListSize()
    {
        return teamList.size();
    }

    // get max number of cards than can be removed per team (b)
    public int getB(int index)
    {
        return b.get(index);
    }

    // get teamList(i)'s number of cards
    public int getTeamListNumberOfCards(int index)
    {
        return teamList.get(index).getSize();
    }

    // make sure that each team has at least 2 cards at the beggining
    public boolean checkK(int k, int m)
    {
        if(k <= (m / 2))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // initialize teamList by adding empty teams
    public void makeTeams(int noOfCards)
    {
        for(int i = 0; i < k; i++)
        {   
            Team team = new Team(2);
            teamList.add(team);
        }

        System.out.println("TeamListSize : " + teamList.size());
    }

    // ensure that O is not greater than the greatest number of cards in a team
    public int findMaxTeam()
    {
        int max = 0;

        for(int i = 0; i  < teamList.size(); i++)
        {
            if(teamList.get(i).getSize() > max)
            {
                max = teamList.get(i).getSize();
            }
        }

        System.out.println("Max number of cards in a team : " + max);

        return max;
    }

    // call make teams and let the user choose number of cards per team
    public void pickNumberOfCardsPerTeam(int k, int m)
    {

        this.makeTeams(0);

        m -= 2 * k;

        if(m == 0) return; // no cards left to add, skip the rest of this method

        System.out.println("!!!!!!!!!!!!!!!! IMPORTANT !!!!!!!!!!!!!!!!!!!");
        System.out.println("Each team already has 2 card ! Add more cards any way you want !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        
        while(m > 0)
        {   
            for(int i = 0; i < k; i++)
            {
                
                System.out.println("###########################################");

                if(m == 0) break;

                System.out.println("\nNumber of cards left to add : " + m);
                System.out.println("\nEnter size of Team[" + (i+1) + "](A[" + (i+1) + "])");
                System.out.println("------------------------------------------------------");

                int n = getValidInput(); // number of cards to add
                while((m - n) < 0 || n < 0)
                {
                    if(n < 0)
                    {
                        System.out.println("\nNumber should be greater than 0 !");
                    }
                    else 
                    {
                        System.out.println("\nNumber should not be greater than " + m + "!");
                    }
                    n = getValidInput();
                }

                m -= n;
                teamList.get(i).add(n);

                System.out.println("###########################################");

            }
            
        }
    }
    
    // uses arraylist b to save how many cards can be removed per turn on each team
    public void pickNumberOfCardToBeRemovedPerTeam()
    {
        int n;
        
        System.out.println("\n##################################################################");
        System.out.println("Enter max number of cards to be removed from each team per turn");
        System.out.println("---------------------------------------------------------------");
        
        for(int i = 0; i < k; i++)
        {
            System.out.println("\n====================================");
            System.out.println("Enter number for O[" + (i+1) + "]");
            System.out.println("---------------------------------------------------------------");
            n = getValidInput();
            
            while(n < 1 || n > teamList.get(i).getSize())
            {
                System.out.println("\nNumber should be greater than 1 and less than or equal to " + teamList.get(i).getSize());
                n = getValidInput();
            }
            
            b.add(n);
            
            System.out.println("\n====================================");
        }
    }

    // print how many cards are left in each team
    public void printCurrentBoard()
    {
        System.out.println("###########################################");
        for(int i = 0; i < k; i++)
        {
            System.out.println("O[" + (i + 1) + "] cards left : " + teamList.get(i).getSize());
        }
        System.out.println("###########################################");
    }

    // get current board

    public ArrayList<Integer> getCurrentBoardInt()
    {
        //ArrayList<Team> copyList = new ArrayList<>(teamList);
        ArrayList<Integer> getCardsLeft = new ArrayList<Integer>();

        for(int i = 0; i < teamList.size(); i++)
        {
            getCardsLeft.add(teamList.get(i).getSize());
        }

        return getCardsLeft;
    }

    // remove cards from board
    public void remove()
    {
        System.out.println("Enter the number of team : ");
        int nt = getValidInput() - 1; // number of team (-1 beacuse prints show 1 and we start counting from 0)

        while(nt >= teamList.size() || nt < 0 || teamList.get(nt).getSize() == 0)
        {
            if(nt >= teamList.size() || nt < 0 )
            {
                System.out.println("\nNumber should be greater than 0 and less than " + teamList.size());
            }
            else
            {
                System.out.println("This team is empty ! Try choosing another one !");
            }
            System.out.println("\nEnter the number of team");
            System.out.println("----------------------------------");
            nt = getValidInput() - 1; // same as above
        }
        
        System.out.println("\nEnter number of cards to be removed");
        System.out.println("------------------------------------------");
        int nc = getValidInput(); // number of card
        int minimum = Math.min(teamList.get(nt).getSize(), b.get(nt)); // min(Bi, Ni)

        while(nc < 1 || nc > minimum)
        {
            System.out.println("Number should be greater than 1 and equal or less than " + minimum);
            nc  = getValidInput();
        }

        teamList.get(nt).remove(nc);
    }

    // used from bot ////////////////////////
    public void botRemove(int i, int j)
    {
        teamList.get(i).remove(j);
    }

    // check if input was an integer 
    public int getValidInput()
    {
        boolean validInput = false;
        int num = 0;

        while(!validInput)
        {
            System.out.println("\nEnter a number:");
            if(scanner.hasNextInt())
            {
                num = scanner.nextInt();
                validInput = true;
            }
            else
            {
                System.out.println("\nInvalid input, please enter an integer.");
                scanner.next();
            }
        }

        return num;
    }

    // check if every team is empty
    public boolean endGame()
    {
        for(int i = 0; i < k; i++)
        {
            if(teamList.get(i).getSize() > 0)
            {
                return false;
            }
        }

        return true;
    }

}