package question;

import java.util.HashMap;
import java.util.Map;

import response.MaxResponse;
import response.Response;
import user.User;

public class NaiveBayesQuestion extends Question {
	
	double SMALL_NUMBER = Math.pow(10, -6);

	public NaiveBayesQuestion(int _id) {
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
		int totalRight = 0;
		int totalWrong = 0;
		// To find N(l = true) and N(l = false)
		for (Response response : responses) {
			for (User user : response.getUsers()) {
				totalRight += user.numRight;
				totalWrong += user.numWrong;
				numUsers++;
			}
			if (response.getId() == 10008) {
				System.out.println(totalRight);
				System.out.println(totalWrong);
			}
		}
		for (Response response : responses) {
			double pos = 0;
			double neg = 0;
			for (User user : response.getUsers()) {
				pos += Math.log(user.numRight/(double)totalRight);
				neg += Math.log(user.numWrong/(double)totalWrong);
				if (response.getId() == 10008) {
//					System.out.println(user.numRight/(double)totalRight);
//					System.out.println(user.numWrong/(double)totalWrong);
				}
			}
			negativeResponse.put(response, neg);
			positiveResponse.put(response, pos);
		}
		
		if (getId() == 2006) {
			System.out.println(positiveResponse);
			System.out.println(negativeResponse);
		}
		
		Response best = new MaxResponse(-1, this);
//		double max = Double.NEGATIVE_INFINITY;
		double max = Double.POSITIVE_INFINITY;
		for (Response current : responses) {
			double val = positiveResponse.get(current);
			for (Response other : responses) {
				if (other != current) {
					val += negativeResponse.get(other);
				}
			}
			val += totalRight/(totalRight+totalWrong);
			val = val/numUsers;
			if (val < max) {
				best = current;
				max = val;
			}
		}
		return best;
	}

}
