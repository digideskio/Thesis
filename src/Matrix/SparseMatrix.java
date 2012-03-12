package Matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ejml.simple.SimpleMatrix;

public class SparseMatrix {

	int max_y;
	
	Map<Integer, Map<Integer, Integer>> filledCells;
	SimpleMatrix matrix;
	
	public SparseMatrix() {		
		// Numer of columns on the final matrix
		max_y = 0;
		// Indices that need to be true when computing the final matrix
		filledCells = new HashMap<Integer, Map<Integer, Integer>>();
	}
	
	public void set(Integer x, Integer y, Integer value) {
		if (filledCells.containsKey(x)) {
			Map<Integer, Integer> row = filledCells.get(x);
			row.put(y, value);
		} else {
			HashMap<Integer, Integer> newRow = new HashMap<Integer, Integer>();
			newRow.put(y, value);
			filledCells.put(x, newRow);
		}
		if (y > max_y) max_y = y;
		matrix = null;
	}
	
	public Integer get(Integer x, Integer y) {
		if (filledCells.containsKey(x)) {
			Map<Integer, Integer> row = filledCells.get(x);
			if (row.containsKey(y)) {
				return row.get(y);
			}
		}
		return null;
	}
	
	public SimpleMatrix getMatrix() {
		if (matrix != null) return matrix;
		double[][] array = new double[filledCells.size()][max_y + 1];
		for (Entry<Integer, Map<Integer, Integer>> row : filledCells.entrySet()) {
			int x = row.getKey();
			Map<Integer, Integer> r = row.getValue();
			for (Entry<Integer, Integer> col : r.entrySet()) {
				System.out.println(x + ", " + col.getKey());
				array[x][col.getKey()] = col.getValue();
			}
		}
		matrix = new SimpleMatrix(array);
		return matrix;
	}
	
	public static void main(String[] args) {
		SparseMatrix m = new SparseMatrix();
		m.set(0, 0, 4);
		m.set(1, 3, -1);
//		System.out.println(m.max_y);
		System.out.println(m.getMatrix());
	}
	
}
