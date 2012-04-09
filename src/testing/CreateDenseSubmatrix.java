package testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CreateDenseSubmatrix {
	
	static Map<Integer, Integer> userNumbers = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> questionNumbers = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws IOException {
		System.out.print("Aggregating user counts...");
		new File("src/data/dense_bootstrap").delete();
		BufferedReader reader = new BufferedReader(new FileReader("src/data/training_clean2"));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int question_id = Integer.parseInt(arr[3]);
			int user_id = Integer.parseInt(arr[2]);
			if (!userNumbers.containsKey(user_id)) userNumbers.put(user_id, 0);
			userNumbers.put(user_id, userNumbers.get(user_id) + 1);
		}
		System.out.println("done");
		
		System.out.print("Sorting users...");
		List<Integer> sortedUsers = new ArrayList<Integer>(userNumbers.keySet());
		Collections.sort(sortedUsers, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1,
					Integer o2) {
				return userNumbers.get(o2).compareTo(userNumbers.get(o1));
			}
		});
		System.out.println("done");
		System.out.print("Getting top users...");
		System.out.println(sortedUsers.size());
		List<Integer> topUsers = sortedUsers.subList(0, 300);
		System.out.println("done");
		
		System.out.print("Aggregating question counts...");
		new File("src/data/dense_bootstrap").delete();
		reader = new BufferedReader(new FileReader("src/data/training_clean2"));
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int question_id = Integer.parseInt(arr[3]);
			int user_id = Integer.parseInt(arr[2]);
			if (topUsers.contains(user_id)) {
				if (!questionNumbers.containsKey(question_id)) questionNumbers.put(question_id, 0);
				questionNumbers.put(question_id, questionNumbers.get(question_id) + 1);
			}
		}
		System.out.println("done");
		
		System.out.print("Sorting questions...");
		List<Integer> sortedQuestions = new ArrayList<Integer>(questionNumbers.keySet());
		Collections.sort(sortedQuestions, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1,
					Integer o2) {
				return questionNumbers.get(o2).compareTo(questionNumbers.get(o1));
			}
		});
		System.out.println("done");
		System.out.print("Getting top questions...");
		System.out.println(sortedQuestions.size());
		List<Integer> topQuestions = sortedQuestions.subList(0, 300);
		System.out.println("done");
		
		System.out.println("Writing to file");
		new File("src/data/dense_bootstrap").delete();
		reader = new BufferedReader(new FileReader("src/data/training_clean2"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/dense_bootstrap"));
		line = reader.readLine();
		writer.write(line);
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int question_id = Integer.parseInt(arr[3]);
			int user_id = Integer.parseInt(arr[2]);
			if (topUsers.contains(user_id) && topQuestions.contains(question_id)) {
				writer.newLine();
				writer.write(line);
			}
		}
		writer.close();
		
	}
	
}
