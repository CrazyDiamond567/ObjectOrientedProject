<<<<<<< HEAD

public class Controller implements Command {
	Command slot;
	
	public Controller() {}
	
	public void setCommand(Command command) {
		slot = command;
=======
//Omeed was here
public class Controller {

	public static void parseInput(String input) {
		
		String[] commandSplit = input.split(" ");
		
		if (commandSplit[0].equals("test")) {
			System.out.println(commandSplit[1]);
		}
>>>>>>> branch 'master' of https://github.com/CrazyDiamond567/ObjectOrientedProject.git
	}
	
	public void execute(String y) {
		slot.execute(y);
	}
}
