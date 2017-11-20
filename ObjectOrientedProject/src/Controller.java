import java.util.*;

public class Controller {
		
	public void parseInput(String input, Map map) {
		String[] inputArray = input.split(" ");
		boolean result = true;
			
		switch (inputArray[0]) {
			case "help":
				System.out.println("You have Selected Help: " + '\n'
	    			+ "--> For More Information - Type 'info' " + '\n'
	    			+ "--> To Quit - Type 'quit'"
	    			);
				break;
			case "info":
				System.out.println("Adventure Game");
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
		}
	}
		
}
