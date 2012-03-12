package question;

import java.util.HashMap;
import java.util.Map;

import response.MaxResponse;
import response.Response;
import user.User;

public class PairwiseDependentQuestion extends Question {
	
	static Map<Integer, Map<Integer, Integer>> pairwiseCorrect = new HashMap<Integer, Map<Integer,Integer>>();
	static Map<Integer, Map<Integer, Integer>> pairwiseWrong = new HashMap<Integer, Map<Integer,Integer>>();

	public PairwiseDependentQuestion(int _id) {
		super(_id);
	}

	@Override
	public double getDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(User user, Response response, boolean correct) {
		for (User u : response.getUsers()) {
			if (u != user) updateMap(user.getId(), u.getId(), correct);
		}
	}
	
	public void updateMap(int userID1, int userID2, boolean correct) {
		int first = userID1 < userID2 ? userID1 : userID2;
		int second = userID1 < userID2 ? userID2 : userID1;
		if (correct) {
			if (!pairwiseCorrect.containsKey(first)) {
				pairwiseCorrect.put(first, new HashMap<Integer, Integer>());
			}
			Map<Integer, Integer> entry = pairwiseCorrect.get(first);
			if (!entry.containsKey(second)) {
				entry.put(second, 1);
			}
			entry.put(second, entry.get(second) + 1);
			pairwiseCorrect.put(first, entry);
		} else {
			if (!pairwiseWrong.containsKey(first)) {
				pairwiseWrong.put(first, new HashMap<Integer, Integer>());
			}
			Map<Integer, Integer> entry = pairwiseWrong.get(first);
			if (!entry.containsKey(second)) {
				entry.put(second, 1);
			}
			entry.put(second, entry.get(second) + 1);
			pairwiseWrong.put(first, entry);
		}
	}

	@Override
	public void update(User user, Response response) {
		// TODO Auto-generated method stub
		
	}
	
	public double getScore(int userID1, int userID2) {
		int first = userID1 < userID2 ? userID1 : userID2;
		int second = userID1 < userID2 ? userID2 : userID1;
		if (pairwiseCorrect.containsKey(first) && pairwiseCorrect.get(first).containsKey(second) && pairwiseWrong.containsKey(first) && pairwiseWrong.get(first).containsKey(second)) {
			int correct = pairwiseCorrect.get(first).get(second);
			int wrong = pairwiseWrong.get(first).get(second);
			return correct / (double) (correct + wrong); 
		}
		return 1;
	}

	@Override
	public Response getTopResponse() {
		Response best = new MaxResponse(-1, this);
		double bestScore = Double.NEGATIVE_INFINITY;
		for (Response response : responses) {
			double score = 0;
			for (User u1 : response.getUsers()) {
				for (User u2 : response.getUsers()) {
					if (u1 != u2) {
						score += Math.log(getScore(u1.getId(), u2.getId()));
					}
				}
			}
			if (score > bestScore) {
				best = response;
				bestScore = score;
			}
		}
		return best;
	}

}
