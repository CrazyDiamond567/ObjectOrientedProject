import java.util.*;

public class MainMethod {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		//initialize needed objects
		Controller controller = new Controller();
		Map map = new Map();
		Helper helper = new Helper();
		Inventory playerInventory = new Inventory();
		
		//input variable
		String input = "";
		
		//setup rooms
		setupRooms(map);
		
		//give player starting items
		startingInventory(playerInventory);
		
		//starting text
		System.out.println("This is an incomplete game. You can move around and talk to an NPC. Type 'help' for a list of commands.");
		
		while (true) {
			System.out.print("input > ");
			input = reader.nextLine();
			if (input != "") {
				controller.parseInput(input, map, helper, playerInventory);
				input = "";
			}
		}
	}
	
	
	//function to separate out some of the setup work and clean up the code
	public static void setupRooms(Map map) {
		//example rooms
		Room schoolEntrance = new Room("School Entrance","entrance of school, to the north is the Main Hallway.");
		map.addRoomToMap(schoolEntrance, 50, 0, 0);
			
		Room reception = new Room("Front Reception","This is the front reception of the school");
		map.addRoomToMap(reception, 50, 1, 0);
		
		Room hall1DSection1 = new Room("Hall 1D (Section 1)", "You are in Hall 1D");
		map.addRoomToMap(hall1DSection1, 50, 2, 0);
		
		Room hall1DSection2 = new Room("Hall 1D (Section 2)", "You are in Hall 1D");
		map.addRoomToMap(hall1DSection2, 50, 3, 0);
		
		Room hall1DSection3 = new Room("Hall 1D (Section 3)", "You are in Hall 1D");
		map.addRoomToMap(hall1DSection3, 50, 4, 0);
		
		Room hall1DSection4 = new Room("Hall 1D (Section 4)", "You are in Hall 1D");
		map.addRoomToMap(hall1DSection4, 50, 5, 0);
		
		
		//example conversation
		Conversation<String> conversation1 = new Conversation<String>("Hello, my name is bob and I am an example NPC!");
		Conversation.Node node1 = conversation1.root.addOption("1. How do you feel?", "node1", "Nothing, as I am a soulless NPC.");
				
		//example npc
		NPC npc1 = new NPC("bob", conversation1);
				
		//add npc to room
		schoolEntrance.addNPCToRoom(npc1);
	}
	
	public static void startingInventory(Inventory playerInventory) {
		Item flyer = new Item("flyer","This is an example item!");
		
		playerInventory.addItemToInventory(flyer);
	}

}
