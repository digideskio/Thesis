package strategies;

import java.util.HashMap;
import java.util.Map;

import org.ejml.alg.dense.mult.VectorVectorMult;
import org.ejml.data.MatrixIterator;
import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.SimpleSVD;

import Matrix.SparseBooleanMatrix;

public class LSI {
	
	SparseBooleanMatrix matrix;
	SimpleMatrix decomposedMatrix;
	double[][] cosineSimilarities;
	boolean decomposed;
	int k;
	
	Map<Integer, Integer> rowIDs;
	Map<Integer, Integer> colIDs;
	
	
	public LSI(int k) {
		matrix = new SparseBooleanMatrix();
		this.k = k;
		decomposed = false;
		rowIDs = new HashMap<Integer, Integer>();
		colIDs = new HashMap<Integer, Integer>();
	}
	
	public void update(Integer x, Integer y, boolean value) {
		if (value) {
			if (!rowIDs.containsKey(x)) {
				rowIDs.put(x, rowIDs.size());
			}
			if (!colIDs.containsKey(y)) {
				colIDs.put(y, colIDs.size());
			}
			matrix.set(rowIDs.get(x), colIDs.get(y));
			decomposed = false;
		}
	}
	
	public void decompose() {
		SimpleSVD svd = matrix.getMatrix().svd();
		decomposedMatrix = reduceRank(svd);
		int rows = decomposedMatrix.numRows();
		int cols = decomposedMatrix.numCols();
		if (cosineSimilarities == null || cosineSimilarities.length != rows) {
			cosineSimilarities = new double[rows][rows];
		}
		for (int i = 0; i < decomposedMatrix.numRows(); i++) {
			SimpleMatrix vec1 = decomposedMatrix.extractVector(true, i);
			double norm1 = vec1.normF();
			for (int j = 0; j < i; j++) {
				SimpleMatrix vec2 = decomposedMatrix.extractVector(true, j);
				double norm2 = vec2.normF();
				double cosineSimilarity =  VectorVectorMult.innerProd(vec1.getMatrix(), vec2.getMatrix()) / (norm1 * norm2);
				cosineSimilarities[i][j] = cosineSimilarity;
				cosineSimilarities[j][i] = cosineSimilarity;
			}
		}
		decomposed  = true;
	}
	
	public double get(int x, int y) {
		if (rowIDs.containsKey(x) && colIDs.containsKey(y)) {
			if (!decomposed) decompose();
			int row = rowIDs.get(x);
			int col = colIDs.get(y);
			MatrixIterator iter = decomposedMatrix.iterator(true, 0, col, decomposedMatrix.numRows() - 1, col);
			double val;
			double sum = 0;
			int i = 0;
			while (iter.hasNext()) {
				val = iter.next();
//				System.out.println(val);
				sum += Math.log(val)*Math.log(cosineSimilarities[row][i]);
				i++;
			}
			System.out.println(sum);
			return sum;
		}
//		System.out.println("couldn't find anything");
		return 0.0;
	}
	
	public SimpleMatrix reduceRank(SimpleSVD svd) {
		SimpleMatrix u = svd.getU();
		SimpleMatrix s = svd.getW();
		SimpleMatrix v = svd.getV();
		int rank = svd.rank();
		for (int i = k; i < rank; i++) {
			s.set(i, i, 0);
		}
		return u.mult(s).mult(v.transpose());
	}
	
}
