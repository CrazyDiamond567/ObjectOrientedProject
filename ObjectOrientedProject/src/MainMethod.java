import java.util.*;

public class MainMethod {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Controller controller = new Controller();
		
		String input = "";
		
		while (true) {
			System.out.print("input > ");
			input = reader.nextLine();
			if (input != "") {
				controller.parseInput(input);
				input = "";
			}
		}
	}

}
