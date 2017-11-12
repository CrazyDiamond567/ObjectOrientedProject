import java.util.*;

public class Controller {
		
	public void parseInput(String input, Map map) {
		String[] inputArray = input.split(" ");
			
		switch (inputArray[0]) {
			case "move":
			case "m":
				map.handleMovement(inputArray[1]);
				break;
		}
			
	}
		
}
