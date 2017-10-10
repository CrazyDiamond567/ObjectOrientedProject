
public class Controller {

	public static void parseInput(String input) {
		
		String[] commandSplit = input.split(" ");
		
		if (commandSplit[0].equals("test")) {
			System.out.println(commandSplit[1]);
		}
	}
	
}
