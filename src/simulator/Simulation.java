package simulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import question.Question;
import question.SimpleQuestion;
import response.PopularResponse;
import response.Response;
import user.SimpleUser;
import user.User;

public class Simulation {

	static File[] sets;
	static int NUM_SETS = 6;

	public static HashMap<Integer, User> users = new HashMap<Integer, User>();
	public static HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
	public static HashMap<Integer, Response> responses = new HashMap<Integer, Response>();
	
	static double bestResult = 0.0;
	
	static User masterUser;
	
	static String TRAINING_FILE = "src/data/training_clean2";
	
	public static void main(String[] args) throws IOException, ParseException {
		splitData(NUM_SETS);
		System.out.println("training...");
		masterUser = initializeUser(-1);
//		crossTrain();
//		while (masterUser.updateTestingParameters()) crossTrain();
		train();
		test();
	}
	
	private static void train() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(TRAINING_FILE));
			String line;
			while ((line = reader.readLine()) != null) {
				update(line, true);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + TRAINING_FILE);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void crossTrain() throws IOException {
		System.out.println("    with " + masterUser.currentParameters());
		double result = 0;
		for (int j = 0; j < NUM_SETS; j++) {
			System.out.print("        on set " + j);
			leaveOneAndTrain(j);
			double inermediatetResult = testOn(sets[j]); 
			System.out.println(" with result " + inermediatetResult);
			result += inermediatetResult;
		}
		result /= NUM_SETS;
		System.out.println();
		System.out.println("Average result with " + masterUser.currentParameters() + " was " + result);
		if (result > bestResult) {
			bestResult = result;
			masterUser.storeParameters();
		}
		System.out.println("---------------------");
	}

	public static void leaveOneAndTrain(int j) {
		users = new HashMap<Integer, User>();
		questions = new HashMap<Integer, Question>();
		for (int i = 0; i < NUM_SETS; i++) {
			if (i != j) trainOn(i);
		}
	}

	public static void trainOn(int i) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(sets[i]));
			String line;
			while ((line = reader.readLine()) != null) {
				update(line, true);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + sets[i]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void update(String line, boolean train) {
		String[] arr = line.split(",");
		if (arr.length < 10) return;
		boolean correct = arr[0].equals("1");
		int user_id = Integer.parseInt(arr[2]);
		int question_id = Integer.parseInt(arr[3]);
		int response_id = Integer.parseInt(arr[12]);

		if (!users.containsKey(user_id)) {
			users.put(user_id, initializeUser(user_id));
		}

		if (!questions.containsKey(question_id)) {
			questions.put(question_id, initializeQuestion(question_id));
		}
		
		if (!responses.containsKey(response_id)) {
			responses.put(response_id, initializeResponse(response_id, questions.get(question_id)));
		} 

		User user = users.get(user_id);
		Question question = questions.get(question_id);
		Response response = responses.get(response_id);
		
		if (train) {
			user.update(question, response, correct);
			response.update(user, question, correct);
			question.update(user, response, correct);
		} else {
			user.update(question, response);
			response.update(user, question);
			question.update(user, response);
		}
	}

	public static double testOn(File file) throws IOException {
		
		int numCorrect = 1;
		int numAttempts = 1;
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] arr = line.split(",");
			if (arr.length > 10) {
				boolean correct = arr[0].equals("1");
				int user_id = Integer.parseInt(arr[2]);
				int question_id = Integer.parseInt(arr[3]);
				if (users.containsKey(user_id)
						&& questions.containsKey(question_id)) {
					User user = initializeUser(user_id);
					Question question = initializeQuestion(question_id);
					if (correct == user.getTestPrediction(question)) {
						numCorrect++;
					}
					numAttempts++;
				}
			}
		}
//		System.out.println(numCorrect + "/" + numAttempts);
		return numCorrect / (double) numAttempts;
	}

	public static void test() throws IOException {
		System.out.println("testing...");

		int numPredictions = 0;
		int numPredictedCorrectly = 0;

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"src/data/test")));
		String line;
		while ((line = reader.readLine()) != null) {
			line = reader.readLine();
			String[] arr = line.split(",");
			boolean correct = arr[0].equals("1");
			int user_id = Integer.parseInt(arr[2]);
			int question_id = Integer.parseInt(arr[3]);

			if (users.containsKey(user_id)
					&& questions.containsKey(question_id)) {
				User user = users.get(user_id);
				boolean prediction = user.getPrediction(questions.get(question_id));
				if (prediction == correct)
					numPredictedCorrectly++;
				numPredictions++;
			}
		}
		System.out.println(numPredictedCorrectly + "/" + numPredictions);
		System.out.println(numPredictedCorrectly / (double) numPredictions);
	}

	public static void splitData(int numSets) throws IOException {
		
		sets = new File[numSets];
		for (int i = 0; i < numSets; i++) sets[i] = new File("src/data/splits/set" + i);
		
		if (new File("src/data/splits").listFiles().length == numSets) return;
		
		System.out.print("Splitting training data into " + numSets + " sets... ");
		
		clearDirectory("src/data/splits");
		
		BufferedWriter[] writer = new BufferedWriter[numSets];
		for (int i = 0; i < numSets; i++) {
			writer[i] = new BufferedWriter((new FileWriter(sets[i])));
		}

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"src/data/train")));
		reader.readLine();
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			writer[i%numSets].write(line);
			writer[i%numSets].newLine();
			i++;
		}
		System.out.println("done");
	}

	private static void clearDirectory(String pathname) {
		File directory = new File(pathname);
		for (File file : directory.listFiles()) {
			file.delete();
		}
	}
	
	public static User initializeUser(int id) {
//		return new PriorUser(id);
		return new SimpleUser(id);
	}
	
	public static Question initializeQuestion(int id) {
//		return new RandomQuestion(id);
//		return new IndependentClassifierQuestion(id);
		return new SimpleQuestion(id);
	}
	
	public static Response initializeResponse(int id, Question question) {
		return new PopularResponse(id, question);
	}
	
	public static int getUserID(String line) {
		return Integer.parseInt(line.split(",")[2]);
	}
	
	public static int getQuestionID(String line) {
		return Integer.parseInt(line.split(",")[3]);
	}	
	
	public static int getResponseID(String line) {
		return Integer.parseInt(line.split(",")[12]);
	}
}
//