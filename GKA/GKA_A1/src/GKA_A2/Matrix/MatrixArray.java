package GKA_A2.Matrix;


/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixArray extends AbstractMatrix {

	private double[][] mArray;

	/**
	 * Creates a Matrix from a new two dimensional array.
	 * 
	 * @param m
	 *            m dimension of a Matrix
	 * @param n
	 *            n dimension of a Matrix
	 */
	public MatrixArray(int m, int n) {
		super(m, n);
		mArray = new double[m][n]; // Arrays werden automatische mit 0.0
									// gefuellt
	}
	
	public MatrixArray(int m, int n, Double defaultValue) {
		super(m, n);
		mArray = new double[m][n];

		for (int i = 1; i <= this.getM(); i++) {
			for (int j = 1; j <= this.getN(); j++) {
				this.insert(i, j, defaultValue);
			}
		}
	}

	/**
	 * Inserts a double value into the current array in row i at position j.
	 * 
	 * @param i
	 *            current column.
	 * @param j
	 *            current position.
	 * @param value
	 *            double to be inserted.
	 */
	@Override
	public void insert(int i, int j, double value) {
		outOfBound(i - 1, j - 1);
		accessCount += 1; // access auf das Element
		mArray[i - 1][j - 1] = value;
	}

	/**
	 * Gets the value out of the current array by row i at position j.
	 * 
	 * @param i
	 *            current column.
	 * @param j
	 *            current position.
	 * @return double value to be at position i and j
	 */
	@Override
	public double get(int i, int j) {
		outOfBound(i - 1, j - 1);
		accessCount += 1; // access auf das Element
		return mArray[i - 1][j - 1];
	}

	@Override
	public Matrix mul(Matrix factor) {
		return super.mul_(factor, new MatrixArray(this.getM(), factor.getN()));
	}

	@Override
	public Matrix add(Matrix m) {
		return super.add_(m, new MatrixArray(this.getM(), this.getN()));
	}

	@Override
	public Matrix mul(double skalar) {
		return super.mul_(skalar, new MatrixArray(this.getM(), this.getN()));
	}

	@Override
	public Matrix pow(int exponent) {
		return super.pow_(exponent, new MatrixArray(this.getM(), this.getN()));
	}

	@Override
	public Matrix powFast(int exponent) {
		Matrix newMatrix = new MatrixArray(this.getM(), this.getN());
		newMatrix.copyFrom(this);
		return super.powFast_(exponent, newMatrix);
	}
}
