package models;

import question.Question;
import question.RaschQuestion;
import response.RaschResponse;
import response.Response;
import user.RaschUser;
import user.User;

public class RaschModel extends Model {

	@Override
	public User initializeUser(int userID) {
		return new RaschUser(userID);
	}

	@Override
	public Question initializeQuestion(int questionID) {
		return new RaschQuestion(questionID);
	}

	@Override
	public Response initializeResponse(int responseID, int questionID) {
		return new RaschResponse(responseID, questions.get(questionID));
	}

}
