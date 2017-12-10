import java.util.*;

public class Inventory {
	public ArrayList<Item> itemList = new ArrayList<Item>();
	
	public void addItemToInventory(ArrayList<Item> al) {
		itemList.addAll(al);
	}
	public void addItemToInventory(Item item) {
		itemList.add(item);
	}
	public Item removeItemFromInventory(String name) {
		for(int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).name.equals(name)) {
				Item removedItem = itemList.get(i);
				itemList.remove(i);
				return removedItem;
			}
		}
		return null;
	}
	public void listItems() {
		for(int i = 0; i < itemList.size(); i++) {   
		    System.out.print(itemList.get(i).name + ", ");
		}
		System.out.println();
	}
	public String handleInspect(String name) {
		for(int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).name.equals(name)) {
				Item tempitem = itemList.get(i);
				return tempitem.description;
			}
		}
		return "";
	}
	
	public void handleUse(String name, Map map) {
		for(int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).name.equals(name)) {
				Item tempitem = itemList.get(i);
				tempitem.use(map);
			}
		}
	}
}
