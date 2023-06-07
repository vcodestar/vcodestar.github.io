import java.util.ArrayList;
import java.util.Scanner;
/*

Team was made with an arraylist, in order to build a clickable interface later

*/
public class Team 
{
    private int noOfCards; // number of cards

    private ArrayList<String> cardList = new ArrayList<String>(); // where cards will be held for the current Team

    // args = number of cards
    public Team(int noOfCards)
    {
        this.noOfCards = noOfCards;

        this.add(noOfCards);
    }

    // add cards to list
    public void add(int noOfCards)
    {
        for(int i = 0; i < noOfCards; i++)
        {
            cardList.add("Card");
        }
    }

    // remove cards from list
    public void remove(int noOfCards)
    {
        for(int i = 0; i < noOfCards; i++)
        {
            if(cardList.size() > 0)
                cardList.remove(cardList.size() - 1);
            else
                System.out.println("\n! No Cards Left To Be Removed !\n");
        }
    }

    // get size of list
    public int getSize()
    {
        return cardList.size();
    }


    
}
