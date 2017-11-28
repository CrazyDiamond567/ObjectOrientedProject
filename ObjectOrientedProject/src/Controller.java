import java.util.*;

public class Controller {
		
	public int parseInput(String input, Map map, Helper helper) {
		//single word parser
		switch (input) {
			case "help":
				System.out.println("Command List: \n" +
					"move or m: Moves the player in the specified direction. Example: \"move north or \"m n\". \n" +
					"talk or t: Talks to the target NPC. Example: \"talk bob\" or \"t bob\". \n" +
					"exit: Exits the conversation.");
				return 0;
				
			case "quit":
				System.out.println("You have quit the game.");
				System.exit(0);
				// quit program
			case "info":
				System.out.println("The Ineptitude \n" +
						"Created by: \n" +
						"Omeed Ghafari, Arias Talari, Vitali Taranto");
				return 0;
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
				room.handleTalk(inputArray[1]);
				break;
			default:
				System.out.println("Type help for commands.");
		}
		
		return 0;
	}
		
}
