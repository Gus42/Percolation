import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  boolean grid[][];
  int N;
  WeightedQuickUnionUF ali;
  
  // create N-by-N grid, with all sites blocked
  public Percolation(int n) {
    N = n * n + 2;
    ali = new WeightedQuickUnionUF(N*N+2);
    System.out.println("test1");
    grid = new boolean[N+1][N+1];
    for(int i=1; i <= N; i++)
      for(int j=1; j <= N; j++)
        grid[i][j] = false;
    System.out.println("test2");
  }
  
  // open site (row i, column j) if it is not open already  
  public void open(int i, int j) {
    i--;
    j--;
    System.out.println("test");
    invalidIndices(i, j);
    if(isOpen(i, j)) return;
    grid[i][j] = true;
    int index = dimensionConverter(i, j);
    //if(i == 1) {
    if(i != 1) {
      if(isOpen(i-1, j)) {
        ali.union(index,dimensionConverter(i-1, j));
      }
    }
    if(i != N) {
      if(isOpen(i+1, j)) {
        ali.union(index,dimensionConverter(i+1, j));
      }
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
    return (grid[i--][j--] == true);
  }
  
  // is site (row i, column j) full?  
  public boolean isFull(int i, int j) {
    return p.ali.connected(p.dimensionConverter(i--, j--), p.dimensionConverter(N, N));
  }
  
  // does the system percolate?  
  public boolean percolates() {
    return true;
  }
  
  // dimension converter
  public int dimensionConverter(int i, int j) {
    return i * N - N + j;
  }
  // throw an exception for invalid indices
  public void invalidIndices(int i, int j) {
    if (i <= 0 || i > N) throw new IndexOutOfBoundsException("row index i out of bounds");
    if (j <= 0 || j > N) throw new IndexOutOfBoundsException("column index j out of bounds");
  }
  
  // test client (optional)
  public static void main(String[] args) {
    /* 
     * call open(1, 1) and open(1, 2), and then to ensure that 
     * the two corresponding entries are connected (using .connected() in WeightedQuickUnionUF). 
     */
    System.out.println("test");
    Percolation p = new Percolation(10);
    p.open(1, 1);
    p.open(1, 2);
    if(p.ali.connected(p.dimensionConverter(1, 1), p.dimensionConverter(1, 2))){
      System.out.println("funzia");
    }else
      System.out.println("non funzia");
    
    if(p.ali.connected(p.dimensionConverter(1, 1), p.dimensionConverter(3, 2))){
      System.out.println(" non funzia");
    }else
      System.out.println(" funzia");
  }
}