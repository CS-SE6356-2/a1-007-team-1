import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    static String type;//type of game
    static Player dealer = new Player(type);//dealer player
    static Player[] turnOrder = new Player[6];//array of players


    @Test
    public void main() {

        turnOrder[0] = new Player();
        turnOrder[1] = new Player();
        turnOrder[2] = new Player();
        turnOrder[3] = new Player();
        turnOrder[4] = new Player();
        turnOrder[5] = new Player();

        int userinput = 1;
        Deck deck = new Deck(type);
        Deck deckRandom = new Deck(type);
        assertNotNull(deck); // check to see that deck not null
        deckRandom.Shuffle();//shuffles deck

        assertNotEquals(deck, deckRandom); // compare shuffled deck and random deck


    }

    @Test
    public void phases() {
        System.out.println("Not used in blackjack");
    }

    @Test
    public void checkWin() {


        dealer.setPoints(3);

        System.out.println("\nFirst test with dealer at 3 point to see if they pass win" );
        if(dealer.getPoints()==3)//win condition
        {
            System.out.println("Dealer pass first win statement and wins!\n");

            //return dealer;//returns winner
        }


        dealer.setPoints(1);
        System.out.println("\n\n2nd statement wit dealer less then 3 points, should not print they won:");
        if(dealer.getPoints()==3)//win condition
        {
            System.out.println("Dealer wins Statement 2, Something went wrong!");

            //return dealer;//returns winner
        }
        else{
            System.out.println("Dealer did not win statement 2");
        }

        dealer.setPoints(10);
        System.out.println("\n\n3nd statement wit dealer more then 3 points, should not print they won:");
        if(dealer.getPoints()==3)//win condition
        {
            System.out.println("Dealer win Statement 3, Something went wrong!!");

            //return dealer;//returns winner
        }
        else{
            System.out.println("Dealer did not win statement 3");
        }


        turnOrder[0].setPoints(0);
        turnOrder[1].setPoints(1);
        turnOrder[2].setPoints(2);
        turnOrder[3].setPoints(3);
        turnOrder[4].setPoints(4);
        turnOrder[5].setPoints(3);

        // make sure that only people with 3 points should win, any more or less wont
        System.out.println("\n\nOnly Player 3 and 5 should print out they won: \n");

        for(int x=0; x < turnOrder.length; x++)
        {

            if(turnOrder[x].getPoints() == 3)//win condition
            {
                System.out.println("Player "+ x +" wins the game!");

                //return turnOrder[x];//returns winner
            }
        }
    }
}