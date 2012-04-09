package user;

import question.Question;
import response.Response;

public class WeightedPriorUser extends User {
	
	double correct;
	double total;

	public WeightedPriorUser(int id) {
		super(id);
		correct = 1;
		total = 1;
	}

	@Override
	public double getAbility() {
		return correct / total;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		totalResponses++;
		if (correct) this.correct++;
		total++;
	}

	@Override
	public void update(Question question, Response response, double correctness) {
		totalResponses++;
		correct += correctness;
		total++;
	}

	@Override
	public void update(Question question, Response response) {
		totalResponses++;
//		if (response.getQuestion().getUsers().size() > 10) {
//			update(question, response, response.getQuestion().getTopResponse() == response);
//			System.out.println(Math.pow(Math.E, response.getConfidence()));
			if (Math.pow(Math.E, response.getConfidence()) > 0.6) {
				update(question, response, response.getQuestion().getTopResponse() == response);
			}
//		}
	}

	@Override
	public boolean getTestPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPrediction(Question question) {
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
