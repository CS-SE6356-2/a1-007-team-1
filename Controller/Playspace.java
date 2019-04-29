
import java.util.Arrays;

public class Playspace {
    Card[] discardPile = new Card[52];//discard pile
    String Type;//type of game being played

    Playspace(String type)
    {
    	if(type == "BlackJack")
    	{
    		discardPile = new Card[80];
    		
    		for(int i=0;i<discardPile.length; i++)
    		{
    			discardPile[i].suit = 0;
    			discardPile[i].face = 0;
    		}
    	}
    }
    
    public boolean checkFull()//checks if discard pile is full
    {
    	int flag = 0;
    	for (int i=0; i<discardPile.length; i++)
    	{
    		if(discardPile[i].suit == 0 && discardPile[i].face == 0)
    		{
    			flag++;
    		}
    	}
    	if(flag>0)
    		return false;
    	else
    		return true;
    }
    
    public void add(Card card)//adds card to discardpile
    {
    	for(int i=0; i<discardPile.length; i++)
    	{
    		if(discardPile[i].suit == 0 && discardPile[i].face == 0)
    		{
    			discardPile[i].suit = card.suit;
    			discardPile[i].face = card.face;
    		}
    	}
    }

    public void clearPlayspace(){//clears playspace
        // clear the discard pile 
        discardPile = null;
    }

}
