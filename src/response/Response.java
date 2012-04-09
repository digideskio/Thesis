package response;

import java.util.HashSet;
import java.util.Set;

import question.Question;
import user.User;

public abstract class Response {
	
	protected double confidence;
	protected int id;
	protected Question question;
	
	protected Set<User> users; 
	
	protected static Set<Response> responses = new HashSet<Response>();
	
	public Response(int id, Question question) {
		this.setId(id);
		this.question = question;
		users = new HashSet<User>();
		if (question != null) responses.add(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public double averageAbility() {
		double sum = 0;
		for (User u : users) {
			sum += u.getAbility();
		}
		return sum / users.size();
	}
	
	public String toString() {
		return "" + getId();
	}
	
	public Question getQuestion() {
		return question;
	}

	public abstract double getConfidence();	
	public abstract void setConfidence(double confidence);
	public abstract void update(User user, Question question, boolean correct);
	public abstract void update(User user, Question question, double correctness);
	public abstract void update(User user, Question question);

}
