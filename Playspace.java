
import java.util.Arrays;

public class Playspace {
    Card[] discardPile = new Card[52];
    String Type;

    public void Playspace(String type)
    {
    	if(type == "BlackJack")
    	{
    		discardPile = new Card[30];
    		
    		for(int i=0;i<discardPile.length; i++)
    		{
    			discardPile[i].suit = 0;
    			discardPile[i].face = 0;
    		}
    	}
    }
    
    public boolean checkFull()
    {
    	int flag = 0;
    	for (int i=0; i<discardPile.length; i++)
    	{
    		if(discardPile[i].suit != 0 && discardPile[i].face != 0)
    		{
    			flag++;
    		}
    	}
    	if(flag>0)
    		return false;
    	else
    		return true;
    }
    
    public void add(Card card)
    {
    	for(int i=0; i<discardPile.length; i++)
    	{
    		if(discardPile[i].suit == 0 && discardPile[i].face == 0)
    		{
    			
    		}
    	}
    }

    public void clearPlayspace(){
        // clear the discard pile 
        discardPile = null;
    }

}
