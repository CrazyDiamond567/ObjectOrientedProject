
public class Item {
	public String name = null;
	public String description = null;
		
	public Item(String n, String d) {
		name = n;
		description = d;
	}
	
	public void use(Map map) {
		System.out.println("You can't see how this item would be used");
	}
}
