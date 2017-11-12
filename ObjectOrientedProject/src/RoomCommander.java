
public class RoomCommander implements Command {
	Room x;
	
	// the pattern wants the constructor to require a room object, but that would require a command object for every room...
	/**
	public RoomCommander(Room x) {
		this.x = x;
	}
	**/
	
	public void SetObject(Avatar avatar) {
		this.x = avatar.getLocation();
	}
	
	public void execute(String y) {
		if (y.equals("look")) {
			x.test();
		}
	}
}


