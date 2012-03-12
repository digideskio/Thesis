package models;

import question.IndependentClassifierQuestion;
import question.Question;
import response.PopularResponse;
import response.Response;
import user.PriorUser;
import user.User;

public class IndependenceModel extends Model {

	@Override
	public User initializeUser(int userID) {
		return new PriorUser(userID);
	}

	@Override
	public Question initializeQuestion(int questionID) {
		return new IndependentClassifierQuestion(questionID);
	}

	@Override
	public Response initializeResponse(int responseID, int questionID) {
		return new PopularResponse(responseID, questions.get(questionID));
	}
	

}
