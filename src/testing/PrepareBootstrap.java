package testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrepareBootstrap {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/data/training_clean2"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/bootstrap"));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int question_id = Integer.parseInt(arr[3]);
			if (question_id < 100) {
				writer.write(line);
				writer.newLine();
			}
		}
		writer.close();
	}

}
