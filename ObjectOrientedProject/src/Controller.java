import java.util.*;

public class Controller {
		
	public void parseInput(String input, Map map, Helper helper) {
		
		if (input.equals("help")) {
			System.out.println("Commands: move, talk");
		}
		else {
		
		String[] inputArray = input.split(" ");
		boolean result = true;
			
		switch (inputArray[0]) {
			case "help":
				helper.handleHelp(inputArray[1]);
				break;
			case "info":
				System.out.println("Adventure Game");
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
				System.out.println("you have quit the game");
				System.exit(0);
				// quit program
			default:
				System.out.println("type help for commands");
		}
		}
	}
		
}
