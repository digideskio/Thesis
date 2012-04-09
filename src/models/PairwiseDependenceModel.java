package models;

import question.PairwiseDependentQuestion;
import question.Question;
import response.IndependentResponse;
import response.Response;
import user.SimpleUser;
import user.User;

public class PairwiseDependenceModel extends Model {

	@Override
	public User initializeUser(int userID) {
		return new SimpleUser(userID);
	}

	@Override
	public Question initializeQuestion(int questionID) {
		return new PairwiseDependentQuestion(questionID);
	}

	@Override
	public Response initializeResponse(int responseID, int questionID) {
		return new IndependentResponse(responseID, questions.get(questionID));
	}

}
