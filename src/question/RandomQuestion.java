package question;

import java.util.Random;

import response.Response;
import user.User;

public class RandomQuestion extends Question {

	public RandomQuestion(int _id) {
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
		if (correct) correctResponse = response;
	}

	@Override
	public void update(User user, Response response) {
		responses.add(response);
	}

	@Override
	public Response getTopResponse() {
		int index = new Random().nextInt(responses.size());
		int i = 0;
		for (Response r : responses) {
			if (i == index) return r;
			i++;
		}
		return null;
	}

}
