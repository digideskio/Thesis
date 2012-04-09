package question;

import java.util.Iterator;

import response.Response;
import user.User;

public class RaschQuestion extends Question {
	
	int numRight;
	int numWrong;
	double difficulty;

	public RaschQuestion(int _id) {
		super(_id);
		numRight = 1;
		numWrong = 1;
		difficulty = 0;
	}

	@Override
	public double getDifficulty() {
		return difficulty;
//		if (users.size() < 10) return numRight / (double) (numRight + numWrong);
//		else return difficulty;
	}

	@Override
	public void update(User user, Response response, boolean correct) {
		responses.add(response);
		if (correct) numRight++;
		else numWrong++;
	}

	@Override
	public void update(User user, Response response, double correctness) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Response response) {
		responses.add(response);
		if (getTopResponse() == null) return;
		double difference = user.getAbility() - getDifficulty();
		double probabilityOfCorrect = Math.exp(difference) / (1 + Math.exp(difference));
		if (user.getAbility() > 4) {
			System.out.println(user.getAbility());
		}
		int estimatedCorrectness = getTopResponse() == response ? 1 : 0;
		double numerator = estimatedCorrectness - probabilityOfCorrect;
		double denominator = probabilityOfCorrect*(1 - probabilityOfCorrect);
//		System.out.println(difficulty + "+" + estimatedCorrectness + "-" + probabilityOfCorrect + "/" + probabilityOfCorrect + "*" + (1 - probabilityOfCorrect));
		if (users.size() == 1) difficulty = numRight / (double) (numRight + numWrong);
		difficulty = difficulty - (numerator / denominator);
	}

	@Override
	public Response getTopResponse() {
//		if (responses.size() < 5) return null;
		Iterator<Response> iter = responses.iterator();
		Response best = responses.isEmpty() ? null : iter.next();
		double bestValue = Double.NEGATIVE_INFINITY;
		for (Response r : responses) {
			if (r.getConfidence() > bestValue) {
				best = r;
				bestValue = r.getConfidence();
			}
		}
//		System.out.println(best);
		return best;
	}

}
