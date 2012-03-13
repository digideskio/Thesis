package question;

import java.util.HashSet;
import java.util.Set;

import response.Response;
import user.User;

public abstract class Question {
	
	protected int id;
	
	public Set<Response> responses;
	public Set<User> users;
	public Response correctResponse;
	
	public Question(int _id) {
		this.id = _id;
		responses = new HashSet<Response>();
		users = new HashSet<User>();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public Response getCorrectResponse() {
		return correctResponse;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public abstract double getDifficulty();
	
	public abstract void update(User user, Response response, boolean correct);
	public abstract void update(User user, Response response, double correctness);
	public abstract void update(User user, Response response);
	
	public abstract Response getTopResponse();
	
}
