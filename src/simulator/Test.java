package simulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import question.Question;
import question.SimpleQuestion;
import response.MaxResponse;
import response.Response;
import user.PriorUser;
import user.User;

public class Test {
	
	static String CURRENT_TRAINING_FILE = "src/data/training_clean2";
	static String NEW_TRAINING_FILE = "src/data/training_clean3";

	public static void main(String[] args) throws IOException {
//		test();
//		removeShortRows();
		prepareAnswerSheet();
//		removeSkippedAndTimeout();
//		printOverallStats();
	}

	public static void printOverallStats() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(CURRENT_TRAINING_FILE)));
		String line = reader.readLine();
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
		HashMap<Integer, Response> responses = new HashMap<Integer, Response>();
		HashMap<Integer, Question> response_id_to_question = new HashMap<Integer, Question>();

		int counter = 0;

		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			if (arr.length != 17) System.out.println(Arrays.toString(arr));
			boolean correct = arr[0].equals("1");
			int user_id = Integer.parseInt(arr[2]);
			int question_id = Integer.parseInt(arr[3]);
			int response_id = Integer.parseInt(arr[12]);
			if (response_id != 25553) {

				if (!users.containsKey(user_id)) {
					users.put(user_id, new PriorUser(user_id));
				}

				if (!questions.containsKey(question_id)) {
					questions.put(question_id, new SimpleQuestion(question_id));
				}

				if (!responses.containsKey(response_id)) {
					responses.put(response_id, new MaxResponse(response_id, questions.get(question_id)));
				}

				User user = users.get(user_id);
				Question question = questions.get(question_id);
				Response response = responses.get(response_id);

				if (response_id_to_question.containsKey(response_id)) {
					if (response_id_to_question.get(response_id).getId() != question_id) {
//						 System.out.println(response_id_to_question.get(response_id).getId() + ", " + question_id);
						// System.out.println(response_id);
						// System.out.println("multiple questions with the same response");
						// counter++;
					}
				} else {
					response_id_to_question.put(response_id, question);
				}

				user.update(question, response, correct);
				response.update(user, question, correct);
				question.update(user, response, correct);
			}
		}

		System.out.println("Number of users: " + users.size());
		System.out.println("Number of questions: " + questions.size());
		System.out.println("Number of responses: " + responses.size());
		System.out.println(counter);

	}
	
	public static Set<Integer> prepareAnswerSheet() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(CURRENT_TRAINING_FILE)));
		String line = reader.readLine();
		
		HashMap<Integer, Integer> answer_sheet = new HashMap<Integer, Integer>();
		Set<Integer> multipleAnswers = new HashSet<Integer>();

		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			boolean correct = arr[0].equals("1");
			int question_id = Integer.parseInt(arr[3]);
			int response_id = Integer.parseInt(arr[12]);
			if (response_id != 25553) {
				if (correct) {
					answer_sheet.put(question_id, response_id);
				}
			}
		}
		new File("src/data/answer_sheet").delete();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/data/answer_sheet")));
		int count = 0;
		writer.write("question_id response_id");
		for (Entry<Integer, Integer> entry : answer_sheet.entrySet()) {
			writer.newLine();
			writer.write(entry.getKey() + " " + entry.getValue());
		}
		writer.close();
		System.out.println(multipleAnswers.size());
		return multipleAnswers;

	}
	
	public static void removeQuestionsWithMultipleAnswers() throws IOException {
		
		Set<Integer> multipleAnswers = prepareAnswerSheet();
		
		int c = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(CURRENT_TRAINING_FILE)));
		new File(NEW_TRAINING_FILE).delete();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(NEW_TRAINING_FILE)));
		writer.write(reader.readLine());
		String line;
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			int question_id = Integer.parseInt(arr[3]);
			int response_id = Integer.parseInt(arr[12]);
			if (response_id != 25553) System.out.println("25553 crept in");
			if (!multipleAnswers.contains(question_id)) {
				writer.newLine();
				writer.write(line);
				c++;
			}
		}
		
		System.out.println("wrote " + c + " lines");
		writer.close();
	}
	
	public static void removeShortRows() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(CURRENT_TRAINING_FILE)));
		new File(NEW_TRAINING_FILE).delete();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(NEW_TRAINING_FILE)));
		writer.write(reader.readLine());
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			if (line.split(",").length == 17) {
				writer.newLine();
				writer.write(line);
				count++;
			}
		}
		writer.close();
		System.out.println("Wrote " + count + " lines");
	}
	
	public static void test() throws IOException {
		boolean multipleAnswers = false;
		boolean tooShort = false;
		boolean multQSameResponse = false;
		
		HashMap<Integer, Integer> responseToQuestion = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> questionToCorrectResponse = new HashMap<Integer, Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(CURRENT_TRAINING_FILE)));
		reader.readLine();
		
		String line;
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			if (arr.length != 17) {
				tooShort = true;
			} else {
				boolean correct = arr[0].equals("1");
				int question_id = Integer.parseInt(arr[3]);
				int response_id = Integer.parseInt(arr[12]);
				if (responseToQuestion.containsKey(response_id)) {
					if (responseToQuestion.get(response_id) != question_id) multQSameResponse = true;
				} else {
					responseToQuestion.put(response_id, question_id);
				}
				if (correct) {
					if (questionToCorrectResponse.containsKey(question_id)) {
						if (questionToCorrectResponse.get(question_id) != response_id) multipleAnswers = true;
					} else {
						questionToCorrectResponse.put(question_id, response_id);
					}
				}
			}
		}
		
		if (multipleAnswers) System.out.println("There exist questions ith multiple correct answers");
		if (multQSameResponse) System.out.println("There exist multiple questions with the same response");
		if (tooShort) System.out.println("There exist rows that are too short to parse");
		
	}
	
	public static void removeSkippedAndTimeout() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(CURRENT_TRAINING_FILE)));
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(NEW_TRAINING_FILE)));
		String line = reader.readLine();
		writer.write(line);
		int count = 0;
		while((line = reader.readLine()) != null) {
			String outcome = line.split(",")[1];
			if (outcome.equals("1") || outcome.equals("2")) {
				count++;
				writer.newLine();
				writer.write(line);
			}
		}
		System.out.println(count);
		writer.close();
	}

}
