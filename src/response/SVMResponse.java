package response;

import question.Question;
import user.User;

public class SVMResponse extends Response {
	
	

	public SVMResponse(int id, Question question) {
		super(id, question);
	}

	@Override
	public double getConfidence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setConfidence(double confidence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question, boolean correct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question, double correctness) {
		// TODO Auto-generated method stub
		
	}

}
