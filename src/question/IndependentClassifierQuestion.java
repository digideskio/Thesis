package question;

import java.util.HashMap;
import java.util.Map;

import response.MaxResponse;
import response.Response;
import user.User;

public class IndependentClassifierQuestion extends Question {

	double SMALL_NUMBER = -Math.pow(10, 6);

	public IndependentClassifierQuestion(int _id) {
		super(_id);
	}

	@Override
	public double getDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(User user, Response response, boolean correct) {
		responses.add(response);
		users.add(user);
	}

	@Override
	public void update(User user, Response response) {
		responses.add(response);
		users.add(user);
	}

	@Override
	public Response getTopResponse() {
		Response best = new MaxResponse(-1, this);
		best.setConfidence(Double.NEGATIVE_INFINITY);
		for (Response r : responses) {
			if (best.getConfidence() < r.getConfidence()) {
				best = r;
			}
		}
		correctResponse = best;
		return best;		
	}

	@Override
	public void update(User user, Response response, double correctness) {
		responses.add(response);
		users.add(user);
	}

}
