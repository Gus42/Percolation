import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean grid[][];
  private int N;
  private int sites;
  private WeightedQuickUnionUF ali;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    System.out.println("test 1, I'll made a grid with dim:"+N);
    this.N = N;
    sites = N * N + 2;
    ali = new WeightedQuickUnionUF(sites);
    grid = new boolean[N][N];
    for(int i=0; i < N; i++)
      for(int j=0; j < N; j++)
        grid[i][j] = false;
    System.out.println("test 2");
  }

  // open site (row i, column j) if it is not open already  
  public void open(int i, int j) {
    System.out.println("test 3");
    invalidIndices(i, j);
    System.out.println("test 4");
    //i--;
    //j--;
    if(isOpen(i, j)) return;
    i--; j--;
    grid[i][j] = true;
    i++; j++;
    int index = dimensionConverter(i, j);
    System.out.println("test 5");
    if(i != 1) {
      if(isOpen(i-1, j)) {
        ali.union(index,dimensionConverter(i-1, j));
      }
    }else{
      ali.union(index, N*N);
    }
    System.out.println("test 6");
    if(i != N) {
      if(isOpen(i+1, j)) {
        ali.union(index,dimensionConverter(i+1, j));
      }
    }else{
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
    System.out.println(i+" "+j+" is open?");
    invalidIndices(i, j);
    i--;
    j--;
    System.out.println(" return from test 11 in test isOpen. this is the value of grid i: "+i+" and j "+j+" the grid: "+grid[i][j]);
    if(grid[i][j] == true){ System.out.println(" yes "); return true;}
    System.out.println(" no "); return false;
  }

  // is site (row i, column j) full?  
  public boolean isFull(int i, int j) {
    System.out.println("test 8");
    //i--;
    //j--;
    return ali.connected(dimensionConverter(i, j), N*N);
  }

  // does the system percolate?  
  public boolean percolates() {
    System.out.println("test 9");
    return ali.connected(N*N+1, N*N);
  }

  // dimension converter
  public int dimensionConverter(int i, int j) {
    System.out.println("test 10: dimension converter with "+i+" and "+j);
    i--;
    j--;
    return (i * N + j); 
  }
  // throw an exception for invalid indices
  public void invalidIndices(int i, int j) {
    System.out.println("test 11");
    if (i <= 0 || i > N) throw new IndexOutOfBoundsException("row index i out of bounds");
    if (j <= 0 || j > N) throw new IndexOutOfBoundsException("column index j out of bounds");
    System.out.println("completed test 11");
  }
}