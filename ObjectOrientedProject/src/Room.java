import java.util.*;

public class Room {
	public String name = null;
	public String description = null;
	public ArrayList<NPC> npcList = new ArrayList<NPC>();
	public Inventory roomInventory = new Inventory();
	
	public String northBlocker = "";
	public String southBlocker = "";
	public String westBlocker = "";
	public String eastBlocker = "";
	public String upBlocker = "";
	public String downBlocker = "";
	
	public Room(String n, String d) {
		name = n;
		description = d;
	}
	
	public void addNPCToRoom(ArrayList<NPC> al) {
		npcList.addAll(al);
	}
	public void addNPCToRoom(NPC npc) {
		npcList.add(npc);
	}
	public void printNPCs() {
		System.out.println("You also see the following people here...");
		for(int i = 0; i < npcList.size(); i++) {   
		    System.out.print(npcList.get(i).name + ", ");
		}
		System.out.println();
	}
	
	public void handleTalk(String npcName, Inventory playerInventory, Map map) {
		NPC npc = searchRoomForNPC(npcName);
		if (npc != null) {
			npc.startConversation(playerInventory, map);
		}
		else {
			System.out.println("You don't see anybody with that name in this room.");
		}
	}
	
	public NPC searchRoomForNPC(String npcName) {
		if (npcList != null) {
			for (NPC npc : npcList) {
				if (npc.name.equals(npcName)) {
					return npc;
				}
			}
		}
		return null;
	}

}
