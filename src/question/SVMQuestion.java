package question;

import response.Response;
import user.User;

public class SVMQuestion extends Question {

	public SVMQuestion(int _id) {
		super(_id);
		correctResponse = null;
	}

	@Override
	public double getDifficulty() {
		return 0;
	}

	@Override
	public void update(User user, Response response, boolean correct) {
		users.add(user);
		responses.add(response);
		if (correct) correctResponse = response;
	}

	@Override
	public void update(User user, Response response) {
		users.add(user);
		responses.add(response);
	}

	@Override
	public Response getTopResponse() {
		Response best = null;
		double best_val = Double.NEGATIVE_INFINITY;
		for (Response r : responses) {
			if (r.getConfidence() > best_val) {
				best = r;
				best_val = r.getConfidence();
			}
		}
//		System.out.println(best_val);
		return best;
	}
	

	@Override
	public void update(User user, Response response, double correctness) {
		// TODO Auto-generated method stub
		
	}

}
