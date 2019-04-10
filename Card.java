//This class represents the cards. Every card will be an iteration of this object.

public class Card
{
	int face = 0;//the number or face on the card
	int suit = 0;//the suit of the card (spade, club, diamond, heart)
	
	boolean visibleAll = false;
	boolean visibleHolder = false;
	
	//Method to compare card to another card passed in.
	public int compareTo(Card c)
	{
		if (face == c.face)
			return 0;
		else if (face > c.face)
			return 1;
		else
			return -1;
	}
}
