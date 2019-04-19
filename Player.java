import java.util.ArrayList;

//This class is meant to hold player attributes and functions, for use in the Game class

public class Player
{
	private ArrayList<Card> hand = new ArrayList<Card>(0);//list of cards to represent player's hand
	private int points = 0;//keeps count of player's point total
	private Player[] team = new Player[0];//array of players for use in team games, not applicable to Black Jack
	boolean isOut = false;
	int number = 0;
  int cardTotal;

	
	
	Player(String type)
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
	void draw(Deck deck)
	{
		
		
		for(int i=0; i<=deck.deck.length; i++)
		{
			if(deck.deck[i].face != 0 && deck.deck[i].suit != 0)
			{
				hand.add(deck.deck[i]);
				deck.deck[i].visibleAll = true;
				deck.deck[i].face = 0;
				deck.deck[i].suit = 0;
				break;
			}
		}
	}
	void discard(Card card, Deck deck)
	{
		for(int i=0; i<=deck.deck.length; i++)
		{
			if (deck.deck[i].compareTo(card) == 0)
			{
				deck.deck[i].face = 0;
				deck.deck[i].suit = 0;
				break;
			}
		}
	}
        void updateTotal()
        {
            for(int x = 0; x < hand.size(); x++)
            {
                cardTotal += hand.get(x).value;
                
                if(cardTotal > 21)
                {
                    for(int y = 0; y< hand.size(); y++)
                    {
                        if(hand.get(y).value == 11)
                        {
                            hand.get(x).value = 1;
                            cardTotal -= 10;
                        }
                    }
                }
            }
        }

}
