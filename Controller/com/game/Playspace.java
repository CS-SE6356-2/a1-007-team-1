package com.game;

public class Playspace {
    Card[] discardPile = new Card[52];
    String Type;

    Playspace(String type)
    {
    	if(type == "BlackJack")
    	{
    		discardPile = new Card[80];
    		
    		for(int i=0;i<discardPile.length; i++)
    		{
    			discardPile[i].setSuit(0);
    			discardPile[i].setFace(0);
    		}
    	}
    }
    
    public boolean checkFull()
    {
    	int flag = 0;
    	for (int i=0; i<discardPile.length; i++)
    	{
    		if(discardPile[i].getSuit() == 0 && discardPile[i].getFace() == 0)
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
    		if(discardPile[i].getSuit() == 0 && discardPile[i].getFace() == 0)
    		{
    			discardPile[i].setSuit(card.getSuit());
    			discardPile[i].setFace(card.getFace());
    		}
    	}
    }

    public void clearPlayspace(){
        // clear the discard pile 
        discardPile = null;
    }

}
