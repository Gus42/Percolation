import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean grid[];
  private int N;
  private WeightedQuickUnionUF ali;

  // create a fake N-by-N grid, with only one dimension, with all sites blocked(false)
  public Percolation(int N) {
    this.N = N;
    int sites = N * N + 2;
    ali = new WeightedQuickUnionUF(sites);
    grid = new boolean[sites];
    for(int i=0; i < sites; i++)
        grid[i] = false;
  }

  // open site (row i, column j) if it is not open already  
  public void open(int i, int j) {
    invalidIndices(i, j);
    if(isOpen(i, j)) return;
    int index = dimensionConverter(i, j);
    grid[index] = true;
    if(i != 1) {
      if(isOpen(i-1, j)) {
        ali.union(index,dimensionConverter(i-1, j));
      }
    } else {
      ali.union(index, N*N);
    }
    if(i != N) {
      if(isOpen(i+1, j)) {
        ali.union(index,dimensionConverter(i+1, j));
      }
    } else {
      ali.union(index, N*N+1);
    }
    if(j != 1) {
      if(isOpen(i, j-1)) {
        ali.union(index,dimensionConverter(i, j-1));
      }
    }
    if(j != N) {
      if(isOpen(i, j+1)) {
        ali.union(index,dimensionConverter(i, j+1));
      }
    }
  }

  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    invalidIndices(i, j);
    return (grid[dimensionConverter(i, j)] == true);
  }

  // is site (row i, column j) full?  
  public boolean isFull(int i, int j) {
    invalidIndices(i, j);
    return ali.connected(dimensionConverter(i, j), N*N);
  }

  // does the system percolate?  
  public boolean percolates() {
    return ali.connected(N*N+1, N*N);
  }

  // dimension converter
  public int dimensionConverter(int i, int j) {
    i--;
    j--;
    return (i * N + j);
  }
  
  // throw an exception for invalid indices
  public void invalidIndices(int i, int j) {
    if (i <= 0 || i > N) throw new IndexOutOfBoundsException("row index i out of bounds");
    if (j <= 0 || j > N) throw new IndexOutOfBoundsException("column index j out of bounds");
  }
}