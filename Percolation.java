import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  boolean grid[][];
  int N = 10;
  WeightedQuickUnionUF qu = new WeightedQuickUnionUF(N*N);
  
  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    grid = new boolean[N][N];
    for(int i=1; i <= N; i++)
      for(int j=1; j <= N; j++)
        grid[i][j] = false;
  }
  
  // open site (row i, column j) if it is not open already  
  public void open(int i, int j) {
    invalidIndices(i, j);
    grid[i][j] = true;
  }
  
  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    return (grid[i][j] == true);
  }
  
  // is site (row i, column j) full?  
  public boolean isFull(int i, int j) {
    return (grid[i][j] == false);
  }
  
  // does the system percolate?  
  public boolean percolates() {
    return true;
  }
  
  //dimension converter
  public int dimensionConverter(int i, int j) {
    int row = i*N-N;
    int position = row + j;
    return position;
  }
  //throw an exception for invalid indices
  public void invalidIndices(int i, int j) {
    if (i <= 0 || i > N) throw new IndexOutOfBoundsException("row index i out of bounds");
    if (j <= 0 || j > N) throw new IndexOutOfBoundsException("column index j out of bounds");
  }
  
  // test client (optional)
  public static void main(String[] args) {
    
  }
}