package user;

import question.Question;
import response.Response;

public class BayesianAverageUser extends User {
	
	static int global_num_votes = 0;
	static int global_sum_votes = 0;
	static int global_num_users = 0;
	
	int this_num_votes = 0;

	public BayesianAverageUser(int id) {
		super(id);
		global_num_users++;
	}

	@Override
	public double getAbility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		int val = correct ? 1 : -1;
		global_num_votes++;
		global_sum_votes += val;
	}

	@Override
	public void update(Question question, Response response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getTestPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateTestingParameters() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void storeParameters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String currentParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Question question, Response response, double correctness) {
		// TODO Auto-generated method stub
		
	}

}
