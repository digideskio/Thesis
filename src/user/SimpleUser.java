package user;

import question.Question;
import response.Response;

public class SimpleUser extends User {

	public SimpleUser(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getAbility() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		// TODO Auto-generated method stub
		
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

}
