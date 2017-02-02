package hw2;                       
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private Percolation[] percolation;
    private double[] xs;
    private double mean;
    private double stddev;
    private int T;
    private int N;

    /**perform T independent experiments on an N-by-N grid*/
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be positive");
        }
        this.T = T;
        this.N = N;
        this.percolation = new Percolation[T];
        for (int i = 0; i < T; i++) {
            percolation[i] = new Percolation(N);
        }
        this.xs = new double[T];
        getxs();
        this.mean = StdStats.mean(xs);
        this.stddev = StdStats.stddev(xs);
    }

    private void getxs() {
        for (int i = 0; i < N; i++) {
            while (!percolation[i].percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                percolation[i].open(row, col);
            }
            xs[i] = percolation[i].numberOfOpenSites() / percolation[i].getCount();
        }
    }

    /**sample mean of percolation threshold*/
    public double mean() {
        return mean;
    }
    /**sample standard deviation of percolation threshold*/
    public double stddev() {
        return stddev;
    }

    /**low  endpoint of 95% confidence interval*/
    public double confidenceLow() {
        double cL = mean - (1.96 * stddev) / Math.sqrt(T);
        return cL;
    }

    /**high endpoint of 95% confidence interval*/
    public double confidenceHigh() {
        double cH = mean + (1.96 * stddev) / Math.sqrt(T);
        return cH;
    }
}                       
