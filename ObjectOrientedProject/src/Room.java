import java.util.*;

public class Room {
	public String name = null;
	public String description = null;
	public ArrayList<NPC> npcList = new ArrayList<NPC>();
	
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
	
	public void handleTalk(String npcName) {
		//left off here
		NPC npc = searchRoomForNPC(npcName);
		if (npc != null) {
			npc.startConversation();
		}
		else {
			System.out.println("You don't see anybody with that name in this room");
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
