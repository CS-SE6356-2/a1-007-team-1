//This class represents the cards. Every card will be an iteration of this object.

public class Card
{
	int face = 0;//the number or face on the card
	int suit = 0;//the suit of the card (spade, club, diamond, heart)

        int value = 0;

	
	boolean visibleAll = false;
	boolean visibleHolder = true;
	
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

        public void setValue()
        {
            if(face == 1)
            {
                value = 11;
            }
            else if(face == 11)
            {
                value = 10;
            }
            else if(face == 12)
            {
                value = 10;
            }
            else if(face == 13)
            {
                value = 10;
            }
            else
            {
                value = face;
            }
        }
}
