package response;

import question.Question;
import user.User;

public class MaxResponse extends Response {
	
	
	public MaxResponse(int id, Question question) {
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
		users.add(user);
	}

	@Override
	public void update(User user, Question question) {
		users.add(user);
//		question.addResponse(this);
		
	}

}
