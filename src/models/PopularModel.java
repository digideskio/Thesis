package models;

import question.Question;
import question.SimpleQuestion;
import response.PopularResponse;
import response.Response;
import user.SimpleUser;
import user.User;

public class PopularModel extends Model {

	@Override
	public User initializeUser(int userID) {
		return new SimpleUser(userID);
	}
	@Override
	public Question initializeQuestion(int questionID) {
		return new SimpleQuestion(questionID);
	}
	@Override
	public Response initializeResponse(int responseID, int questionID) {
		return new PopularResponse(responseID, questions.get(questionID));
	}

}
