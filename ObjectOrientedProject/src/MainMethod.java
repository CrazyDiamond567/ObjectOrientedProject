import java.util.*;

public class MainMethod {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		//initialize needed objects
		Controller controller = new Controller();
		Map map = new Map();
		
		Room room1 = new Room("School Entrance","entrance of school, to the north is the Main Hallway");
		map.addRoomToMap(room1, 50, 0, 0);
		
		Room room2 = new Room("Main Hallway","Main Hallway,to the north is a Stairwell, to the south is the School Entrance, to the west is the Main Reception, to the east is a Library");
		map.addRoomToMap(room2, 50, 1, 0);
		
		Room room3 = new Room("Stairwell(Level 0)","This is level 0 of a stair, to the south is the Main Hallway, the stairwell continues up");
		map.addRoomToMap(room3, 50, 2, 0);
		
		Room room4 = new Room("Main Reception","Go East");
		map.addRoomToMap(room4, 49, 1, 0);
		
		Room room5 = new Room("Library","Go West");
		map.addRoomToMap(room5, 51, 1, 0);
		
		Room room6 = new Room("Stairwell(Level 1)","Go Down");
		map.addRoomToMap(room6, 50, 2, 1);
		
		//initialize input variable
		String input = "";
		
		
		//game loop
		while (true) {
			System.out.print("input > ");
			input = reader.nextLine();
			if (input != "") {
				controller.parseInput(input, map);
				input = "";
			}
		}
	}

}
