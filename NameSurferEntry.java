
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {


	private String name;

	private ArrayList<Integer> ranks;
	// The constructor initializes the arraylist,
	// fills it with values from the String parameter and finds the name associated with the string using getName() method
	public NameSurferEntry(String line) {
		name = getName(line);
		ranks = new ArrayList<>();
		getRanks(line);
	}
	// extracts the name from the string
	private String getName(String line) {
		String name = "";
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) != ' ')
				name += line.charAt(i);
			else
				break;
		}
		return name;
	}
	// public method to get the name easily
	public String getName() {
		return name;
	}
	// method for filling the arraylist with integer values
	private void getRanks(String line) {
		String entry = line;
		entry = entry.replaceAll(name + " ", "");
		entry = entry + " ";
		String num = "";
		for (int i = 0; i < entry.length(); i++) {
			if (Character.isDigit(entry.charAt(i)))
				num += entry.charAt(i);
			else {
				ranks.add(Integer.parseInt(num));
				num = "";
			}
		}
	}
	// returns the rank associated with the decade parameter (the parameter is a difference of the current decade and the starting decade)
	public int getRank(int decade) {
		return ranks.get(decade / 10);
	}
	// converts the original String into a more easily readable String
	public String toString() {
		String numbers = "[";
		for (int number : ranks) {
			numbers += number + " ";
		}
		numbers = numbers + "]";
		return name + " " + numbers;
	}
}

