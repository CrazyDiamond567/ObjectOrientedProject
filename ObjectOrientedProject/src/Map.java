import java.util.*;

public class Map {
	
	Room[][][] worldspace = new Room[100][100][5];
	int[] currentLocation = {50,0,0};
	
	public void addRoomToMap(Room room, int x, int y, int z) {
		worldspace[x][y][z] = room;
	}
	public Room returnCurrentRoom() {
		return worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]];
	}
	public void handleLook() {
		System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
		System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
	}
	public void handleCheck() {
		worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].roomInventory.listItems();
		System.out.println("");
		worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].printNPCs();
	}
	public void handleDrop(Item tempitem) {
		worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].roomInventory.addItemToInventory(tempitem);
	}
	public Item handlePickup(String name) {
		return worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].roomInventory.removeItemFromInventory(name);
	}
	public String handleInspect(String name) {
		return worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].roomInventory.handleInspect(name);
	}
	public void handleMovement(String direction) {
		switch (direction) {
			case "north":
			case "n":
				moveNorth();
				break;
			case "south":
			case "s":
				moveSouth();
				break;
			case "east":
			case "e":
				moveEast();
				break;
			case "west":
			case "w":
				moveWest();
				break;
			case "up":
			case "u":
				moveUp();
				break;
			case "down":
			case "d":
				moveDown();
				break;
		}
	}
	public void moveNorth() {
		if (currentLocation[1] == 99) {
			System.out.println("You cannot go that way!");
		}
		else {
			if (worldspace[currentLocation[0]][currentLocation[1]+1][currentLocation[2]] != null) {
				if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].northBlocker == "") {
					currentLocation[1] = currentLocation[1]+1;
					System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
					System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
				}
				else {
					System.out.println(worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].northBlocker);
				}
			}
			else {
				System.out.println("You cannot go that way!");
			}
		}
	}
	public void moveSouth() {
		if (currentLocation[1] == 0) {
			System.out.println("You cannot go that way!");
		}
		else {
			if (worldspace[currentLocation[0]][currentLocation[1]-1][currentLocation[2]] != null) {
				if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].southBlocker == "") {
					currentLocation[1] = currentLocation[1]-1;
					System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
					System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
				}
				else {
					System.out.println(worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].southBlocker);
				}
			}
			else {
				System.out.println("You cannot go that way!");
			}
		}
	}
	public void moveEast() {
		if (currentLocation[0] == 99) {
			System.out.println("You cannot go that way!");
		}
		else {
			if (worldspace[currentLocation[0]+1][currentLocation[1]][currentLocation[2]] != null) {
				if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].eastBlocker == "") {
					currentLocation[0] = currentLocation[0]+1;
					System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
					System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
				}
				else {
					System.out.println(worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].eastBlocker);
				}
			}
			else {
				System.out.println("You cannot go that way!");
			}
		}
	}
	public void moveWest() {
		if (currentLocation[0] == 0) {
			System.out.println("You cannot go that way!");
		}
		else {
			if (worldspace[currentLocation[0]-1][currentLocation[1]][currentLocation[2]] != null) {
				if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].westBlocker == "") {
					currentLocation[0] = currentLocation[0]-1;
					System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
					System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
				}
				else {
					System.out.println(worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].westBlocker);
				}
			}
			else {
				System.out.println("You cannot go that way!");
			}
		}
	}
	public void moveDown() {
		if (currentLocation[2] == 0) {
			System.out.println("You cannot go that way!");
		}
		else {
			if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]-1] != null) {
				if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].downBlocker == "") {
					currentLocation[2] = currentLocation[2]-1;
					System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
					System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
				}
				else {
					System.out.println(worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].downBlocker);
				}
			}
			else {
				System.out.println("You cannot go that way!");
			}
		}
		
	}
	public void moveUp() {
		if (currentLocation[2] == 4) {
			System.out.println("You cannot go that way!");
		}
		else {
			if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]+1] != null) {
				if (worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].upBlocker == "") {
					currentLocation[2] = currentLocation[2]+1;
					System.out.println("Room Name: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].name);
					System.out.println("Room Description: "+worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].description);
				}
				else {
					System.out.println(worldspace[currentLocation[0]][currentLocation[1]][currentLocation[2]].upBlocker);
				}
			}
			else {
				System.out.println("You cannot go that way!");
			}
		}
	}
	
	

}
