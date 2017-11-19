import java.util.*;

public class Controller {
		
	public boolean parseInput(String input, Map map) {
		String[] inputArray = input.split(" ");
		boolean result = true;
			
		switch (inputArray[0]) {
			case "move":
				//switch statements can be allowed to fall through, see link
				//https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
				//I am taking advantage of that behavior here
			case "m":
				map.handleMovement(inputArray[1]);
				break;
			case "quit":
				result = false;
				System.out.println("you have quit the game");
				break;
				// quit program
			
		}
		return result;	
	}
		
}
