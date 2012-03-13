package response;

import question.Question;
import user.User;

public class PopularResponse extends Response {

	public PopularResponse(int id, Question question) {
		super(id, question);
	}

	@Override
	public double getConfidence() {
		return users.size();
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
		users.add(user);
	}

}
