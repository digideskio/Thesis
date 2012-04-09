package testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrepareBootstrap {
	
	static Map<Integer, Set<Integer>> userToQuestions = new HashMap<Integer, Set<Integer>>();
	static Map<Integer, Integer> userNumbers = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> questionPopularity = new HashMap<Integer, Integer>();
	
	static Set<Integer> bootstrapUsers = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		new File("src/data/bootstrap").delete();
		BufferedReader reader = new BufferedReader(new FileReader("src/data/training_clean2"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/bootstrap"));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int question_id = Integer.parseInt(arr[3]);
			int user_id = Integer.parseInt(arr[2]);
			if (question_id < 100) {
				writer.write(line);
				writer.newLine();
				bootstrapUsers.add(user_id);
			}
//			if (!userToQuestions.containsKey(user_id)) userToQuestions.put(user_id, new HashSet<Integer>());
//			userToQuestions.get(user_id).add(question_id);
			if (!userNumbers.containsKey(user_id)) userNumbers.put(user_id, 0);
			userNumbers.put(user_id, userNumbers.get(user_id) + 1);
		}
		
		System.out.println(bootstrapUsers.size());

		
		//		for (Set<Integer> questions : userToQuestions.values()) {
//			for (Integer question : questions) {
//				if (!questionPopularity.containsKey(question)) questionPopularity.put(question, 1);
//				questionPopularity.put(question, questionPopularity.get(question) + 1);
//			}
//		}
//		List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(questionPopularity.entrySet());
//		Collections.sort(list, new Comparator<Object>() {
//			public int compare(Object o1, Object o2) {
//				return ((Comparable) ((Map.Entry) (o1)).getValue())
//						.compareTo(((Map.Entry) (o2)).getValue());
//			}
//		});
//		
//		int i = 0;
//		for (Entry<Integer, Integer> entry : list) {
//			if (i < 100) System.out.println(entry.getKey());
//			i++;
//		}
		
		
//		writer.close();
	}

}
