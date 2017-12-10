import java.util.*;

public class Controller {
		
	public int parseInput(String input, Map map, Helper helper, Inventory playerInventory, Thread t1) {
		//single word parser
		//I am being sure to return 0 so I never reach the second switch if any of these cases are true. Messy, but it works.
		switch (input) {
			case "m":
			case "move":
				System.out.println("move or m needs an argument to work, type 'help' for commands");
				return 0;
			case "help":
				System.out.println("Command List: \n" +
					"move or m: Moves the player in the specified direction. Example: \"move north or \"m n\". \n" +
					"talk or t: Talks to the target NPC. Example: \"talk bob\" or \"t bob\". \n" +
					"exit: Exits the conversation.");
				return 0;
				
			case "look":
				map.handleLook();
				return 0;
				
			case "info":
				System.out.println("The Ineptitude \n" +
						"Created by: \n" +
						"Omeed Ghafari, Arias Talari, Vitali Taranto");
				return 0;
			
			case "stop music":
				t1.suspend();
				return 0;
				
			case "resume music":
				t1.resume();
				return 0;
				
			case "quit":
				System.out.println("You have quit the game.");
				System.exit(0);
				// quit program
		}
		
		String[] inputArray = input.split(" ");
		
		//double word parser
		switch (inputArray[0]) {
			case "help":
				helper.handleHelp(inputArray[1]);
				break;
			case "move":
				//switch statements can be allowed to fall through, see link
				//https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
				//I am taking advantage of that behavior here
			case "m":
				map.handleMovement(inputArray[1]);
				break;
			case "talk":
			case "t":
				Room room = map.returnCurrentRoom();
				room.handleTalk(inputArray[1], playerInventory, map);
				break;
			case "check":
			case "c":
				if (inputArray[1].equals("inventory")) {
					System.out.println("Ruffling through your pockets, you see a...");
					playerInventory.listItems();
					break;
				}
				else if (inputArray[1].equals("room")) {
					System.out.println("Carefully checking the room, you see a...");
					map.handleCheck();
					break;
				}
				else {
					System.out.println("check can take 'inventory' or 'room' as an argument");
					break;
				}
			case "inspect":
			case "i":
				String tempdescription = map.handleInspect(inputArray[1]);
				if (tempdescription.equals("")) {
					tempdescription = playerInventory.handleInspect(inputArray[1]);
				}
				if (tempdescription.equals("")) {
					System.out.println("No such item in room or inventory");
				}
				else {
					System.out.println(tempdescription);
				}
				break;
			case "drop":
			case "d":
				Item tempitem = playerInventory.removeItemFromInventory(inputArray[1]);
				if (tempitem != null) {
					map.handleDrop(tempitem);
				}
				else {
					System.out.println("you don't see an item by that name");
				}
				break;
			case "pickup":
			case "p":
				Item tempitem2 = map.handlePickup(inputArray[1]);
				if (tempitem2 != null) {
					playerInventory.addItemToInventory(tempitem2);
				}
				break;
			case "use":
			case "u":
				playerInventory.handleUse(inputArray[1], map);
				break;
			default:
				System.out.println("Type help for commands.");
		}
		
		return 0;
	}
		
}
