import java.util.*;

public class MainMethod {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		//initialize needed objects
		Controller controller = new Controller();
		Map map = new Map();
		
		//example rooms
		Room room1 = new Room("School Entrance","entrance of school, to the north is the Main Hallway.");
		map.addRoomToMap(room1, 50, 0, 0);
		
		Room room2 = new Room("Main Hallway","Main Hallway, to the north is a Stairwell, to the south is the School Entrance, to the west is the Main Reception, to the east is a Library.");
		map.addRoomToMap(room2, 50, 1, 0);
		
		Room room3 = new Room("Stairwell(Level 0)","This is Level 0 of a Stairwell, to the south is the Main Hallway, the Stairwell continues up.");
		map.addRoomToMap(room3, 50, 2, 0);
		
		Room room4 = new Room("Main Reception","Go East");
		map.addRoomToMap(room4, 49, 1, 0);
		
		Room room5 = new Room("Library","Go West");
		map.addRoomToMap(room5, 51, 1, 0);
		
		Room room6 = new Room("Stairwell(Level 1)","Go Down");
		map.addRoomToMap(room6, 50, 2, 1);
		
		//example conversation
		Conversation<String> conversation1 = new Conversation<String>("Hello, my name is bob and I am an example NPC!");
		Conversation.Node node1 = conversation1.root.addOption("1. How do you feel?", "node1", "Nothing, as I am a soulless NPC.");
		
		
		
		//example npc
		NPC npc1 = new NPC("bob", conversation1);
		
		//add npc to room
		room1.addNPCToRoom(npc1);
		
		//initialize input variable
		String input = "";
		boolean running = true;
		
		//game loop
		Helper helper = new Helper();
		
		System.out.println("This is an incomplete game. You can move around and talk to an NPC. Type 'help' for a list of commands.");
		
		while (running) {
			System.out.print("input > ");
			input = reader.nextLine();
			if (input != "") {
				controller.parseInput(input, map, helper);
				input = "";
			}
		}
	}

}
