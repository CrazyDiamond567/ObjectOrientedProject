import java.util.*;

public class MainMethod {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		//initialize needed objects
		Parser parser = new Parser();
		Avatar avatar = new Avatar();
		Room room1 = new Room("description 1");
		Room room2 = new Room("description 2");
		Room room3 = new Room("description 3");
		
		//initialize player location
		avatar.setLocation(room1);
		
		//initialize input variable
		String input = "";
		
		while (true) {
			System.out.print("input > ");
			input = reader.nextLine();
			if (input != "") {
				parser.parseInput(input);
				input = "";
			}
		}
	}

}
