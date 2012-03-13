package user;

import question.Question;
import response.Response;

public class SimpleUser extends User {
	
	int correct;
	int total;
//	
//	static int totalCorrect = 0;
//	static int totalAnswered = 0;

	public SimpleUser(int id) {
		super(id);
		correct = 1;
		total = 1;
	}

	@Override
	public double getAbility() {
		return correct / (double) total;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
//		System.out.println(response + ":" + question.correctResponse);
		if (correct) this.correct++;
		total++;
	}

	@Override
	public void update(Question question, Response response) {
//		totalAnswered++;
//		if (response.equals(question.correctResponse)) totalCorrect++;
//		System.out.println(totalCorrect / (double) totalAnswered);
//		System.out.println(response + ", " + question.correctResponse + ":" + response.equals(question.correctResponse));
//		update(question, response, response.equals(question.correctResponse));
		
	}

	@Override
	public boolean getTestPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateTestingParameters() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void storeParameters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String currentParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Question question, Response response, double correctness) {
		// TODO Auto-generated method stub
		
	}

}
