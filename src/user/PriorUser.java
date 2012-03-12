package user;

import question.Question;
import response.Response;
import strategies.Prior;

public class PriorUser extends User {

	Prior prior;
	static double threshold = 0.8;
	static double[] thresholds = {0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3};
	static int currentThreshold = 0;
	
	static double bestThreshold = 1.0;
	
	

	public PriorUser(int id) {
		super(id);
		prior = new Prior();
		numRight = 1;
		numWrong = 1;
	}

	@Override
	public double getAbility() {
		return prior.value();
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		prior.update(correct);
		if (correct) numRight++;
		else numWrong++;
	}
	
	@Override
	public void update(Question question, Response response) {
		
	}

	@Override
	public boolean getTestPrediction(Question question) {
		return prior.value() > threshold;
	}
	
	@Override
	public boolean getPrediction(Question question) {
		return prior.value() > bestThreshold;
//		double frac = right / (double) total;
//		return Math.random() < 0.5;
//		return prior.value() > 0.5;
//		return true;
	}

	@Override
	public void updateModel() {

	}

	@Override
	public boolean updateTestingParameters() {
		if (currentThreshold < thresholds.length - 1) {
			currentThreshold++;
			threshold = thresholds[currentThreshold];
			return true;
		}
		return false;
	}

	@Override
	public void storeParameters() {
		bestThreshold = thresholds[currentThreshold];
		System.out.println("Set best threshold to " + bestThreshold);
	}

	@Override
	public String currentParameters() {
		return "threshold " + threshold;
	}

}
