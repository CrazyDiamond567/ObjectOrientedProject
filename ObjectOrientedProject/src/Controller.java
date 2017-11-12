public class Controller {

	public static void parseInput(String input) {
		
		String[] commandSplit = input.split(" ");
		
		public void parseInput(String input) {
			String[] inputArray = input.split(" ");
			matchInputToCommand(inputArray);
		}
		
		if (commandSplit[0].equals("test")) {
			System.out.println(commandSplit[1]);
		}
	}
	
}
