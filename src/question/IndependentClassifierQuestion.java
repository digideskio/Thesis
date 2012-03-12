package question;

import java.util.HashMap;
import java.util.Map;

import response.MaxResponse;
import response.Response;
import user.User;

public class IndependentClassifierQuestion extends Question {

	double SMALL_NUMBER = Math.pow(10, -6);

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
		// TODO Auto-generated method stub
	}

	@Override
	public void update(User user, Response response) {
		responses.add(response);
	}

	@Override
	public Response getTopResponse() {
		Map<Response, Double> negativeResponse = new HashMap<Response, Double>();
		Map<Response, Double> positiveResponse = new HashMap<Response, Double>();
		int numUsers = 0;

		for (Response response : responses) {
			double pos = 0;
			double neg = 0;
			for (User user : response.getUsers()) {
				numUsers++;
				pos += Math.log(user.getAbility());
				neg += user.getAbility() == 1 ? SMALL_NUMBER : Math.log(1 - user.getAbility());
				if (response.getId() == 10008) {
					// System.out.println(user.numRight/(double)totalRight);
					// System.out.println(user.numWrong/(double)totalWrong);
				}
			}
			negativeResponse.put(response, neg);
			positiveResponse.put(response, pos);
		}

//		System.out.println(numUsers);

		// if (getId() == 2006) {
		// System.out.println(positiveResponse);
		// System.out.println(negativeResponse);
		// }

		Response best = new MaxResponse(-1, this);
		double best_val = Double.NEGATIVE_INFINITY;
		double second_val = Double.NEGATIVE_INFINITY;
		for (Response current : responses) {
			double val = positiveResponse.get(current);
			for (Response other : responses) {
				if (other != current) {
					val += negativeResponse.get(other);
				}
			}
			val = val / numUsers;
			if (val > best_val) {
				second_val = best_val;
				best = current;
				best_val = val;
			} else if (val > second_val){
				second_val = val;
			}
		}
//		if (best_val - second_val < 0.1) return null;
		System.out.println(getId() + ":" + best);
		return best;
		
	}

}
