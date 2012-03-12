package models;

import java.util.HashMap;
import java.util.Map;

import question.Question;
import response.Response;
import user.User;

public abstract class Model {
	
	Map<Integer, User> users = new HashMap<Integer, User>();
	Map<Integer, Question> questions = new HashMap<Integer, Question>();
	Map<Integer, Response> responses = new HashMap<Integer, Response>();
	
	public abstract User initializeUser(int userID);
	public abstract Question initializeQuestion(int questionID);
	public abstract Response initializeResponse(int responseID, int questionID);
	
	public void update(int userID, int questionID, int responseID) {
		prepMaps(userID, questionID, responseID);
		User user = users.get(userID);
		Question question = questions.get(questionID);
		Response response = responses.get(responseID);
		user.update(question, response);
		question.update(user, response);
		response.update(user, question);
	}
	
	public void update(int userID, int questionID, int responseID, boolean correct) {
		prepMaps(userID, questionID, responseID);
		User user = users.get(userID);
		Question question = questions.get(questionID);
		Response response = responses.get(responseID);
		user.update(question, response, correct);
		question.update(user, response, correct);
		response.update(user, question, correct);
	}
	
	public void prepMaps(int userID, int questionID, int responseID) {
		if (!users.containsKey(userID)) users.put(userID, initializeUser(userID));
		if (!questions.containsKey(questionID)) questions.put(questionID, initializeQuestion(questionID));
		if (!responses.containsKey(responseID)) responses.put(responseID, initializeResponse(responseID, questionID));
	}
	
	public Response getBestResponse(int questionID) {
//		System.out.println(questionID);
		if (questions.containsKey(questionID)) {
			return questions.get(questionID).getTopResponse();
		} else {
//			System.out.println("Couldn't find anything");
			return null;
		}
	}
	
	

}
