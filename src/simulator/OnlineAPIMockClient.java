package simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OnlineAPIMockClient {
	
	OnlineAPI api;
	Map<Integer, Integer> ANSWER_KEY;
	static String[] arguments = {"pairwise"};
	
	public static void main(String[] args) {
		OnlineAPIMockClient client = new OnlineAPIMockClient();
		client.simulate(new File("src/data/training_clean2"));
	}
	
	public OnlineAPIMockClient() {
		api = new OnlineAPI(arguments);
		ANSWER_KEY = getAnswerKey();
	}
	
	public void simulate(File input) {
		System.out.println("Training...");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String line = reader.readLine();
			int i = 0;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split(",");
				if (i%10000 == 0) System.out.println(i);
				i++;
				boolean correct = arr[0].equals("1");
				int user_id = Integer.parseInt(arr[2]);
				int question_id = Integer.parseInt(arr[3]);
				int response_id = Integer.parseInt(arr[12]);
				if (question_id < 1000) api.update(user_id, question_id, response_id, correct);
				else api.update(user_id, question_id, response_id);
			}
			
			System.out.println("Testing...");
			int total = 0;
			int numCorrect = 0;
			int a_id;
			for (int q_id = 1000; q_id < 6046; q_id++) {
				if (ANSWER_KEY.containsKey(q_id)) {
					total++;
					a_id = api.getAnswer(q_id);
					if (a_id == ANSWER_KEY.get(q_id)) numCorrect++;
				}
			}
			
			System.out.println(numCorrect / (double) total);
			
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<Integer, Integer> getAnswerKey() {
		Map<Integer, Integer> answer_key = new HashMap<Integer, Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("src/data/answer_sheet")));
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				answer_key.put(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer_key;
	}

}
