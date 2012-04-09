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
	static String[] arguments = { "rasch" };

	public static void main(String[] args) throws IOException {
		OnlineAPIMockClient client = new OnlineAPIMockClient();
		client.bootstrap(new File("src/data/bootstrap"));
		client.simulate(new File("src/data/training_clean2"));
	}

	public OnlineAPIMockClient() {
		api = new OnlineAPI(arguments);
		ANSWER_KEY = getAnswerKey();
	}

	public void bootstrap(File input) throws IOException {
		System.out.println("Bootstrapping...");
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");			
			boolean correct = arr[0].equals("1");
			int user_id = Integer.parseInt(arr[2]);
			int question_id = Integer.parseInt(arr[3]);
			try {
				int response_id = Integer.parseInt(arr[12]);
				api.update(user_id, question_id, response_id, correct);
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
	}

	public void simulate(File input) {
		System.out.println("Simulating...");
		int total = 0;
		int numCorrect = 0;
		int i = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split(",");
				boolean correct = arr[0].equals("1");
				int user_id = Integer.parseInt(arr[2]);
				int question_id = Integer.parseInt(arr[3]);
				int response_id = Integer.parseInt(arr[12]);
				int answer = api.getAnswer(question_id);
//				if (ANSWER_KEY.containsKey(question_id)) {
				if (question_id > 100 && ANSWER_KEY.containsKey(question_id)) {
//					System.out.println(answer + ":" + ANSWER_KEY.get(question_id));
					total++;
					if (answer == ANSWER_KEY.get(question_id)) {
						numCorrect++;
					}
//					 api.update(user_id, question_id, response_id, correct);
					api.update(user_id, question_id, response_id);
					i++;
					if (i % 100000 == 0)
						System.out.println(numCorrect / (double) total);
				}
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
	}

	public Map<Integer, Integer> getAnswerKey() {
		Map<Integer, Integer> answer_key = new HashMap<Integer, Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					"src/data/answer_sheet")));
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				answer_key.put(Integer.parseInt(line.split(" ")[0]),
						Integer.parseInt(line.split(" ")[1]));
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
