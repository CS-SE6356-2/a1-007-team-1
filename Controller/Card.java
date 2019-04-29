//This class represents the cards. Every card will be an iteration of this object.
//Deck class holds an array of this object

public class Card
{
	int face = 0;//the number or face on the card (1,2,3,king,queen,etc.)
	int suit = 0;//the suit of the card (spade, club, diamond, heart)
  int value = 0;//the value of the card in relation to the current game (in blackjack cards have point values, kings=10, ace=11/1, etc.)
	boolean visibleAll = false;//flag for whether card is visible to everyone playing (to regulate cheating)
	boolean visibleHolder = true;//flag for whether card is visible to holder (to regulate cheating)

	
	
	Card()//constructor incase other games need to modify class
	{
		
	}
	
	//Method to compare card to another card passed in. (not used in blackjack)
	public int compareTo(Card c)
	{
		if (face == c.face)//check if the two cards have the same face
			return 0;
		else if (face > c.face)//see if one is greater than the other
			return 1;
		else//see if one is less than the other
			return -1;
	}

	
	//sets the value of card to what it is worth in the game (Blackjack in this case)
	//this is done to more easily keep track of player's points in a round, and to determine who wins each round
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
