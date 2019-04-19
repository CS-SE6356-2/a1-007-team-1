import java.util.Scanner;

//This class will serve as the main class for the program. It will house the main function,
//and all of the central operations for the card game Black Jack.

	

public class Game {
    String name;
    static String type;
    static Player[] turnOrder = new Player[0];
    Player dealer = new Player(type);
    static int userinput=99;
    
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
            	
            	
            	
            	deck.Deal(turnOrder, type);//deal the cards
            	
            	for(i=0; i<)
            }
            
            
    	}
        
    }
    public void phases(Player player, String type)
    {
        
    }
    public Player checkWin(String type, Player turnOrder)
    {
        return turnOrder;
    }
    }


