//This class represents the cards. Every card will be an iteration of this object.

public class Card
{
	int face = 0;//the number or face on the card
	int suit = 0;//the suit of the card (spade, club, diamond, heart)
  int value = 0;//the value of the card in relation to the current game
	boolean visibleAll = false;//flag for whether card is visible to everyone playing
	boolean visibleHolder = true;//flag for whether card is visible to holder

	
	
	Card()//constructor
	{
		
	}
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

	
	//sets the value of card to what it is worth in the game (Blackjack in this case)
	public void setValue()
    {
        if(face == 1)//Aces worth 11 initially
        {
            value = 11;
        }
        else if(face == 11)//jacks worth 10
        {
            value = 10;
        }
        else if(face == 12)//queens worth 10
        {
            value = 10;
        }
        else if(face == 13)//kings worth 10
        {
            value = 10;
        }
        else//other cards worth their face value
        {
            value = face;
        }
    }


}
