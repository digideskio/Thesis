package testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class PrepareListOfUsers {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/data/training_clean2"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/user_list"));
		String line = reader.readLine();
		HashSet<Integer> user_ids = new HashSet<Integer>();
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int user_id = Integer.parseInt(arr[2]);
			user_ids.add(user_id);
		}
		System.out.println(user_ids.size());
		for (Integer id : user_ids) {
			writer.write("" + id);
			writer.newLine();
		}
		writer.close();
	}

}
