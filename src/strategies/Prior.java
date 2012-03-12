package strategies;

public class Prior {
	
	private int numCorrect;
	private int numWrong;
	private int total;
	
	public Prior() {
		numCorrect = 1;
		numWrong = 1;
		total = 1;
	}
	
	public void update(boolean correct) {
		if (correct) numCorrect++;
		else numWrong++;
		total++;
	}
	
	public double value() {
//		return numCorrect/(double)numWrong;
		return numCorrect/(double)total;
	}
	
}
