import java.util.ArrayList;

//This class is meant to hold player attributes and functions, for use in the Game class

public class Player
{
	private ArrayList<Card> hand = new ArrayList<Card>(0);//list of cards to represent player's hand
	private int points = 0;//keeps count of player's point total
	private Player[] team = new Player[0];//array of players for use in team games, not applicable to Black Jack
	boolean isOut = false;//flags when a person is out
	int number = 0;//player number: Player1, Player2, etc.
    int cardTotal;//total points of cards in hand

	
	Player()
	{
		
	}
	Player(String type)//constructor
	{
		if(type == "BlackJack")
		{
			cardTotal = 0;
		}
	}
	
	ArrayList<Card> getHand()
	{
		return hand;
	}
	void setHand(ArrayList<Card> newHand)
	{
		hand = newHand;
	}
	void clearHand()
	{
		hand.clear();
	}
	int getPoints()
	{
		return points;
	}
	void setPoints(int newPoints)
	{
		points = newPoints;
	}
	Player[] getTeam()
	{
		return team;
	}
	void setTeam(Player[] newTeam)
	{
		team = newTeam;
	}
	void draw(Deck deck)//draws a card from the deck and places in player's hand
	{
		
		//runs through deck looking for first available card
		for(int i=0; i<deck.deck.length; i++)
		{
			if(deck.deck[i].face != 0 && deck.deck[i].suit != 0)
			{
				Card card = new Card();
				card.face = deck.deck[i].face;
				card.suit = deck.deck[i].suit;
				card.value = deck.deck[i].value;
				
				hand.add(card);//adds to hand
				
				card.visibleAll = true;//all drawn cards in blackjack are visible to table
				deck.deck[i].face = 0;//sets card in deck to 0 to avoid reuse
				deck.deck[i].suit = 0;
				break;//exits for loop
			}
		}
	}
	void discard(Card card, Playspace table)//discards a card from the hand
	{
			hand.remove(card);//removes card from hand
			
			if(table.checkFull() != true)//checks if discard pile is full
			{
				for (int i=0; i<table.discardPile.length; i++)//runs through and adds card into first empty slot
		    	{
		    		if(table.discardPile[i].suit == 0 && table.discardPile[i].face == 0)
		    		{
		    			table.discardPile[i] = card;
		    		}
		    	}
			}
	}
        void updateTotal()//updates total point value of cards in hand
        {
        	cardTotal=0;//reinitialize to zero
            for(int x = 0; x < hand.size(); x++)//runs through hand
            {
                cardTotal += hand.get(x).value;//totals value of cards
                
                if(cardTotal > 21)//detects bust
                {
                    for(int y = 0; y< hand.size(); y++)//runs through hand again looking for aces to convert
                    {
                        if(hand.get(y).value == 11)//if unconverted ace is found
                        {
                            hand.get(x).value = 1;//convert
                            cardTotal -= 10;//update point value of cards to account for conversion
                        }
                    }
                }
            }
        }

}
