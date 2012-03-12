package strategies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SVMPrep {
	
	public void formatForSVM(String input, String output) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(input)));
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output)));
		String line;
		while ((line = reader.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
	}

}
