
public class Helper {
	public void handleHelp(String secondWord) {
		switch (secondWord) {
		case "move":
			System.out.println("move takes a direction as an argument (north, south, west, east, up, down");
			break;
		case "talk":
			System.out.println("talk takes an NPC in the same room as you as an argument");
			break;
	
		}	
	}

}
