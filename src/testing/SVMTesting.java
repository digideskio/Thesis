package testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import question.Question;
import response.Response;
import simulator.Simulation;
import user.User;

public class SVMTesting extends Simulation {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/data/training_clean2_head")));
		new File("src/data/svm/training_svm").delete();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/data/svm/training_svm")));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			update(line, true);
		}
		
		for (Question q : questions.values()) {
			Response correct = q.correctResponse;
			Set<User> allUsers = q.getUsers();
			for (Response r : q.responses) {
				String s = "";
				Set<User> currentUsers = r.getUsers();
				if (r == correct) s += 1;
				else s += -1;
				for (User u : allUsers) {
					s += " " + u.getId() + ":";
					if (currentUsers.contains(u)) s += 1;
					else s+= -1;
				}
//				int i = 1;
//				for (User u : users.values()) {
//					s += " " + u.getId() + ":";
//					i++;
//					if (currentUsers.contains(u)) s += 1;
//					else if (allUsers.contains(u)) s += -1;
//					else s += 0;
//				}
				writer.write(s);
				writer.newLine();
			}
		}
		writer.close();
	}

}
