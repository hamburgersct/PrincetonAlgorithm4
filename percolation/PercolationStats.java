import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private double[] frac;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        int num = n * n;
        frac = new double[trials];
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!grid.isOpen(row, col)) {
                    grid.open(row, col);
                }
            }
            frac[i] = grid.numberOfOpenSites() / (double) num;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(frac);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (trials == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(frac);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        if (Double.isNaN(stddev())) return Double.NaN;
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (Double.isNaN(stddev())) return Double.NaN;
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);

       PercolationStats stats = new PercolationStats(n, trials);
       System.out.println("mean                    = " + stats.mean());
       System.out.println("stddev                  = " + stats.stddev());
       System.out.println("confidence interval     = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}
