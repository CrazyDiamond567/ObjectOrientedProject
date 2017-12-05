
public class Key extends Item {
	public int[] unlockLocation = new int[3];
	public String unlockDirection = "";
	
	public Key(String n, String d, int[] unlockLocation, String unlockDirection) {
		super(n, d);
		
		this.unlockLocation = unlockLocation;
		this.unlockDirection = unlockDirection;
	}
	
	public void use(Map map) {
		if ((map.currentLocation[0] == unlockLocation[0]) && (map.currentLocation[1] == unlockLocation[1]) && (map.currentLocation[2] == unlockLocation[2])) {
			System.out.println("You unlocked the door!");
			switch (unlockDirection) {
				case("north"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].northBlocker = "";
					break;
				case("south"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].southBlocker = "";
					break;
				case("east"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].eastBlocker = "";
					break;
				case("west"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].westBlocker = "";
					break;
				case("up"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].upBlocker = "";
					break;
				case("down"):
					map.worldspace[map.currentLocation[0]][map.currentLocation[1]][map.currentLocation[2]].downBlocker = "";
					break;
				default:
					System.out.println("A key that doesn't unlock anything is pointless, add a direction to the key");
					break;
			}
		}
		else {
			System.out.println("You don't see the door this key unlocks.");
		}
	}
}
