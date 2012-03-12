package question;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import libsvm.svm_model;
import libsvm.svm_train;

import response.Response;
import user.User;

public class SVMQuestion extends Question {
	
	static final String TRAINING_FILE = "src/data/svm/training";
	static final String MODEL_FILE = "src/data/svm/model";
	static final String[] arguments = {TRAINING_FILE, MODEL_FILE};
	
	svm_model model = new svm_model();
	
	static Set<Question> questions = new HashSet<Question>();

	public SVMQuestion(int _id) {
		super(_id);
		questions.add(this);
	}

	@Override
	public double getDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(User user, Response response, boolean correct) {
		responses.add(response);
		if (correct) correctResponse = response;
	}

	@Override
	public void update(User user, Response response) {
		responses.add(response);
	}

	@Override
	public Response getTopResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void trainModel() throws IOException {
		svm_train train = new svm_train();
		train.run(arguments);
	}

}
