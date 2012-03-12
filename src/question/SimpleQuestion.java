package question;

import response.MaxResponse;
import response.Response;
import user.User;

public class SimpleQuestion extends Question {
	
	public SimpleQuestion(int id) {
		super(id);
	}

	@Override
	public double getDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(User user, Response response, boolean correct) {
		responses.add(response);
		if (correct) correctResponse = response;
		users.add(user);
	}

	@Override
	public void update(User user, Response response) {
		responses.add(response);
		users.add(user);
//		responses.increment(response.getId(), user.getAbility());
//		responses.increment(response.getId());
	}
	
	@Override
	public Response getTopResponse() {
		Response bestResponse = new MaxResponse(-1, this);
		for (Response r : responses) {
			if (bestResponse.getConfidence() < r.getConfidence())
				bestResponse = r;
		}
		return bestResponse;
	}
}
