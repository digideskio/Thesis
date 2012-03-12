package response;

import question.Question;
import user.User;
import utilities.IncrementingHashMap;

public class BayesianAverageResponse extends Response {
	
	static IncrementingHashMap<Question> global_num_votes = new IncrementingHashMap<Question>();;
	static IncrementingHashMap<Question> global_sum_votes = new IncrementingHashMap<Question>();
	static IncrementingHashMap<Question> global_num_responses = new IncrementingHashMap<Question>();
	
	double this_sum_votes = 0;
	int this_num_votes = 0;

	public BayesianAverageResponse(int id, Question question) {
		super(id, question);
		global_num_responses.increment(question);
	}

	@Override
	public double getConfidence() {
		System.out.println((global_sum_votes.get(question) + this_sum_votes) / ((global_num_votes.get(question)/(double)global_num_responses.get(question)) + this_num_votes));
		System.out.println(this_sum_votes/(double)this_num_votes);
		System.out.println(this_num_votes);
		System.out.println();
		return (global_sum_votes.get(question) + this_sum_votes) / ((global_num_votes.get(question)/(double)global_num_responses.get(question)) + this_num_votes);
	}

	@Override
	public void setConfidence(double confidence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question, boolean correct) {
		
	}

	@Override
	public void update(User user, Question question) {		
		double val = user.getAbility();
		
		global_sum_votes.increment(question, val);
		this_sum_votes += val;
		
		global_num_votes.increment(question);
		this_num_votes++;
	}
	
}
