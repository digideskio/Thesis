package user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import libsvm.svm_model;
import libsvm.svm_train;
import question.Question;
import response.Response;

public class SVMUser extends User {

	static File raw = new File("data/svmraw");
	static File output = new File("data/output");
	static BufferedWriter writer;
	static svm_model model;
	
	static boolean computed = false;
	
	static {
		try {
			writer = new BufferedWriter(new FileWriter(raw));
		} catch (IOException e) {

		}
		raw.delete();
		output.delete();
	}

	public SVMUser(int id) {
		super(id);
	}

	@Override
	public double getAbility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Question question, Response response, boolean correct) {
		String s = "";
		s += correct ? "1 " : "0 ";
		s += "1:" + getId() + " ";
		s += "2:" + question.getDifficulty();
		try {
			writer.write(s);
		} catch (IOException e) {
			System.out.println("couldn't write " + s + " to file");
		}
	}
	
	@Override
	public void update(Question question, Response response) {
		
	}

	@Override
	public boolean getPrediction(Question question) {
		svm_train train = new svm_train();
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
	public boolean getTestPrediction(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String currentParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
