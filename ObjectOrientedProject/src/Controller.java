import java.util.*;

public class Controller {
		
	public boolean parseInput(String input, Map map) {
		String[] inputArray = input.split(" ");
		boolean result = true;
			
		switch (inputArray[0]) {
			case "move":
			   //move function code 
				break;
			
			case "m":
				map.handleMovement(inputArray[1]);
				break;
			case "quit":
				result = false;
				break;
				// quit program
			
		}
		return result;	
	}
		
}
