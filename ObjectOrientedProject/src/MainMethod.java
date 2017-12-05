import java.util.*;

public class MainMethod {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
        // background music
		// threads are weird.
        Thread t1 = new Thread(new Runnable() {
            public void run() {
            	Jukebox background_music = new Jukebox("Ave_Marimba.wav");
            }});  
        t1.start();
        
		
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
				controller.parseInput(input, map, helper, playerInventory, t1);
				input = "";
			}
		}
	}
	
	
	//function to separate out some of the setup work and clean up the code
	public static void setupRooms(Map map) {
		
		
		//Starting rooms
		Room schoolEntrance = new Room("School Entrance","entrance of school, to the north is the front reception");
		map.addRoomToMap(schoolEntrance, 50, 0, 0);
			
		Room reception = new Room("Front Reception","This is the front reception of the school, to the north is Hallway 1D, to the east is Hallway 1A");
		map.addRoomToMap(reception, 50, 1, 0);
		
		
		// hall 1D
		Room hall1DSection1 = new Room("Hall 1D (Section 1)", "You are in Hall 1D, it continues to the north, to the south is the Front Reception, to the east is classroom 1A1D1");
		map.addRoomToMap(hall1DSection1, 50, 2, 0);
		
		Room classroom1A1D1 = new Room("Classroom 1A1D1", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 1A and the west exit leads to hall 1D");
		map.addRoomToMap(classroom1A1D1, 51, 2, 0);
		
		Room hall1DSection2 = new Room("Hall 1D (Section 2)", "You are in Hall 1D, it continues to the north and south, to the west is classroom 1D2");
		map.addRoomToMap(hall1DSection2, 50, 3, 0);
		hall1DSection2.westBlocker = "This door is locked!";
		
		Room classroom1D2 = new Room("Classroom 1D2", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The east exit leads to hall 1D");
		map.addRoomToMap(classroom1D2, 49, 3, 0);
		
		Room hall1DSection3 = new Room("Hall 1D (Section 3)", "You are in Hall 1D, it continues to the north and south");
		map.addRoomToMap(hall1DSection3, 50, 4, 0);
		
		Room hall1DSection4 = new Room("Hall 1D (Section 4)", "You are in Hall 1D, it continues to the north and south");
		map.addRoomToMap(hall1DSection4, 50, 5, 0);
		
		Room hall1DSection5 = new Room("Hall 1D (Section 5)", "You are in Hall 1D, it continues to the north and south");
		map.addRoomToMap(hall1DSection5, 50, 6, 0);
		
		
		// intersection 1D 1C
		Room hall1D1CIntersection = new Room("Hall 1D 1C Intersection", "This is the intersection of two hallways, 1D to the south and 1C to the east");
		map.addRoomToMap(hall1D1CIntersection, 50, 7, 0);
		
		
		// hall 1C
		Room hall1CSection1 = new Room("Hall 1C (Section 1)", "You are in Hall 1C, it continues to the west and east");
		map.addRoomToMap(hall1CSection1, 51, 7, 0);
		
		Room hall1CSection2 = new Room("Hall 1C (Section 2)", "You are in Hall 1C, it continues to the west and east");
		map.addRoomToMap(hall1CSection2, 52, 7, 0);
		
		Room hall1CSection3 = new Room("Hall 1C (Section 3)", "You are in Hall 1C, it continues to the west and east");
		map.addRoomToMap(hall1CSection3, 53, 7, 0);
		
		Room hall1CSection4 = new Room("Hall 1C (Section 4)", "You are in Hall 1C, it continues to the west and east");
		map.addRoomToMap(hall1CSection4, 54, 7, 0);
		
		Room hall1CSection5 = new Room("Hall 1C (Section 5)", "You are in Hall 1C, it continues to the west and east");
		map.addRoomToMap(hall1CSection5, 55, 7, 0);
		
		
		// intersection 1B 1C
		Room hall1B1CIntersection = new Room("Hall 1B 1C Intersection", "This is the intersection of two hallways, 1B to the south and 1C to the west");
		map.addRoomToMap(hall1B1CIntersection, 56, 7, 0);
		
		
		// hall 1B
		Room hall1BSection5 = new Room("Hall 1B (Section 5)", "You are in Hall 1B, it continues to the north and south");
		map.addRoomToMap(hall1BSection5, 56, 6, 0);
		
		Room hall1BSection4 = new Room("Hall 1B (Section 4)", "You are in Hall 1B, it continues to the north and south");
		map.addRoomToMap(hall1BSection4, 56, 5, 0);
		
		Room hall1BSection3 = new Room("Hall 1B (Section 3)", "You are in Hall 1B, it continues to the north and south");
		map.addRoomToMap(hall1BSection3, 56, 4, 0);
		
		Room hall1BSection2 = new Room("Hall 1B (Section 2)", "You are in Hall 1B, it continues to the north and south");
		map.addRoomToMap(hall1BSection2, 56, 3, 0);
		
		Room hall1BSection1 = new Room("Hall 1B (Section 1)", "You are in Hall 1B, it continues to the north and south");
		map.addRoomToMap(hall1BSection1, 56, 2, 0);
		
		
		// hall 1B 1A Intersection
		Room hall1B1AIntersection = new Room("Hall 1B 1A Intersection", "This is the intersection of two hallways, 1B to the north and 1A to the west");
		map.addRoomToMap(hall1B1AIntersection, 56, 1, 0);
		
		
		// hall 1A
		Room hall1ASection5 = new Room("Hall 1A (Section 5)", "You are in Hall 1A, it continues to the west and east");
		map.addRoomToMap(hall1ASection5, 55, 1, 0);
		
		Room hall1ASection4 = new Room("Hall 1A (Section 4)", "You are in Hall 1A, it continues to the west and east");
		map.addRoomToMap(hall1ASection4, 54, 1, 0);
		
		Room hall1ASection3 = new Room("Hall 1A (Section 3)", "You are in Hall 1A, it continues to the west and east");
		map.addRoomToMap(hall1ASection3, 53, 1, 0);
		
		Room hall1ASection2 = new Room("Hall 1A (Section 2)", "You are in Hall 1A, it continues to the west and east");
		map.addRoomToMap(hall1ASection2, 52, 1, 0);
		
		Room hall1ASection1 = new Room("Hall 1A (Section 1)", "You are in Hall 1A, it continues to the east, to the west is the Front Reception");
		map.addRoomToMap(hall1ASection1, 51, 1, 0);
		
		//example conversation
		Conversation<String> conversation1 = new Conversation<String>("Hello, my name is bob and I am an example NPC!");
		Conversation.Node node1 = conversation1.root.addOption("1. How do you feel?", "node1", "Nothing, as I am a soulless NPC.");
				
		//example npc
		NPC npc1 = new NPC("bob", conversation1);
				
		//add npc to room
		schoolEntrance.addNPCToRoom(npc1);
	}
	
	public static void startingInventory(Inventory playerInventory) {
		
		int[] KeyArray1D2 = {50,3,0};
		Key key1D2 = new Key("1D2Key","This unlocks room 1D2", KeyArray1D2, "west");
		playerInventory.addItemToInventory(key1D2);
	}

}
