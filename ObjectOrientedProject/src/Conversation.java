import java.util.*;
import java.io.IOException;

public class Conversation<C> { //java generics are cool. learn something new every day.
	    public Node<C> root;

	    public Conversation(C rootData) {
	        root = new Node<C>();
	        root.data = rootData;
	        root.children = new HashMap<String, Node<C>>();
	    }

	    public class Node<C> {
	        public C data;
	        public Node<C> parent;
	        public HashMap<String, Node<C>> children = new HashMap<String, Node<C>>();
	        public boolean visited = true;
	        
	        public Node<C> addOption(String option ,C child, C data) {
		        Node<C> childNode = new Node<C>();
		        childNode.parent = this;
		        childNode.data = data;
		        this.children.put(option, childNode);
		        return childNode;
		    }
	        
	    }
	    
	    public void start() { // I don't think this is approved of by the coding gods...
	    	try {
	    		start(root);
	    	} catch (IOException ouch) {
	    		//don't do anything....
	    	}
	    }
	    
	    public void start(Node<C> CurrentNode) throws IOException {
			Scanner reader = new Scanner(System.in);
						
	    	System.out.println(CurrentNode.data+"\n");
	    	System.out.println("Possible responses");
	    	System.out.println("0. Go back to previous response");
	    	
	    	for (String option : CurrentNode.children.keySet()) {
	    		System.out.println(option);
	    	}
	    	
	    	System.out.print("input > ");
	    	
	    	String input = reader.nextLine();
	    	if ("bye".equals(input)) {
	    		System.out.println("You have exited the conversation.");
	    		throw new IOException("");
	    	}
	    	if ('0' == input.charAt(0)) {
	    		if (CurrentNode.parent != null) {
	    			start(CurrentNode.parent);
	    		}
	    		else {
	    			System.out.println("You are already at the root of the conversation.\n");
	    			start(CurrentNode);
	    		}
	    	}
	    	
	    	if (!CurrentNode.children.isEmpty()) {
	    		for (String option : CurrentNode.children.keySet()) {
		    		if (option.charAt(0) == input.charAt(0)) {
		    			start(CurrentNode.children.get(option));
		    		}
		    	}
	    	}
	    	
	    	System.out.println("You didn't enter a valid number!\n");
	    	start(CurrentNode);
	    }
	    
	    

}
