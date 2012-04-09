package user;

import question.Question;
import response.Response;

public abstract class User {
	
	public int numRight;
	public int numWrong;
	
	protected int id;
	
	public int totalResponses;
	
	public User(int id) {
		this.id = id;
		totalResponses = 0;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public abstract double getAbility();
	
	public abstract void update(Question question, Response response, boolean correct);
	public abstract void update(Question question, Response response, double correctness);
	
	public abstract void update(Question question, Response response);
	
	public abstract boolean getTestPrediction(Question question);
	
	public abstract boolean getPrediction(Question question);
	
	public abstract void updateModel();
	
	public abstract boolean updateTestingParameters();
	
	public abstract void storeParameters();
	
	public abstract String currentParameters();

}
