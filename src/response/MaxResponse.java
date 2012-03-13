package response;

import question.Question;
import user.User;

public class MaxResponse extends Response {
	
	
	public MaxResponse(int id, Question question) {
		super(id, question);
	}

	@Override
	public double getConfidence() {
		return confidence;
	}

	@Override
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	@Override
	public void update(User user, Question question, boolean correct) {
		users.add(user);
	}

	@Override
	public void update(User user, Question question) {
		users.add(user);
		
	}

	@Override
	public void update(User user, Question question, double correctness) {
		// TODO Auto-generated method stub
		
	}

}
