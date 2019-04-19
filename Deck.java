

public class Deck 
{
    Card[] deck = new Card[0];
    int size = deck.length;
    
    //Constructor
    public Deck(String type)
    {
        if(type == "BlackJack")
        {
            deck = new Card[104];
            int y=1;
            for(int z=0; z<2; z++)
            {
            	for(int i=0; i< 4; i++)
                {
                    for(int x=0; x< 13; x++)
                    {
                        deck[y].face = (x+1);
                        deck[y].setValue();
                        deck[y].suit = (i+1);
                        y++;
                    }
                }
            }
        }
    }
    
    //Shuffles the 104 card deck
    public void Shuffle()
    {
        Card[] shuffledDeck = deck;
        int randomNum = 0;
        
        for(int i = 0; i < 104; i++)
        {
            do
            {
                randomNum = (int) (Math.random() * 104);
                
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
    
    //Resets the deck to a full 104 card deck unshuffled
    public void Reset(String type)
    {
       if(type == "BlackJack")
        {
            deck = new Card[104];
            int y=1;
            for(int z=0; z<2; z++)
            for(int i = 0; i <= 3; i++)
            {
            	for(int i=0; i< 4; i++)
                {
                    for(int x=0; x< 13; x++)
                    {
                        deck[y].face = (x+1);
                        deck[y].setValue();
                        deck[y].suit = (i+1);
                        y++;
                    }
                }
            }
        }
    }
    
    //Deals a starting hand to each player depending on the type of game selected
    public void Deal(Player[] players, String type)
    {
        
        if(type == "BlackJack")
        {
        	for(int a=0; a<2; a++)
        	{
        		for(int i=0; i < players.length; i++)
                {
                    for(int x = 0; x < deck.length; x++)
                    {
                        if((deck[x].suit != 0) && (deck[x].face != 0))
                        {
                            players[i].getHand().add(deck[x]);
                            if(a == 1)
                            {
                            	deck[x].visibleAll = true;
                            }
                            deck[x].face = 0;
                            deck[x].suit = 0;
                            break;
                        }
                    }
                    
                    
                }
        	}
            
            
        }
    }

    
}
