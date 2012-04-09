package user;

import java.util.HashSet;
import java.util.Set;

import question.Question;
import response.Response;

public class RaschUser extends User {

	double ability = 0;
	int total = 0;

	static int i = 0;

	Set<Question> question = new HashSet<Question>();

	public RaschUser(int id) {
		super(id);
		ability = 0;
	}

	@Override
	public double getAbility() {
		// if (ability == 0) return numRight / (double) (numRight + numWrong);
		// else return ability;
		return ability;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		
		if (correct) numRight++;
		else numWrong++;
		total++;
	}

	@Override
	public void update(Question question, Response response, double correctness) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Question question, Response response) {
		i++;
		if (i == 100) {
			System.out.println("here");
		}
		if (question.getTopResponse() == null)
			return;
		total++;
		double difference = getAbility() - question.getDifficulty();
		// System.out.println(getAbility() + ":" + question.getDifficulty());
		double probabilityOfCorrect = Math.exp(difference)
				/ (1 + Math.exp(difference));

		int estimatedCorrectness = question.getTopResponse() == response ? 1
				: 0;

		double numerator = estimatedCorrectness - probabilityOfCorrect;
		double denominator = probabilityOfCorrect * (1 - probabilityOfCorrect);
		if (total == 1)
			ability = numRight / (double) (numRight + numWrong);
		ability = ability + (numerator / denominator);
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

}
