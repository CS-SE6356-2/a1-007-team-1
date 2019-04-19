import java.util.Scanner;

//This class will serve as the main class for the program. It will house the main function,
//and all of the central operations for the card game Black Jack.

	

public class Game {
    String name;
    static String type;
    static Player[] turnOrder = new Player[0];
    static Player dealer = new Player(type);
    static int userinput=99;
    static int dealerAction = 0;

    
    public static void Main(String[] args)
    {
    	while(userinput != 0)
    	{
    		System.out.println("Please select a game from the menu or press zero to exit.");
            
            System.out.print("0. Exit\n\n1. Blackjack");
            
            Scanner input = new Scanner(System.in);
            
            userinput = input.nextInt();
            
            while(userinput != 0 || userinput != 1)
            {
            	System.out.println("ERROR, INVALID INPUT. PLEASE RE-ENTER INPUT");
            	
            	userinput = input.nextInt();
            }
            
            if (userinput == 0)
            {
            	System.exit(0);
            }
            else if (userinput == 1)
            {
            	type = "BlackJack";
            	
            	System.out.println("How many Players? (Max 4)");
            	
            	userinput = input.nextInt();
            	
            	while(userinput <= 0 || userinput > 4)
            	{
            		System.out.println("ERROR, INVALID INPUT. PLEASE RE-ENTER INPUT");
            		
            		userinput = input.nextInt();
            	}
            	
            	turnOrder = new Player[userinput];
            	
            	for(int i = 0; i < userinput; i++)
            	{
            		turnOrder[i].number = i;
            	}
            	
            	Deck deck = new Deck(type);//create deck

            	
            	while(checkWin(type, turnOrder) == null)
                {            	
                    deck.Deal(turnOrder, type);//deal the cards
                    
                    //Display Points and Visble cards to all players
                    
                    for(int x = 0; x < turnOrder.length; x++)
                    {
                        int playerAction = 0;
                        
                        do
                        {
                            
                            System.out.println("Would you like to Hit or Stay? (Please enter 0 for Hit and 1 for Stay");
                            playerAction = input.nextInt();
                            if (playerAction == 0)
                            {
                                turnOrder[x].draw(deck);
                                turnOrder[x].updateTotal();
                                if(turnOrder[x].cardTotal > 21)
                                {
                                    turnOrder[x].isOut = true;
                                    System.out.println("Player " + turnOrder[x].number + " busts and is out.");
                                    playerAction = 1;
                                }
                                
                                //Display player card total
                            }
                            
                        }while(playerAction == 0);
                    }
                    
                    dealer.updateTotal();
                    do
                    {
                        if(dealer.cardTotal <= 15)
                        {
                            dealer.draw(deck);
                        }
                        else if(dealer.cardTotal == 16)
                        {
                            if(Math.random() * 2 == 1)
                            {
                                dealer.draw(deck);
                            }
                            else
                            {
                                dealerAction = 1;
                            }
                        }
                        else if(dealer.cardTotal > 17)
                        {
                            dealerAction = 1;
                        }
                    }while(dealerAction == 0);
                    dealerAction = 0;
                    if(dealer.cardTotal > 21)
                    {
                        System.out.println("Dealer busts and is out.");
                    }
                    
                    for(int x = 0; x < turnOrder.length; x++)
                    {
                        
                    }
                    // Last worked on here ( trying to figure out how to calculate who won the round
                }
            }
            
            
    	}
        
    }
    public void phases(Player player, String type)
    {
        
    }
    public static Player checkWin(String type, Player[] turnOrder)
    {

        if(type == "BlackJack")
        {
            for(int x=0; x < turnOrder.length; x++)
            {
                if(turnOrder[x].getPoints() == 10)
                {
                    return turnOrder[x];
                }
                else
                {
                    return null;
                }
            }
            
            
        }
        
        return null;
    }
    }


