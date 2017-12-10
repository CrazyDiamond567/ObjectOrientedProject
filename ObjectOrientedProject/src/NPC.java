
public class NPC {
	public String name;
	public Conversation conversation;

	public NPC(String name, Conversation conversation) {
		this.name = name;
		this.conversation = conversation;
	}
	
	public void startConversation() {
		conversation.start();
	}
	
	
}
