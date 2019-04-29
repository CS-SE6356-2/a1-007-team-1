//This class represents the deck. Game will hold a deck object and use it to play the game.
//Deck holds an array of Card objects. Deck size will be initialized according to game type.

public class Deck 
{
    Card[] deck = new Card[0];//set to zero until initialized by constructor
    int size = deck.length;//variable to hold deck size
    
    //Constructor
    Deck()
    {
    	deck = new Card[104];//size of 2 52-card decks for blackjack
        
        for(int i = 0; i < 104; i++)//for loop initializes the deck to new card objects
        {
        	deck[i]=new Card();
        }
        
        int y=0;//counter to keep track of place in deck
        for(int z=0; z<2; z++)//loop to run through a full deck initialization twice
        {
        	for(int i=0; i< 4; i++)//loop to run through all four suits
            {
                for(int x=0; x< 13; x++)//loop to run through all 13 faces
                {
                    deck[y].face = (x+1);
                    deck[y].setValue();//sets the card value during initialization
                    deck[y].suit = (i+1);
                    y++;//increments deck index to move to next card
                }
            }
        }
    }
    
    Deck(String type)
    {
        if(type == "BlackJack")//initialization conditional to check which type of game deck needs to be
        	                   //initialized for
        {
            deck = new Card[104];//size of 2 52-card decks for blackjack
            
            for(int i = 0; i < 104; i++)//for loop initializes the deck to new card objects
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
                        deck[y].setValue();//sets the card value during initialization
                        deck[y].suit = (i+1);
                        y++;//increments deck index to move to next card
                    }
                }
            }
        }
    }
    
    //Shuffles the 104 card deck
    public void Shuffle()
    {
        Deck shuffledDeck = new Deck();//creates new deck for holding shuffled cards
        int randomNum = 0;//random number variable for use in shuffling algorithm
        
        for(int i = 0; i < deck.length; i++)//runs through for entire deck size
        {
            do
            {
                randomNum = (int) (Math.random() * deck.length);//randomly produces an index within the unshuffled deck
                
                if((deck[randomNum].face != -1) && (deck[randomNum].suit != -1))//checks if this card has been used
                {
                    shuffledDeck.deck[i].face = deck[randomNum].face;//copies the card's info into next available slot in shuffled deck
                    shuffledDeck.deck[i].suit = deck[randomNum].suit;
                    shuffledDeck.deck[i].value = deck[randomNum].value;
                    
                    deck[randomNum].face = -1;//sets face/suit to -1 to avoid reuse of same card
                    deck[randomNum].suit = -1;
                    break;//breaks from do-while and goes to next deck entry
                }
            }while((deck[randomNum].face == -1) || (deck[randomNum].suit == -1));//finds next unused card
        }
        
        deck = shuffledDeck.deck;//set deck = to shuffled deck, now deck is shuffled randomly.
    }
    
    //Resets the deck to a full 104 card deck (for blackjack) unshuffled
    public void Reset(String type)
    {
       if(type == "BlackJack")
       {
            deck = new Card[104];//reset method uses same algorithm as initialization in constructor.
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
    
    //Deals a starting hand to each player depending on the type of game selected
    public void Deal(Player[] players, String type)
    {
        
        if(type == "BlackJack")
        {
        	for(int a=0; a<2; a++)//goes around the table twice, dealing once to each player on each go round
        	{
        		for(int x = 0; x < deck.length; x++)
                {
                    if((deck[x].suit != 0) && (deck[x].face != 0))//finds next available card in deck
                    {
                    	Card card = new Card();
                        Game.dealer.getHand().add(card);//adds empty card into dealer's hand
                        
                        card.face=deck[x].face;//copies card attributes to newly dealt card
                        card.suit=deck[x].suit;
                        card.value=deck[x].value;
                        
                        if(a == 1)
                        {
                        	card.visibleAll = true;//second card dealt is visible to table
                        }
                        deck[x].face = 0;//set card in deck to 0 to avoid reuse
                        deck[x].suit = 0;
                        break;
                    }
                }
        		for(int i=0; i < players.length; i++)//same as dealing to dealer, just for the player array
                {
                    for(int x = 0; x < deck.length; x++)
                    {
                        if((deck[x].suit != 0) && (deck[x].face != 0))
                        {
                        	Card card = new Card();
                            players[i].getHand().add(card);
                            
                            card.face=deck[x].face;
                            card.suit=deck[x].suit;
                            card.value=deck[x].value;
                            
                            if(a == 1)
                            {
                            	card.visibleAll = true;
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
