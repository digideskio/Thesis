package response;

import java.util.ArrayList;

import jnisvmlight.FeatureVector;
import jnisvmlight.LabeledFeatureVector;
import jnisvmlight.SVMLightInterface;
import jnisvmlight.SVMLightModel;
import jnisvmlight.TrainingParameters;
import question.Question;
import user.User;

public class SVMResponse extends Response {
	
	static SVMLightModel svmmodel;
	static SVMLightInterface interf = new SVMLightInterface();
	
	static boolean train = true;
	

	public SVMResponse(int id, Question question) {
		super(id, question);
		this.question = question; 
	}
	
	public static void train() {
//		int N = responses.size();
//		LabeledFeatureVector[] lfvecs = new LabeledFeatureVector[N];
		int j = 0;
		ArrayList<LabeledFeatureVector> lfvecsArray = new ArrayList<LabeledFeatureVector>();
		for (Response r : responses) {
			if (r.question.correctResponse != null) {
				int n = r.question.users.size();
				int[] dims = new int[n];
				double[] vals = new double[n];
				int i = 0;
				for (User u : r.question.users) {
					dims[i] = u.getId();
					vals[i] = r.users.contains(u) ? 1 : -1;
					i++;
				}
				if (dims.length > 0) {
					lfvecsArray.add(j, new LabeledFeatureVector(r == r.question.correctResponse ? +1 : -1, dims, vals));
					lfvecsArray.get(j).normalizeL2();
					j++;
				}
			}
		}
	    TrainingParameters tp = new TrainingParameters();
	    tp.getLearningParameters().verbosity = 1;
	    
	    LabeledFeatureVector[] lfvecs = new LabeledFeatureVector[lfvecsArray.size()];
	    lfvecs = lfvecsArray.toArray(lfvecs);
	    System.out.print("training...");
		svmmodel = interf.trainModel(lfvecs, tp);
		System.out.println("done");
		train = false;
	}

	@Override
	public double getConfidence() {
//		System.out.println(getId());
		if (train) train();
		int n = question.getUsers().size();
		int[] dims = new int[n];
		double[] vals = new double[n];
		int i = 0;
		for (User u : question.getUsers()) {
			dims[i] = u.getId();
			vals[i] = users.contains(u) ? 1 : -1;
			i++;
		}
		FeatureVector fv = new FeatureVector(dims, vals);
		double result = svmmodel.classify(fv);
//		System.out.println(result);
		return result;
	}

	@Override
	public void setConfidence(double confidence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Question question, boolean correct) {
		users.add(user);
		
	}

	@Override
	public void update(User user, Question question) {
		users.add(user);
		
	}

	@Override
	public void update(User user, Question question, double correctness) {
		users.add(user);
		
	}

}
