package simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import question.Question;
import response.Response;
import user.User;

public class PredictResponse extends Simulation {
	
	static int TRAINING_QUESTION_THRESHOLD = 2000;
	static Map<Integer, Integer> ANSWER_KEY;
	
	public static void getAnswerKey() {
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
		ANSWER_KEY = answer_key;
	}
	
	public static void train(File input) {
		System.out.println("Training...");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				if (getQuestionID(line) > 1000) update(line, true);
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
	
	public static double test(File input) throws IOException {
		System.out.println("Testing...");
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			if (getQuestionID(line) <= 1000) update(line, false);
		}
		System.out.println("Finished updating, checking answers...");
		int count = 0;
		int correct = 0;
		for (Entry<Integer, Question> entry : questions.entrySet()) {
			int question_id = entry.getKey();
			if (question_id <= 1000 && ANSWER_KEY.get(question_id) != null) {
				Question question = entry.getValue();
				System.out.println(question.getId() + ":" + ANSWER_KEY.get(question_id));
				Response prediction = question.getTopResponse();
				System.out.println();
//				if (prediction != null) {
					count++;
					int prediction_id = prediction.getId();
					int actual = ANSWER_KEY.get(question_id);
					if (prediction_id == actual) correct++;
//				}
			}
		}
		
		return correct / (double) count;
	}
	
	public static void main(String[] args) throws IOException {
		getAnswerKey();
		train(new File(TRAINING_FILE));
		double result = test(new File(TRAINING_FILE));
		System.out.println(result);
	}

}
