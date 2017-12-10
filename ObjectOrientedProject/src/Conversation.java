import java.util.*;
import java.io.IOException;

public class Conversation<C> { //java generics are cool. learn something new every day.
	    public Node<C> root;

	    public Conversation(C rootData) {
	        root = new Node<C>();
	        root.data = rootData;
	        root.children = new HashMap<String, Node<C>>();
	    }

	    public class Node<C> {
	        public C data;
	        public Node<C> parent;
	        public HashMap<String, Node<C>> children = new HashMap<String, Node<C>>();
	        
	        //for trading
	        public int NumTakeItems = 0;
	        public String nameOfItemToTake = "";
	        public Item itemToGive = null;
	        public String textIfTradeHappened = "";
	        
	        //if a room should be unblocked (only used in one case)
	        public String unblockDirection = "north";
	        
	        //if an NPC should leave after trading
	        public String nameOfNPCToDelete = "";
	        public String leaveText = "";
	        
	        //if the game should fail
	        public boolean loseGame = false;
	        public String loseText = "";
	        
	        public Node<C> addOption(String option ,C child, C data) {
		        Node<C> childNode = new Node<C>();
		        childNode.parent = this;
		        childNode.data = data;
		        this.children.put(option, childNode);
		        return childNode;
		    }
	        
	        public int takeItems(Inventory playerInventory, Map map) {
	        	//incomplete...
	        	ArrayDeque stack = new ArrayDeque(); //create a stack
	        	
	        	while (stack.size() < NumTakeItems) { //take the items if the player has enough. Give back the items if he doesn't.
	        		Item tempitem = playerInventory.removeItemFromInventory(nameOfItemToTake);
	        		if (tempitem != null) {
	        			stack.push(tempitem);
	        		}
	        		else {
	        			while (!stack.isEmpty()) {
	        				playerInventory.addItemToInventory((Item) stack.pop());
	        			}
	        			System.out.println("You don't have enough!\n");
	        			return 0;
	        		}
	        	}
	        	
	        	System.out.println(textIfTradeHappened+"\n");
	        	giveItem(playerInventory); //now give an item back, if you should
	        	unblockRoom(map); //unblock a room, if you should
	        	deleteNPC(map); //finally, get rid of the NPC if you should
	        	return 0;
	        }
	        
	        public void giveItem(Inventory playerInventory) {
	        	if (itemToGive != null) {
	        		playerInventory.addItemToInventory(itemToGive);
	        	}
	        }
	        
	        public void unblockRoom(Map map) {
	        	switch (unblockDirection) {
				case("north"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].northBlocker = "";
					break;
				case("south"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].southBlocker = "";
					break;
				case("east"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].eastBlocker = "";
					break;
				case("west"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].westBlocker = "";
					break;
				case("up"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].upBlocker = "";
					break;
				case("down"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].downBlocker = "";
					break;
				default:
					//do nothing
					break;
			}	
	        }
	        
	        public void deleteNPC(Map map) {
	        	System.out.println(leaveText+"\n");
	        	map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].removeNPCFromRoom(nameOfNPCToDelete);
	        }
	        
	        public void loseGame() {
	        	if (loseGame == true) {
	        		System.out.println(loseText);
	        		System.exit(0);
	        	}
	        }
	        
	    }
	    
	    public void start(Inventory playerInventory, Map map) { // I don't think this is approved of by the coding gods...
	    	try {
	    		start(root, playerInventory, map);
	    	} catch (IOException ouch) {
	    		//don't do anything....
	    	}
	    }
	    
	    public void start(Node<C> CurrentNode, Inventory playerInventory, Map map) throws IOException {
			Scanner reader = new Scanner(System.in);
			
			//when you move to this node... check if you need to take an item
			if (CurrentNode.NumTakeItems > 0) {
				CurrentNode.takeItems(playerInventory, map); //gotta pass it the player inventory
				System.out.println("You have exited the conversation.");
				throw new IOException(""); //break out if a trade happened or failed
			}
			
			//check if you should lose the game
			if (CurrentNode.loseGame == true) {
				CurrentNode.loseGame();
			}
			
	    	System.out.println(CurrentNode.data+"\n");
	    	System.out.println("Possible responses");
	    	System.out.println("0. Go back to previous response");
	    	
	    	for (String option : CurrentNode.children.keySet()) {
	    		System.out.println(option);
	    	}
	    	
	    	System.out.print("input > ");
	    	
	    	String input = reader.nextLine();
	    	if ("bye".equals(input)) {
	    		System.out.println("You have exited the conversation.");
	    		throw new IOException(""); //break out
	    	}
	    	if ('0' == input.charAt(0)) {
	    		if (CurrentNode.parent != null) {
	    			start(CurrentNode.parent, playerInventory, map);
	    		}
	    		else {
	    			System.out.println("You are already at the root of the conversation.\n");
	    			start(CurrentNode, playerInventory, map);
	    		}
	    	}
	    	
	    	if (!CurrentNode.children.isEmpty()) {
	    		for (String option : CurrentNode.children.keySet()) {
		    		if (option.charAt(0) == input.charAt(0)) {
		    			start(CurrentNode.children.get(option), playerInventory, map);
		    		}
		    	}
	    	}
	    	
	    	System.out.println("You didn't enter a valid number!\n");
	    	start(CurrentNode, playerInventory, map);
	    }
	    
	    

}
