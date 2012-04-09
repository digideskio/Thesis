package response;

import question.Question;
import svmlearn.Problem;
import user.User;

public class IndependentResponse extends Response {
	
	double logProbCorrect;
	double logProbIncorrect;
	
	static final double SMALL_NUMBER = Math.pow(10, -6);

	public IndependentResponse(int id, Question question) {
		super(id, question);
	}

	@Override
	public double getConfidence() {
		double val = logProbCorrect;
		for (Response r : question.responses) {
			IndependentResponse response = (IndependentResponse) r;
			if (r != this) {
				val += response.logProbIncorrect;
			}
		}
//		System.out.println(val / (double) question.getUsers().size());
		return val / (double) question.getUsers().size();
	}

	@Override
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	@Override
	public void update(User user, Question question, boolean correct) {
		double ability = correct ? 1 : 0;
		update(user, question, ability);
	}

	@Override
	public void update(User user, Question question, double correctness) {
		users.add(user);
		this.question = question;
		if (user.totalResponses > 10) {
			double ability = correctness;
			logProbCorrect += Math.log(ability);
			logProbIncorrect += ability == 1 ? SMALL_NUMBER : Math.log(1 - ability);
		}
	}

	@Override
	public void update(User user, Question question) {
		double ability = user.getAbility();
		update(user, question, ability);
	}

}
