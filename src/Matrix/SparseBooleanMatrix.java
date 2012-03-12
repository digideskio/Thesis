package Matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ejml.simple.SimpleMatrix;

public class SparseBooleanMatrix {
	
	int max_y;
	
	Map<Integer, Set<Integer>> filledCells;
	
	public SparseBooleanMatrix() {		
		// Number of columns on the final matrix
		max_y = 0;
		// Indices that need to be true when computing the final matrix
		filledCells = new HashMap<Integer, Set<Integer>>();
	}
	
	public void set(Integer x, Integer y) {
		if (filledCells.containsKey(x)) {
			Set<Integer> row = filledCells.get(x);
			row.add(y);
		} else {
			Set<Integer> newRow = new HashSet<Integer>();
			newRow.add(y);
			filledCells.put(x, newRow);
		}
		if (y > max_y) max_y = y;
	}
	
	public boolean get(Integer x, Integer y) {
		if (filledCells.containsKey(x)) {
			if (filledCells.get(x).contains(y)) {
				return true;
			}
		}
		return false;
	}
	
	public SimpleMatrix getMatrix() {
		double[][] array = new double[filledCells.size()][max_y + 1]; 
		int x = 0;
		for (Entry<Integer, Set<Integer>> entry : filledCells.entrySet()) {
			Set<Integer> columns = entry.getValue();
			for (int y : columns) {
				array[x][y] = 1.0;
			}
			x++;
		}
		return new SimpleMatrix(array);
	}

}
