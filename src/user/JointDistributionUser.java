package user;

import java.util.HashMap;
import java.util.Map;

import question.Question;
import response.Response;
import utilities.IncrementingHashMap;

public class JointDistributionUser extends User {
	
	static Map<Integer, IncrementingHashMap<Integer>> correctMap;
	static Map<Integer, IncrementingHashMap<Integer>> incorrectMap;

	public JointDistributionUser(int id) {
		super(id);
		correctMap = new HashMap<Integer, IncrementingHashMap<Integer>>();
		incorrectMap = new HashMap<Integer, IncrementingHashMap<Integer>>();
	}

	@Override
	public double getAbility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		if (correct) {
			if (!correctMap.containsKey(getId())) {
				
			}
		}
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
