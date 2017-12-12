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
		System.out.println("You are a student.\n"
				+ "The objective of this game is to find your classroom (Physics 7).\n"
				+ "Type 'help' for a list of commands.\n"
				+ "Type 'help' followed by the name of a command for more specific information about that command.\n"
				+ "If you think the music is terrible, type 'stop music'.");
		
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
		Room schoolEntrance = new Room("School Entrance","You regard the grey concrete of the entrance of the school, resenting how unprepared you are.\n"
				+ "Funny, you don't really remember how you got here. Guess it was just too much booze.\n"
				+ "To the north is the front reception.");
		
		//This adds the room to the map. The first argument is the room you will be adding,
		//The coordinate system is column, row, "floor"
		//to go north, increase the second number; to go south, decrease the second number.
		//to go east, increase the first number; to go west, decrease the first number.
		//to go up, increase the third number to 2; to go down, decrease the third number to 0.
		//The first floor is 0 for the third number, the second floor is 2 for the third number.
		map.addRoomToMap(schoolEntrance, 50, 0, 0);
		
		//example room blocker, delete once game is done
		//schoolEntrance.northBlocker = "You can't go that way, talk to bob first!";
		
		Room reception = new Room("Front Reception","The Front Reception is a cheerful room, contrasting the lifelessness of the world outside.\n"
				+ "You see the Dean of the School, greeting students as they walk in.\n"
				+ "to the north is Hallway 1D, to the east is Hallway 1A");
		map.addRoomToMap(reception, 50, 1, 0);
		
		
		// hall 1D
		Room hall1DSection1 = new Room("Hall 1D (Section 1)", "You are in Hall 1D, it continues to the north, to the south is the Front Reception, to the east is classroom 1A1D1");
		map.addRoomToMap(hall1DSection1, 50, 2, 0);
		
		Room classroom1A1D1 = new Room("Classroom 1A1D1", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The south exit leads to hall 1A and the west exit leads to hall 1D");
		map.addRoomToMap(classroom1A1D1, 51, 2, 0);
		
		Room hall1DSection2 = new Room("Hall 1D (Section 2)", "You are in Hall 1D, it continues to the north and south, to the west is classroom 1D2");
		map.addRoomToMap(hall1DSection2, 50, 3, 0);
		
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
		Room cafeteria = new Room("Cafeteria","This is the school cafeteria, the food here smells... suprisingly good, but you don't have time to think about that.\n"
				+ "To the north is Hallway 1C, to the west is Hallway 1D.");
		map.addRoomToMap(cafeteria, 51, 6, 0);
		
		Room hall1CSection2 = new Room("Hall 1C (Section 2)", "You are in Hall 1C, it continues to the west and east, to the north is a men's bathroom");
		map.addRoomToMap(hall1CSection2, 52, 7, 0);
		
		// men's bathroom
		Room menbathroom = new Room("Men's Bathroom","This is the Men's Bathroom. It smells like... garlic? To the south is Hallway 1C");
		map.addRoomToMap(menbathroom, 52, 8, 0);
		
		Room hall1CSection3 = new Room("Hall 1C (Section 3)", "You are in Hall 1C, it continues to the west and east, to the south is classroom 1C3");
		map.addRoomToMap(hall1CSection3, 53, 7, 0);
		
		Room classroom1C3 = new Room("Classroom 1C3", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 1C");
		map.addRoomToMap(classroom1C3, 53, 6, 0);
		
		Room hall1CSection4 = new Room("Hall 1C (Section 4)", "You are in Hall 1C, it continues to the west and east, north is the women's bathroom");
		map.addRoomToMap(hall1CSection4, 54, 7, 0);
		
		// women's bathroom
		Room womenbathroom = new Room("Women's Bathroom","This is the Women's Bathroom. You feel like you shouldn't be here. To the south is Hallway 1C");
		map.addRoomToMap(womenbathroom, 54, 8, 0);
		
		Room hall1CSection5 = new Room("Hall 1C (Section 5)", "You are in Hall 1C, it continues to the west and east, to the south is classroom 1C5B5");
		map.addRoomToMap(hall1CSection5, 55, 7, 0);
		
		Room classroom1C5B5 = new Room("Classroom 1C5B5", "This class is obviously in session. The eyes of the whole class turn to meet yours.\n"
				+ " You sense you are in the wrong classroom. The north exit leads to hall 1C");
		map.addRoomToMap(classroom1C5B5, 55, 6, 0);
		
		
		// intersection 1B 1C
		Room hall1B1CIntersection = new Room("Hall 1B 1C Intersection", "This is the intersection of two hallways, 1B to the south and 1C to the west. To the north is a stairwell.");
		map.addRoomToMap(hall1B1CIntersection, 56, 7, 0);
		
		// Stairwell
		Room stairwellLevel1 = new Room("Stairwell (Level 1)", "This is the Stairwell, It continues upwards; To the south is intersection of two hallways, 1B to the south and 1C to the west");
		map.addRoomToMap(stairwellLevel1, 56, 8, 0);
		stairwellLevel1.upBlocker = "As you go up the stairwell, you find your path blocked by two fighting students.";
		
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
		Room studentlounge = new Room("StudentLounge","This is the break room for the students, but this is hardly the time for you to be taking a break.\n"
				+ "You see a student worker slowly adding items to the Bulletin Board. To the south is Hallway 1A.");
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
		Room hall2B2CIntersection = new Room("Hall 2B 2C Intersection", "This is the intersection of two hallways, 2B to the south and 2C to the west, to the north is a stairwell.");
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
		Room womenbathroomfloor2 = new Room("Women's Bathroom","This is the Women's Bathroom for the students. You feel like you shouldn't be here. To the south is Hallway 2C");
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
		Room library = new Room("Library","This is the library. You see a librarian at the desk, To the north is Hallway 2C, to the west is Hallway 2D");
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
		
		//Example item
		/**
		int[] KeyArray1D2 = {50,3,0};
		Key key1D2 = new Key("1D2Key","This unlocks room 1D2", KeyArray1D2, "west");
		**/
		
		//example conversation, delete once game is done
		/**
		Conversation<String> conversation1 = new Conversation<String>("Hello, my name is bob and I am an example NPC!");
		Conversation.Node node1 = conversation1.root.addOption("1. Here, have $100 dollars! (Give $100 dollars)", "node1", "");
		
		node1.NumTakeItems = 2;
		node1.nameOfItemToTake = "$50";
		node1.itemToGive = key1D2;
		node1.textIfTradeHappened = "here, have a key! and I unblocked the door!";
		node1.unblockDirection = "north";
		node1.nameOfNPCToDelete = "bob";
		node1.leaveText = "I am going away, goodbye";
		
		Conversation.Node node2 = conversation1.root.addOption("2. I hate your guts!", "node2", "");
		node2.loseGame = true;
		node2.loseText = "bob thinks you out of existence.";
				
		//example npc
		NPC npc1 = new NPC("bob", conversation1);
				
		//add npc to room
		schoolEntrance.addNPCToRoom(npc1);
		**/
		//Items in Rooms
		Item courseSchedule = new Item("CourseSchedule", "You look at the Course Schedule, and find that Physics 7 is in 2A1D1.\n"
				+ "That is on the second floor, you will need to find a way up...") ;
		courseSchedule.moveable = false;
		cafeteria.roomInventory.addItemToInventory(courseSchedule);
		
		Item bulletinBoard = new Item("BulletinBoard","Most of the information on the board is useless, but you do see some headlines that jump out at you.\n"
				+ "'Students who are lost can check the Course Schedule in the Cafeteria.'\n"
				+ "'Students should be on the lookout for a male student in a trenchcoat selling illicit substances to students.\n"
				+ "Worse than that, he is undercutting our library by selling used books.'");
		bulletinBoard.moveable = false;
		studentlounge.roomInventory.addItemToInventory(bulletinBoard);
		
		Item cash50 = new Item("$50","This is $50 in cold hard cash. Money does make the world go round.");
		womenbathroom.roomInventory.addItemToInventory(cash50);
		
		Item textbook = new Item("Physics7Textbook","This is supposedly the book you need for Physics 7.");
		
		//NPC's
		
		//Dean Borben
		Conversation<String> conversationBorben = new Conversation<String>("The stooping dean turns to face you, his ill fitting suit seeming to shift with him a little too late.\n"
				+ "'Hello young man! Is there anything this old man can help you with today?'");
		Conversation.Node BorbenNode1 = conversationBorben.root.addOption("1. Where is the Physics 7 classroom?", "Node1", "'I haven't the slightest idea.' the dean booms jovially.\n"
				+ "'But there is a Course Schedule located in the cafeteria to the north, perhaps you can find out there.' the dean finishes.");
		Conversation.Node BorbenNode2 = conversationBorben.root.addOption("2. Where can I buy books?", "Node2", "'Books can be bought in the library on the second floor.' the dean says with a little too much enthusiasm.");
		
		NPC deanBorben = new NPC("Borben", conversationBorben);
		
		reception.addNPCToRoom(deanBorben);
		
		//Custodian Lorry
		Conversation<String> conversationLorry = new Conversation<String>("This custodian was staring at you before you went to talk to him. He addresses you in a shaking voice.\n"
				+ "'I... I know that you are in Physics 7. And... and I know that you need your textbook.'");
		Conversation.Node LorryNode1 = conversationLorry.root.addOption("1. How could you possibly know that?", "Node1", "'I can't say.' His voice doesn't shake at all.\n"
				+ "You silently suspect this custodian is a few cards short of a deck.");
		Conversation.Node LorryNode2 = conversationLorry.root.addOption("2. Where is Physics 7?", "Node2", "He points at the course schedule without speaking. He is still shaking, silently.");
		Conversation.Node LorryNode3 = conversationLorry.root.addOption("3. I need a textbook for Physics 7?", "Node3", "He gives a shaky nod");
		Conversation.Node LorryNode4 = conversationLorry.root.children.get("3. I need a textbook for Physics 7?").addOption("1. Where can I get one?", "Node4", "'The... the dean should have told you on the way in.'\n"
				+ "He silently mutters under his breath, and then stops.\n"
				+ "'Go see the student worker in the student lounge, in the opposite end of the school.' He says this as though it were an order.");
		
		NPC custodianLorry = new NPC("Lorry", conversationLorry);
		
		cafeteria.addNPCToRoom(custodianLorry);
		
		//Student Worker Hambone
		Conversation<String> conversationHambone = new Conversation<String>("This student worker seems... out of it. After trying to get his attention for more than a few moments, his glassy eyes finally turn to meet yours.\n"
				+ "'Peace man, like, what can I do for you?'");
		Conversation.Node HamboneNode1 = conversationHambone.root.addOption("1. Where is Physics 7?", "Node1", "'I don't know man, go look at the course schedule like all the other fishies.'\n"
				+ "It doesn't look like he is interested in helping you.");
		Conversation.Node HamboneNode2 = conversationHambone.root.addOption("2. Where can I get textbooks?", "Node2", "'I don't know man, but if you want some shrooms, go see the dealer in the men's bathroom.'\n"
				+ "You don't want any shrooms, but it might be worth it to see this dealer anyway. If you can afford the time.");
		
		NPC studentHambone = new NPC("Hambone", conversationHambone);
		
		studentlounge.addNPCToRoom(studentHambone);
		
		//SketchyDude
		Conversation<String> conversationDude = new Conversation<String>("Following the smell of garlic, you slowly open one of the bathroom stalls. Inside you see a guy in a black trenchcoat. He smells like garlic.\n"
				+ "'You have entered my domain, have you come to offer tribute to the lord of the bathroom?'\n"
				+ "The smell of garlic is starting to make you feel sick.");
		Conversation.Node DudeNode1 = conversationDude.root.addOption("1. Why the garlic?","Node1","'Vampires'. He nods simply, as though this were a reasonable answer.\n"
				+ "You still can't tell if this is an act or not.");
		Conversation.Node DudeNode2 = conversationDude.root.addOption("2. I heard you sell textbooks.", "Node2", "He sighs. 'Damn it, nobody ever wants the shrooms. Well, almost nobody.'");
		Conversation.Node DudeNode3 = conversationDude.root.children.get("2. I heard you sell textbooks.").addOption("1. I want the textbook for Physics 7. (Give $50).", "Node3", "");
		DudeNode3.NumTakeItems = 1;
		DudeNode3.nameOfItemToTake = "$50";
		DudeNode3.itemToGive = textbook;
		DudeNode3.textIfTradeHappened = "'Here, have the textbook. I gotta move, the authorities are onto me. If the library asks, you never met me'";
		DudeNode3.nameOfNPCToDelete = "SketchyDude";
		DudeNode3.leaveText = "He quickly runs out of the bathroom. Despite him leaving, the area still smells like garlic.";
		
		NPC sketchyDude = new NPC("SketchyDude", conversationDude);
		
		menbathroom.addNPCToRoom(sketchyDude);
		
		//DixieandAbel
		//main flow
		Conversation<String> conversationDixieAbel = new Conversation<String>("Annoyed at your inability to progress, you take a closer look at the fighting students.\n"
				+ "One of them is a woman with a short haircut; dressed in a leather jacket and jeans.\n"
				+ "The other is also a woman, with long flowing hair; dressed in... a dress.\n"
				+ "The woman in the dress is clearly winning despite her handicap, and the jacket-clad woman is looking slightly worse for wear.\n"
				+ "You interrupt thier fight with a loud shout. Both turn to face you.");
		Conversation.Node FightNode1 = conversationDixieAbel.root.addOption("1. Why are you fighting?","Node1","The long haired woman addresses you in a neutral voice.\n"
				+ "'My money was stolen by that woman. Can you help me get it back?'\n"
				+ "The short haired woman immediately replies. 'I did not steal her money and I am not giving her mine!'\n"
				+ "Great. So now you have to resolve a dispute to pass.");
		Conversation.Node FightNode5 = conversationDixieAbel.root.children.get("1. Why are you fighting?").addOption("1. (To the long-haired woman) Why do you think she stole your money?", "Node5",
				"'She has had something against me for a long time. I used the bathroom, and left my purse on the counter.\n"
				+ "When I checked my purse, the $50 bill was missing. She was the only other person in the bathroom'\n"
				+ "She says this all without showing any emotion on her face.");
		
		Conversation.Node FightNode6 = conversationDixieAbel.root.children.get("1. Why are you fighting?").children.get("1. (To the long-haired woman) Why do you think she stole your money?").addOption(
				"1. If I give you $50, that should resolve this, right? (Give the Long Haired Woman $50)", "Node6", "");
		FightNode6.NumTakeItems = 1;
		FightNode6.nameOfItemToTake = "$50";
		FightNode6.unblockDirection = "up";
		FightNode6.textIfTradeHappened = "'Yes. If you are really willing to stick up for that woman.' The long-haired woman takes the money and leaves.";
		FightNode6.nameOfNPCToDelete = "FightingStudents";
		FightNode6.leaveText = "The short-haired woman regards you angrily. 'You shouldn't have done that. I didn't take her money.'\n"
				+ "Having said that, she leaves as well.";
		
		Conversation.Node FightNode7 = conversationDixieAbel.root.children.get("1. Why are you fighting?").children.get("1. (To the long-haired woman) Why do you think she stole your money?").addOption(
				"2. You dropped this in the bathroom. (Give the Long Haired Woman $50)", "Node7", "");
		FightNode7.NumTakeItems = 1;
		FightNode7.nameOfItemToTake = "$50";
		FightNode7.unblockDirection = "up";
		FightNode7.textIfTradeHappened = "'Oh. In that case I apologize.' The long-haired woman takes the money and leaves.\n"
				+ "Just like that. Without saying anything more.";
		FightNode7.nameOfNPCToDelete = "FightingStudents";
		FightNode7.leaveText = "The short-haired woman remarks, 'I don't know why she hates me, but I have had enough of this shit.'\n"
				+ "Having said that, she leaves as well. You are tempted to ask if she is alright, but she is gone before you get the chance.";
		
		//other options
		Conversation.Node FightNode2 = conversationDixieAbel.root.addOption("2. Can you move so I can pass?", "Node2", "The long haired woman slightly shifts to block your way.\n"
				+ "'I am being bullied, and the right thing for you to do would be to help me out.'\n"
				+ "She says this with absolute conviction. You won't be able to pass unless you resolve the situation.");
		Conversation.Node FightNode3 = conversationDixieAbel.root.addOption("3. (Punch the long-haired woman)","Node3","");
		FightNode3.loseGame = true;
		FightNode3.loseText = "Your sucker-punch catches the long haired woman by suprise, and your fist makes contact with her face in a rather satisfying way.\n"
				+ "However, she recovers fast, and her foot moves at blinding speed towards your head. When it connects, your world goes black.\n"
				+ "GAME OVER";
		Conversation.Node FightNode4 = conversationDixieAbel.root.addOption("4. (Punch the short-haired woman)","Node3","");
		FightNode4.loseGame = true;
		FightNode4.loseText = "The short haired woman attempts to dodge your strike, but is slowed by the damage she has already incured.\n"
				+ "You manage to K.O. her, but the Dean of the School chooses that moment to leave his position in the doorway, and he sees you punching another student.\n"
				+ "As a result, you are expelled.\n"
				+ "GAME OVER";
		
		NPC FightingStudents = new NPC("FightingStudents", conversationDixieAbel);
		stairwellLevel1.addNPCToRoom(FightingStudents); 
		
		//Librarian Cindy
		Conversation<String> conversationCindy = new Conversation<String>("This young librarian has pink hair. It isn't dyed, its just pink. She seems to shake with hidden energy.\n"
				+ "'HELLO. HOW CAN I HELP YOU!?' She shouts at the top of her lungs.\n"
				+ "Doesn't this girl know that this is a library?");
		Conversation.Node CindyNode1 = conversationCindy.root.addOption("1. I want to buy the Physics 7 textbook (Give $100 dollars)", "Node1", "");
		CindyNode1.NumTakeItems = 2;
		CindyNode1.nameOfItemToTake = "$50";
		CindyNode1.itemToGive = textbook;
		CindyNode1.textIfTradeHappened = "'THANKS. HAVE A GREAT DAY!' Your head hurts.";
		
		NPC librarianCindy = new NPC("Cindy", conversationCindy);
		library.addNPCToRoom(librarianCindy);
	}
	
	public static void startingInventory(Inventory playerInventory) {		
		
		Item cash50 = new Item("$50","This is $50 in cold hard cash. Money does make the world go round.");
		playerInventory.addItemToInventory(cash50);
		playerInventory.addItemToInventory(cash50);
	}

}
