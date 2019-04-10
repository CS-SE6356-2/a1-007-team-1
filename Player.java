import java.util.ArrayList;

//This class is meant to hold player attributes and functions, for use in the Game class

public class Player
{
	private ArrayList<Card> hand = new ArrayList<Card>(0);//list of cards to represent player's hand
	private int points = 0;//keeps count of player's point total
	private Player[] team = new Player[0];//array of players for use in team games, not applicable to Black Jack
	boolean isOut = false;
	
	
	Player(String type)
	{
		
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
	void draw()
	{
		for(int i=0; i<=deck.length; i++)
		{
			if(deck[i].face != 0 && deck[i].suit != 0)
			{
				hand.add(deck[i]);
				deck[i].face = 0;
				deck[i].suit = 0;
				break;
			}
		}
	}
	void discard(Card card)
	{
		for(int i=0; i<=deck.length; i++)
		{
			if (deck[i].compareTo(card) == 0)
			{
				deck[i].face = 0;
				deck[i].suit = 0;
				break;
			}
		}
	}
}
