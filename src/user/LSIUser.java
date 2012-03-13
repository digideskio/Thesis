package user;

import question.Question;
import response.Response;
import strategies.LSI;

public class LSIUser extends User {
	
	public static LSI lsi = new LSI(100);
	double threshold;
	
	public LSIUser(int id) {
		super(id);
	}
	
	public LSIUser(int id, double threshold) {
		this(id);
		this.threshold = threshold;
	}

	@Override
	public double getAbility() {
		return 0.0;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		lsi.update(getId(), question.getId(), correct);
	}
	
	@Override
	public void update(Question question, Response response) {
		
	}

	@Override
	public boolean getPrediction(Question question) {
		double val = lsi.get(getId(), question.getId());
//		System.out.println(val);
		return val > threshold;
	}

	@Override
	public void updateModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getTestPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
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
