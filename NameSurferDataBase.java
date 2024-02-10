

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {


	HashMap<String, String> names;
	/*
		Constructor reads a file and extracts the strings into hashMaps:
		 Keys are the names which are extracted by extractName() method
		 Values are the original Strings
	 */
	public NameSurferDataBase(String filename) throws IOException {
		names = new HashMap<>();
	
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String entry = reader.readLine();
		while (entry != null) {
			names.put(extractName(entry), entry);
			entry = reader.readLine();
		}
		reader.close();
	}
	// extracts the name from String
	private String extractName(String entry) {
		entry = entry.toLowerCase();
		String name = "";
		for (int i = 0; i < entry.length(); i++) {
			if (entry.charAt(i) != ' ')
				name += entry.charAt(i);
			else
				break;
		}
		return name;
	}
	// finds a name in the hashmap, finds the string associated with it and passes it to a new NameSurferEntry object
	public NameSurferEntry findEntry(String name) {
		name = name.toLowerCase();
		return new NameSurferEntry(names.get(name));
	}
}

