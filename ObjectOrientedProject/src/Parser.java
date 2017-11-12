import java.util.*;

public class Parser {
	HashMap<String, Command> inputToCommand = new HashMap<String, Command>();

	Controller controller = new Controller();
	RoomCommander roomCommander = new RoomCommander();
	
	public Parser() {
		inputToCommand.put("room", Room);
	}
	
	public void parseInput(String input) {
		String[] inputArray = input.split(" ");
		matchInputToCommand(inputArray);
	}
	
	private void matchInputToCommand(String[] inputArray) {
		if (inputToCommand.containsKey(inputArray[0])) {
			controller.setCommand(inputToCommand.get(inputArray[0]));
			controller.execute(inputArray[1]);
		}
		else {
			System.out.println("Invalid Command (First Word)");
		}
	}

}
