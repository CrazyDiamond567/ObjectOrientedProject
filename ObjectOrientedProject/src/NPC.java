
public class NPC {
	public String name;
	public Conversation conversation;

	public NPC(String name, Conversation conversation) {
		this.name = name;
		this.conversation = conversation;
	}
	
	public void startConversation(Inventory playerInventory, Map map) {
		conversation.start(playerInventory, map);
	}
	
}