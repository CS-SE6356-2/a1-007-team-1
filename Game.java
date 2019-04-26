import java.util.Scanner;

//This class will serve as the main class for the program. It will house the main function,
//and all of the central operations for the card game Black Jack.

	

public class Game {
    static String type;//type of game
    static Player[] turnOrder = new Player[0];//array of players
    static Player dealer = new Player(type);//dealer player
    static int userinput=99;//input from user
    static int dealerAction = 0;//action taken by dealer

    
    public static void main(String[] args)
    {
    	while(userinput != 0)//loop for entire program, circles back to menu until exit is chosen
    	{
    		for(int i=0; i<turnOrder.length; i++)
    		{
    			turnOrder[i].setPoints(0);//initializes points to zero every game
    		}
    		dealer.setPoints(0);//same for dealer
    		
    		System.out.println("Please select a game from the menu or press zero to exit.");
            
            System.out.println("0. Exit\n\n1. Blackjack");//menu
            
            Scanner input = new Scanner(System.in);
            
            userinput = input.nextInt();
            
            while(userinput != 0 && userinput != 1)//input validation
            {
            	System.out.println("ERROR, INVALID INPUT. PLEASE RE-ENTER INPUT");
            	
            	userinput = input.nextInt();
            }
            
            if (userinput == 0)//exit command
            {
            	System.exit(0);
            }
            else if (userinput == 1)//Blackjack is chosen
            {
            	type = "BlackJack";
            	
            	System.out.println("How many Players? (Max 4)");
            	
            	userinput = input.nextInt();
            	
            	while(userinput <= 0 || userinput > 4)//input validation
            	{
            		System.out.println("ERROR, INVALID INPUT. PLEASE RE-ENTER INPUT");
            		
            		userinput = input.nextInt();
            	}
            	
            	turnOrder = new Player[userinput];//player chooses number of players
            	for (int i=0; i<userinput; i++)
            	{
            		turnOrder[i]=new Player(type);//initialize each player object
            	}
            	
            	for(int i = 0; i < userinput; i++)
            	{
            		turnOrder[i].number = i+1;//sets player number
            	}
            	
            	Deck deck = new Deck(type);//create deck
            	
            	deck.Shuffle();//shuffles deck

            	
            	do//holds the rounds of Blackjack. loops until win condition met
                {            	
                    deck.Deal(turnOrder, type);//deal the cards
                    
                    //Display Points and Visible cards to all players
                    for(int i=0; i<turnOrder.length; i++)
                    {
                    	turnOrder[i].updateTotal();//update total value of cards
                    	
                    	//prints card face and total value
                    	System.out.println("\nPlayer "+ turnOrder[i].number +" has "+ turnOrder[i].getHand().get(0).face + " " + turnOrder[i].getHand().get(1).face);
                    	System.out.println("\nPlayer "+ turnOrder[i].number +" has "+ turnOrder[i].cardTotal+ " total");
                    }
                    dealer.updateTotal();//update total value of cards
                    
                    //prints card face and total value
                    System.out.println("\nDealer"+ " has "+ dealer.getHand().get(0).face + " " + dealer.getHand().get(1).face);
                    System.out.println("\nDealer"+ " has "+ dealer.cardTotal+ " total");
                    
                    for(int x = 0; x < turnOrder.length; x++)//runs through players and lets them take their turns
                    {
                        int playerAction = -1;//initialize choice to -1
                        
                        do//loops until they stay or bust
                        {
                        	System.out.println("\nPlayer "+ turnOrder[x].number + "'s turn.");
                            System.out.println("Player "+ turnOrder[x].number + ", would you like to Hit or Stay? (Please enter 0 for Hit and 1 for Stay)");
                            playerAction = input.nextInt();
                            if (playerAction == 0)//hit chosen
                            {
                                turnOrder[x].draw(deck);//draws card
                                turnOrder[x].updateTotal();//updates the total value of cards held
                                
                                System.out.print("\nPlayer "+ turnOrder[x].number +" has ");//Display player card total
                                for(int i=0; i<turnOrder[x].getHand().size(); i++)
                                {
                                	System.out.print(turnOrder[x].getHand().get(i).face + " ");
                                }
                                System.out.println("\n\nPlayer "+ turnOrder[x].number +" has "+ turnOrder[x].cardTotal+ " total");
                                
                                if(turnOrder[x].cardTotal > 21)//bust check
                                {
                                    turnOrder[x].isOut = true;//sets them to out
                                    System.out.println("\nPlayer " + turnOrder[x].number + " busts and is out.");
                                    playerAction = 1;//auto stay to move on
                                }
                            }
                            
                        }while(playerAction == 0);
                    }
                    
                    dealer.updateTotal();//loop for dealer's turn
                    do
                    {
                        if(dealer.cardTotal <= 15)//hits if 15 or under
                        {
                            dealer.draw(deck);
                            dealer.updateTotal();
                            
                            System.out.print("\nDealer has ");
                            for(int i=0; i<dealer.getHand().size(); i++)
                            {
                            	System.out.print(dealer.getHand().get(i).face + " ");
                            }
                            System.out.println("\n\nDealer has "+ dealer.cardTotal+ " total");
                        }
                        else if(dealer.cardTotal == 16)//can either stay or hit if 16
                        {
                            if(Math.random() * 2 == 1)
                            {
                                dealer.draw(deck);
                                dealer.updateTotal();
                                
                                System.out.print("\nDealer has ");
                                for(int i=0; i<dealer.getHand().size(); i++)
                                {
                                	System.out.print(dealer.getHand().get(i).face + " ");
                                }
                                System.out.println("\n\nDealer has "+ dealer.cardTotal+ " total");
                            }
                            else
                            {
                                dealerAction = 1;
                            }
                        }
                        else//if over 17, stays
                        {
                            dealerAction = 1;
                        }
                    }while(dealerAction == 0);
                    dealerAction = 0;//resets dealer action
                    
                    if(dealer.cardTotal > 21)//bust check
                    {
                    	dealer.isOut = true;
                        System.out.println("\nDealer busts and is out.");
                    }
                    
                    if(!dealer.isOut)//check for dealer win round
                    {
                    	for(int i = 0; i < turnOrder.length; i++)
                        {
                        	if(!turnOrder[i].isOut && dealer.cardTotal<turnOrder[i].cardTotal)
                        	{
                        		dealer.isOut=true;//is out if lower than any non-busted player
                        	}
                        }
                    }
                    
                    for(int x = 0; x < turnOrder.length; x++)//check for player win round
                    {
                        if(!turnOrder[x].isOut)
                        {
                        	for(int y = 0; y<turnOrder.length; y++)
                        	{
                        		if(!turnOrder[y].isOut && turnOrder[x].cardTotal < turnOrder[y].cardTotal)
                        		{
                        			turnOrder[x].isOut=true;//out if lower than any non-busted player
                        			break;
                        		}
                        	}
                        	if(!dealer.isOut && turnOrder[x].cardTotal < dealer.cardTotal)
                    		{
                    			turnOrder[x].isOut=true;//out if lower than non-busted dealer
                    		}
                        }
                    }
                    
                    if(!dealer.isOut)//if dealer is not out from bust or outscore, dealer wins
                    {
                    	dealer.setPoints(1+dealer.getPoints());//increment points
                    	System.out.println("Dealer wins the round!");
                    }
                    
                    else//else, the non-bust player(s) with the highest score win
                    {
                    	System.out.print("Player(s)");
                    	
                    	for(int i=0; i<turnOrder.length; i++)
                    	{
                    		if(!turnOrder[i].isOut)
                    		{
                    			turnOrder[i].setPoints(1+turnOrder[i].getPoints());//increment points
                    			System.out.print(" "+turnOrder[i].number);
                    		}
                    	}
                    	System.out.println(" win the round!");
                    }
                    for(int i = 0; i < turnOrder.length; i++)
                    {
                    	turnOrder[i].isOut=false;//reset isout flag
                    }
                    dealer.isOut=false;//reset isout flag
                    
                    dealer.clearHand();//clear dealer hand
                    
                    for(int i=0; i<turnOrder.length; i++)
                    {
                    	turnOrder[i].clearHand();//clear player hands
                    }
                }while(checkWin(type, turnOrder) == null);
            }
    	}
        
    }
    public void phases(Player player, String type)//not used in blackjack
    {
        
    }
    public static Player checkWin(String type, Player[] turnOrder)//check for win condition met
    {

        if(type == "BlackJack")
        {
        	if(dealer.getPoints()==3)//win condition
        	{
        		System.out.println("Dealer wins!");
        		
        		return dealer;//returns winner
        	}
        	
            for(int x=0; x < turnOrder.length; x++)
            {
            	
                if(turnOrder[x].getPoints() == 3)//win condition
                {
                	System.out.println("Player "+turnOrder[x].number+" wins the game!");
                	
                    return turnOrder[x];//returns winner
                }
            }
            
            
        }
        
        return null;//returns null if no winner
    }
    }


