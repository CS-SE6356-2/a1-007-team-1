

public class Deck 
{
    Card[] deck = new Card[0];
    int size = deck.length;
    
    //Constructor
    public Deck(String type)
    {
        if(type == "BlackJack")
        {
            deck = new Card[51];
            for(int i=0; i<= 3; i++)
            {
                for(int x=0; x<=12; x++)
                {
                    deck[x].face = (x+1);
                }
            }
        }
    }
    
    //Shuffles the 52 card deck
    public void Shuffle()
    {
        Card[] shuffledDeck = deck;
        int randomNum = 0;
        
        for(int i = 0; i <= 51; i++)
        {
            do
            {
                randomNum = (int) (Math.random() * 52);
                
                if((deck[randomNum].face != -1) && (deck[randomNum].suit != -1))
                {
                    shuffledDeck[i].face = deck[randomNum].face;
                    shuffledDeck[i].suit = deck[randomNum].suit;
                    
                    deck[randomNum].face = -1;
                    deck[randomNum].suit = -1;
                    break;
                }
            }while((deck[randomNum].face == -1) || (deck[randomNum].suit == -1));
        }
        
        deck = shuffledDeck;
    }
    
    //Resets the deck to a full 52 card deck unshuffled
    public void Reset(String type)
    {
       if(type == "BlackJack")
        {
            deck = new Card[51];
            for(int i = 0; i <= 3; i++)
            {
                for(int x = 0; x <= 12; x++)
                {
                    deck[x].face = (x + 1);
                }
            }
        }
    }
    
    //Deals a starting hand to each player depending on the type of game selected
    public void Deal(Player[] players, String type)
    {
        
        if(type == "BlackJack")
        {
            for(int i=0; i < players.length; i++)
            {
                for(int x = 0; x <= deck.length; x++)
                {
                    if((deck[x].suit != 0) && (deck[x].face != 0))
                    {
                        players[i].hand.add = deck[x];
                        deck[x].face = 0;
                        deck[x].suit = 0;
                    }
                }
                
                
            }
            
        }
    }

    
}
