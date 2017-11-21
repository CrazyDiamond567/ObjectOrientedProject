import java.util.*;

public class Controller {
		
	public void parseInput(String input, Map map, Helper helper) {
		String[] inputArray = input.split(" ");
		boolean result = true;
			
		switch (inputArray[0]) {
			case "help":
				System.out.println("Command List: \n" +
						"move or m: Moves the player in the specified direction. Example: \"move north or \"m n\". \n" +
						"talk or t: Talks to the target NPC. Example: \"talk bob\" or \"t bob\". \n" +
						"bye: Exits the conversation.");
				break;
			case "info":
				System.out.println("The Ineptitude \n" +
						"Created by: \n" +
						"Omeed Ghafari, Arias Talari, Vitali Taranto");
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
				room.handleTalk(inputArray[1]);
				break;
			case "quit":
				System.out.println("You have quit the game.");
				System.exit(0);
				// quit program
			default:
				System.out.println("Type help for commands.");
		}
	}
		
}
