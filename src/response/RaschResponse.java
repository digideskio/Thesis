package response;

import question.Question;
import user.User;

public class RaschResponse extends Response {

	public RaschResponse(int id, Question question) {
		super(id, question);
	}

	@Override
	public double getConfidence() {
		if (users.size() < 10) return users.size();
		double confidence = 0;
		for (User u : getUsers()) {
			double difference = u.getAbility() - question.getDifficulty();
			double probabilityOfCorrect = Math.exp(difference) / (1 + Math.exp(difference));
			confidence += probabilityOfCorrect;
		}
		return confidence;
	}

	@Override
	public void setConfidence(double confidence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question, boolean correct) {
		users.add(user);
		this.question = question;
	}

	@Override
	public void update(User user, Question question, double correctness) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question) {
		users.add(user);
		this.question = question;
		
	}

}
