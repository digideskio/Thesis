package user;

import question.Question;
import response.Response;
import strategies.KNearest;
import Matrix.SparseBooleanMatrix;

public class KNearestUser extends User {
	
	int K = 10;
	static KNearest knearest = new KNearest(10);
	
	public KNearestUser(int id) {
		super(id);	
	}

	@Override
	public double getAbility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
//		int val = correct ? 1 : -1;
		knearest.update(getId(), question.getId(), correct);
	}

	@Override
	public void update(Question question, Response response) {
		
		
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
