package simulator;

import java.util.ArrayList;

import models.IndependenceModel;
import models.Model;
import models.PairwiseDependenceModel;
import models.PopularModel;
import models.RaschModel;
import models.SVMModel;
import response.MaxResponse;
import response.Response;

public class OnlineAPI {
	
	ArrayList<Model> models;
	
	public OnlineAPI(String[] args) {
		models = new ArrayList<Model>();
		for (String modelName : args) {
			if (modelName == "popular") {
				models.add(new PopularModel());
			} else if (modelName == "independent") {
				models.add(new IndependenceModel());
			} else if (modelName == "pairwise") {
				models.add(new PairwiseDependenceModel());
			} else if (modelName == "svm") {
				models.add(new SVMModel());
			} else if (modelName == "rasch") {
				models.add(new RaschModel());
			}
		}
	}
	
	public void update(int userID, int questionID, int responseID) {
		for (Model model : models) {
			model.update(userID, questionID, responseID);
		}
	}
	
	public void update(int userID, int questionID, int responseID, boolean correct) {
		for (Model model : models) {
			model.update(userID, questionID, responseID, correct);
		}
	}
	
	public int getAnswer(int questionID) {
		Response best = new MaxResponse(-1, null);
		best.setConfidence(Double.NEGATIVE_INFINITY);
		for (Model model : models) {
			Response temp = model.getBestResponse(questionID);
			if (temp != null) {
//				System.out.println(temp.getId());
//				System.out.println(temp.getConfidence());
			}
			if (temp!= null && temp.getConfidence() > best.getConfidence()) {
				best = temp;
			}
		}
//		System.out.println(best.getId());
		return best.getId();
	}

}
