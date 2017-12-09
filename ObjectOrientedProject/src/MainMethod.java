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
		
		//FLOOR 1
		
		//first you create the room. The first argument is the name, the second argument is the description. Make sure to note which ways you can go.
		Room schoolEntrance = new Room("School Entrance","entrance of school, to the north is the front reception");
		
		//This adds the room to the map. The first argument is the room you will be adding,
		//The coordinate system is column, row, "floor"
		//to go north, increase the second number; to go south, decrease the second number.
		//to go east, increase the first number; to go west, decrease the first number.
		//to go up, increase the third number to 2; to go down, decrease the third number to 0.
		//The first floor is 0 for the third number, the second floor is 2 for the third number.
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
		
		Room hall1DSection4 = new Room("Hall 1D (Section 4)", "You are in Hall 1D, it continues to the north and south, to the west is classroom 1D4");
		map.addRoomToMap(hall1DSection4, 50, 5, 0);
		
		Room classroom1D4 = new Room("Classroom 1D4", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The east exit leads to hall 1D");
		map.addRoomToMap(classroom1D4, 49, 5, 0);
		
		Room hall1DSection5 = new Room("Hall 1D (Section 5)", "You are in Hall 1D, it continues to the north and south, to the east is the cafeteria");
		map.addRoomToMap(hall1DSection5, 50, 6, 0);
		
		
		// intersection 1D 1C
		Room hall1D1CIntersection = new Room("Hall 1D 1C Intersection", "This is the intersection of two hallways, 1D to the south and 1C to the east");
		map.addRoomToMap(hall1D1CIntersection, 50, 7, 0);
		
		
		// hall 1C
		Room hall1CSection1 = new Room("Hall 1C (Section 1)", "You are in Hall 1C, it continues to the west and east, to the south is the cafeteria");
		map.addRoomToMap(hall1CSection1, 51, 7, 0);
		
		// cafeteria 
		Room cafeteria = new Room("Cafeteria","This is the cafeteria area for the students, the place for all the yummy foods. To the north is Hallway 1C");
		map.addRoomToMap(cafeteria, 51, 6, 0);
		
		Room hall1CSection2 = new Room("Hall 1C (Section 2)", "You are in Hall 1C, it continues to the west and east, to the north is a men's bathroom");
		map.addRoomToMap(hall1CSection2, 52, 7, 0);
		
		// men's bathroom
		Room menbathroom = new Room("Men's Bathroom","This is the Men's Bathroom for the students, To the south is Hallway 1C");
		map.addRoomToMap(menbathroom, 52, 8, 0);
		
		Room hall1CSection3 = new Room("Hall 1C (Section 3)", "You are in Hall 1C, it continues to the west and east, to the south is classroom 1C3");
		map.addRoomToMap(hall1CSection3, 53, 7, 0);
		
		Room classroom1C3 = new Room("Classroom 1C3", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 1C");
		map.addRoomToMap(classroom1C3, 53, 6, 0);
		
		Room hall1CSection4 = new Room("Hall 1C (Section 4)", "You are in Hall 1C, it continues to the west and east, north is the women's bathroom");
		map.addRoomToMap(hall1CSection4, 54, 7, 0);
		
		// women's bathroom
		Room womenbathroom = new Room("Women's Bathroom","This is the Women's Bathroom for the students, To the south is Hallway 1C");
		map.addRoomToMap(womenbathroom, 54, 8, 0);
		
		Room hall1CSection5 = new Room("Hall 1C (Section 5)", "You are in Hall 1C, it continues to the west and east, to the south is classroom 1C5B5");
		map.addRoomToMap(hall1CSection5, 55, 7, 0);
		
		Room classroom1C5B5 = new Room("Classroom 1C5B5", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 1C");
		map.addRoomToMap(classroom1C5B5, 55, 6, 0);
		
		
		// intersection 1B 1C
		Room hall1B1CIntersection = new Room("Hall 1B 1C Intersection", "This is the intersection of two hallways, 1B to the south and 1C to the west");
		map.addRoomToMap(hall1B1CIntersection, 56, 7, 0);
		
		// Stairwell
		Room stairwellLevel1 = new Room("Stairwell (Level 1)", "This is the Stairwell, It continues upwards; To the south is intersection of two hallways, 1B to the south and 1C to the west");
		map.addRoomToMap(stairwellLevel1, 56, 8, 0);
		
		Room stairwellLevelHalf = new Room("Stairwell (Level 1.5)", "This is the Stairwell, It continues upwards and downwards");
		map.addRoomToMap(stairwellLevelHalf, 56, 8, 1);
		
		Room stairwellLevel2 = new Room("Stairwell (Level 2)", "This is the Stairwell, It continues downwards. To the south is intersection of two hallways, 2B to the south and 2C to the west");
		map.addRoomToMap(stairwellLevel2, 56, 8, 2);
		
		// hall 1B
		Room hall1BSection5 = new Room("Hall 1B (Section 5)", "You are in Hall 1B, it continues to the north and south, to the west is classroom 1C5B5");
		map.addRoomToMap(hall1BSection5, 56, 6, 0);
		
		Room hall1BSection4 = new Room("Hall 1B (Section 4)", "You are in Hall 1B, it continues to the north and south, to the east is classroom 1B4");
		map.addRoomToMap(hall1BSection4, 56, 5, 0);
		
		Room classroom1B4 = new Room("Classroom 1B4", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The west exit leads to hall 1B");
		map.addRoomToMap(classroom1B4, 57, 5, 0);
		
		Room hall1BSection3 = new Room("Hall 1B (Section 3)", "You are in Hall 1B, it continues to the north and south");
		map.addRoomToMap(hall1BSection3, 56, 4, 0);
		
		Room hall1BSection2 = new Room("Hall 1B (Section 2)", "You are in Hall 1B, it continues to the north and south, to the east is classroom 1B2");
		map.addRoomToMap(hall1BSection2, 56, 3, 0);
		
		Room classroom1B2 = new Room("Classroom 1B2", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The west exit leads to hall 1B");
		map.addRoomToMap(classroom1B2, 57, 3, 0);
		
		Room hall1BSection1 = new Room("Hall 1B (Section 1)", "You are in Hall 1B, it continues to the north and south, to the west is the Student Lounge");
		map.addRoomToMap(hall1BSection1, 56, 2, 0);
		
		
		// hall 1B 1A Intersection
		Room hall1B1AIntersection = new Room("Hall 1B 1A Intersection", "This is the intersection of two hallways, 1B to the north and 1A to the west");
		map.addRoomToMap(hall1B1AIntersection, 56, 1, 0);
		
		
		// hall 1A
		Room hall1ASection5 = new Room("Hall 1A (Section 5)", "You are in Hall 1A, it continues to the west and east");
		map.addRoomToMap(hall1ASection5, 55, 1, 0);
		
		// student lounge 
		Room studentlounge = new Room("StudentLounge","This is the break room for the students, to the south is Hallway 1A");
		map.addRoomToMap(studentlounge, 55, 2, 0);
		
		Room hall1ASection4 = new Room("Hall 1A (Section 4)", "You are in Hall 1A, it continues to the west and east, to the south is classroom 1A4");
		map.addRoomToMap(hall1ASection4, 54, 1, 0);
		
		Room classroom1A4 = new Room("Classroom 1A4", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 1A");
		map.addRoomToMap(classroom1A4, 54, 0, 0);
		
		Room hall1ASection3 = new Room("Hall 1A (Section 3)", "You are in Hall 1A, it continues to the west and east, to the north is classroom 1A3");
		map.addRoomToMap(hall1ASection3, 53, 1, 0);
		 
		Room classroom1A3 = new Room("Classroom 1A3", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 1A");
		map.addRoomToMap(classroom1A3, 53, 2, 0);
		
		Room hall1ASection2 = new Room("Hall 1A (Section 2)", "You are in Hall 1A, it continues to the west and east, to the south is classroom 1A2");
		map.addRoomToMap(hall1ASection2, 52, 1, 0);
		
		Room classroom1A2 = new Room("Classroom 1A2", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 1A");
		map.addRoomToMap(classroom1A2, 52, 0, 0);
		
		Room hall1ASection1 = new Room("Hall 1A (Section 1)", "You are in Hall 1A, it continues to the east, to the west is the Front Reception, to the north is classroom 1A1D1");
		map.addRoomToMap(hall1ASection1, 51, 1, 0);
		
		//FLOOR 2
		
		//Hall 2B 2C Intersection
		Room hall2B2CIntersection = new Room("Hall 2B 2C Intersection", "This is the intersection of two hallways, 2B to the south and 2C to the west");
		map.addRoomToMap(hall2B2CIntersection, 56, 7, 2);
		
		
		//Hall 2B
		Room hall2BSection5 = new Room("Hall 2B (Section 5)", "You are in Hall 2B, it continues to the north and south, to the west is classroom 2C5B5");
		map.addRoomToMap(hall2BSection5, 56, 6, 2);
		
		Room hall2BSection4 = new Room("Hall 2B (Section 4)", "You are in Hall 2B, it continues to the north and south, to the east is classroom 2B4");
		map.addRoomToMap(hall2BSection4, 56, 5, 2);
		
		Room classroom2B4 = new Room("Classroom 2B4", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The west exit leads to hall 2B");
		map.addRoomToMap(classroom2B4, 57, 5, 2);
		
		Room hall2BSection3 = new Room("Hall 2B (Section 3)", "You are in Hall 2B, it continues to the north and south");
		map.addRoomToMap(hall2BSection3, 56, 4, 2);
		
		Room hall2BSection2 = new Room("Hall 2B (Section 2)", "You are in Hall 2B, it continues to the north and south, to the east is classroom 2B2");
		map.addRoomToMap(hall2BSection2, 56, 3, 2);
		
		Room classroom2B2 = new Room("Classroom 2B2", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The west exit leads to hall 2B");
		map.addRoomToMap(classroom2B2, 57, 3, 2);
		
		Room hall2BSection1 = new Room("Hall 2B (Section 1)", "You are in Hall 2B, it continues to the north and south, to the west is the Teacher's Lounge");
		map.addRoomToMap(hall2BSection1, 56, 2, 2);
		
		//Hall 2C
		Room hall2CSection5 = new Room("Hall 2C (Section 5)", "You are in Hall 2C, it continues to the west and east, to the south is classroom 2C5B5");
		map.addRoomToMap(hall2CSection5, 55, 7, 2);
		
		Room classroom2C5B5 = new Room("Classroom 2C5B5", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 2C");
		map.addRoomToMap(classroom2C5B5, 55, 6, 2);
		
		Room hall2CSection4 = new Room("Hall 2C (Section 4)", "You are in Hall 2C, it continues to the west and east, to the north is women's bathroom");
		map.addRoomToMap(hall2CSection4, 54, 7, 2);
		
		//women's bathroom
		Room womenbathroomfloor2 = new Room("Women's Bathroom","This is the Women's Bathroom for the students, To the south is Hallway 2C");
		map.addRoomToMap(womenbathroomfloor2, 54, 8, 2);
		
		Room hall2CSection3 = new Room("Hall 2C (Section 3)", "You are in Hall 2C, it continues to the west and east, to the south is classroom 2C3");
		map.addRoomToMap(hall2CSection3, 53, 7, 2);
		
		//Classroom 2C3
		Room classroom2C3 = new Room("Classroom 2C3", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 2C");
		map.addRoomToMap(classroom2C3, 53, 6, 2);
	
		Room hall2CSection2 = new Room("Hall 2C (Section 2)", "You are in Hall 2C, it continues to the west and east, to the north is men's bathroom");
		map.addRoomToMap(hall2CSection2, 52, 7, 2);
		
		//men's bathroom
		Room menbathroomfloor2 = new Room("Men's Bathroom","This is the men's Bathroom for the students, To the south is Hallway 2C");
		map.addRoomToMap(menbathroomfloor2, 52, 8, 2);
		
		Room hall2CSection1 = new Room("Hall 2C (Section 1)", "You are in Hall 2C, it continues to the west and east, to the south is the library");
		map.addRoomToMap(hall2CSection1, 51, 7, 2);
		
		//library
		Room library = new Room("Library","This is the library for the students, To the north is Hallway 2C");
		map.addRoomToMap(library, 51, 6, 2);
		
		//Hall 2D 2C Intersection
		Room hall2D2CIntersection = new Room("Hall 2D 2C Intersection", "This is the intersection of two hallways, 2D to the south and 2C to the east");
		map.addRoomToMap(hall2D2CIntersection, 50, 7, 2);
		
		//Hall 2D
		
		Room hall2DSection5 = new Room("Hall 2D (Section 5)", "You are in Hall 2D, it continues to the south and north, to the north is Hall 2D 2C Intersection");
		map.addRoomToMap(hall2DSection5, 50, 6, 2);
		
		Room hall2DSection4 = new Room("Hall 2D (Section 4)", "You are in Hall 2D, it continues to the south and north, to the west is classroom 2D4");
		map.addRoomToMap(hall2DSection4, 50, 5, 2);
		
		//Classroom 2D4
		Room classroom2D4 = new Room("Classroom 2D4", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The east exit leads to hall 2D");
		map.addRoomToMap(classroom2D4, 49, 5, 2);
		
		Room hall2DSection3 = new Room("Hall 2D (Section 3)", "You are in Hall 2D, it continues to the south and north, to the north is hall 2D");
		map.addRoomToMap(hall2DSection3, 50, 4, 2);
		
		Room hall2DSection2 = new Room("Hall 2D (Section 2)", "You are in Hall 2D, it continues to the south and north, to the west is classroom 2D2");
		map.addRoomToMap(hall2DSection2, 50, 3, 2);
		
		//Classroom 2D2
		Room classroom2D2 = new Room("Classroom 2D2", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The east exit leads to hall 2D");
		map.addRoomToMap(classroom2D2, 49, 3, 2);
		
		Room hall2DSection1 = new Room("Hall 2D (Section 1)", "You are in Hall 2D, it continues to the south and north, to the north is hall 2D");
		map.addRoomToMap(hall2DSection1, 50, 2, 2);
		
		//Hall 2A 2D Intersection
		Room hall2A2DIntersection = new Room("Hall 2A 2D Intersection", "This is the intersection of two hallways, 2D to the north and 2A to the east");
		map.addRoomToMap(hall2A2DIntersection, 50, 1, 2);
		
		//Hall 2A
		Room hall2ASection1 = new Room("Hall 2A (Section 1)", "You are in Hall 2A, it continues to the east and south, to the west is Hall 2A 2D Intersection");
		map.addRoomToMap(hall2ASection1, 51, 1, 2);
		
		//Classroom 2A1D1
		Room classroom2A1D1 = new Room("Classroom 2A1D1", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 2A");
		map.addRoomToMap(classroom2A1D1, 51, 2, 2);
		
		Room hall2ASection2 = new Room("Hall 2A (Section 2)", "You are in Hall 2A, it continues to the east and south, to the east is Hall 2A");
		map.addRoomToMap(hall2ASection2, 52, 1, 2);
		
		//Classroom 2A2
		Room classroom2A2 = new Room("Classroom 2A2", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 2A");
		map.addRoomToMap(classroom2A2, 52, 0, 2);
		
		Room hall2ASection3 = new Room("Hall 2A (Section 3)", "You are in Hall 2A, it continues to the east and south, to the north is classroom 2A3");
		map.addRoomToMap(hall2ASection3, 53, 1, 2);
		
		//Classroom 2A3
		Room classroom2A3 = new Room("Classroom 2A3", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 2A");
		map.addRoomToMap(classroom2A3, 53, 2, 2);
		
		Room hall2ASection4 = new Room("Hall 2A (Section 4)", "You are in Hall 2A, it continues to the east and south, to the south is classroom 2A4");
		map.addRoomToMap(hall2ASection4, 54, 1, 2);
		
		//Classroom 2A4
		Room classroom2A4 = new Room("Classroom 2A4", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 2A");
		map.addRoomToMap(classroom2A4, 54, 0, 2);
		
		Room hall2ASection5 = new Room("Hall 2A (Section 5)", "You are in Hall 2A, it continues to the east and south, to the north is teacher's lounge");
		map.addRoomToMap(hall2ASection5, 55, 1, 2);
		
		//Hall 2A 2B Intersection
		Room hall2A2Bintersection = new Room("Hall 2A 2B Intersection", "This is the intersection of two hallways, 2B to the north and 2A to the west");
		map.addRoomToMap(hall2A2Bintersection, 56, 1, 2);
		
		//Teacher's Lounge
		Room teacherslounge = new Room("Teacher's Lounge","This is the Teacher's Lounge for the students, To the south is Hallway 2A");
		map.addRoomToMap(teacherslounge, 55, 2, 2);
		
		
		
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
