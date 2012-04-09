package models;

import question.Question;
import question.SVMQuestion;
import response.Response;
import response.SVMResponse;
import user.SimpleUser;
import user.User;

public class SVMModel extends Model {

	@Override
	public User initializeUser(int userID) {
		return new SimpleUser(userID);
	}

	@Override
	public Question initializeQuestion(int questionID) {
		return new SVMQuestion(questionID);
	}

	@Override
	public Response initializeResponse(int responseID, int questionID) {
		return new SVMResponse(responseID, questions.get(questionID));
	}

}
