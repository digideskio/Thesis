package strategies;

import Matrix.SparseBooleanMatrix;
import Matrix.SparseMatrix;

public class KNearest {
	
	int k;
	SparseMatrix matrix;
	
	public KNearest(int k) {
		this.k = k;
		matrix = new SparseMatrix();
	}
	
	public void update(int x, int y, boolean yes) {
		int val = yes ? 1 : -1;
		matrix.set(x, y, val);
	}
	
	
	
	
	
}
