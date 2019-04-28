import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    Deck shuffledDeck = new Deck();
    Card[] deck = new Card[0];//set to zero until initialized by constructor
    int size = deck.length;//variable to hold deck size
   // DeckTest()
   // {

  //  }

    @Test
    public void shuffle() {
        deck = new Card[104];//size of 2 52-card decks for blackjack

        for(int i = 0; i < 104; i++)
        {
            deck[i]=new Card();
        }

        int y=0;//counter to keep track of place in deck
        for(int z=0; z<2; z++)//loop to run through a full deck init. twice
        {
            for(int i=0; i< 4; i++)//loop to run through all four suits
            {
                for(int x=0; x< 13; x++)//loop to run through all 13 faces
                {
                    deck[y].face = (x+1);
                    deck[y].setValue();
                    deck[y].suit = (i+1);
                    y++;
                }
            }
        }
    System.out.print("Test one");



       // Deck shuffledDeck = new Deck();
        int randomNum = 0;

        for(int i = 0; i < deck.length; i++)//runs through for entire deck size
        {
            do
            {
                randomNum = (int) (Math.random() * deck.length);//randomly produces a num within the deck

                if((deck[randomNum].face != -1) && (deck[randomNum].suit != -1))//checks for duplicates
                {
                    shuffledDeck.deck[i].face = deck[randomNum].face;
                    shuffledDeck.deck[i].suit = deck[randomNum].suit;
                    shuffledDeck.deck[i].value = deck[randomNum].value;

                    deck[randomNum].face = -1;//sets face/suit to -1 to avoid reuse of same card
                    deck[randomNum].suit = -1;
                    break;//breaks from do-while and goes to next deck entry
                }
            }while((deck[randomNum].face == -1) || (deck[randomNum].suit == -1));//finds next unused card
        }

        deck = shuffledDeck.deck;//set deck = to shuffled deck, now deck is shuffled randomly.

        assertNotEquals(deck, shuffledDeck);  // makes sure the deck is shuffled and not the same

    }

    //Resets the deck to a full 104 card deck (for blackjack) unshuffled


    @Test
    public void reset() {
        deck = new Card[104];//size of 2 52-card decks for blackjack

        for(int i = 0; i < 104; i++)
        {
            deck[i]=new Card();
        }

        int y=0;//counter to keep track of place in deck
        for(int z=0; z<2; z++)//loop to run through a full deck init. twice
        {
            for(int i=0; i< 4; i++)//loop to run through all four suits
            {
                for(int x=0; x< 13; x++)//loop to run through all 13 faces
                {
                    deck[y].face = (x+1);
                    deck[y].setValue();
                    deck[y].suit = (i+1);
                    y++;
                }
            }
        }
    assertNotEquals(deck, shuffledDeck);

    }
}