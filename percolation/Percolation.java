/* *****************************************************************************
 *  Name:              hamburgersct
 *  Coursera User ID:  123456
 *  Last modified:     5/30/2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private final int topIndex;
    private final int btmIndex;
    private int openCount;
    private boolean[] status; /* all elements in status[] are set to false */
    private WeightedQuickUnionUF backwashGrid;
    private WeightedQuickUnionUF safeGrid;
    /** use 1-dim array (n * n size)
     to represent the grid -- (row - 1) * N + col */

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        /* two virtual node -- top and bottom */
        size = n;
        topIndex = n * n;
        btmIndex = n * n + 1;
        openCount = 0;
        /* this UF has no virtual bottom node */
        safeGrid = new WeightedQuickUnionUF(n * n + 1);
        backwashGrid = new WeightedQuickUnionUF(n * n + 2);
        status = new boolean[n * n + 2];
        status[topIndex] = true;
        status[btmIndex] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int curSite = getIndex(row, col);
        // check if this site has been clicked before
        if (status[curSite])
            return;
        status[curSite] = true;
        openCount += 1;

        if (row == 1) {
            backwashGrid.union(curSite, topIndex);
            safeGrid.union(curSite, topIndex);
        }
        if (row == size) {
            backwashGrid.union(curSite, btmIndex);
        }
        tryUnion(row, col, row, col - 1);
        tryUnion(row, col, row, col + 1);
        tryUnion(row, col, row - 1, col);
        tryUnion(row, col, row + 1, col);

    }

    private void tryUnion(int rowA, int colA, int rowB, int colB) {
        if (rowB > 0 && rowB <= size && colB > 0 && colB <= size && isOpen(rowB, colB)) {
            backwashGrid.union(getIndex(rowA, colA), getIndex(rowB, colB));
            safeGrid.union(getIndex(rowA, colA), getIndex(rowB, colB));
        }
    }

    private int getIndex(int row, int col) {
        if (row > size || row < 1 || col > size || col < 1)
            throw new IllegalArgumentException();
        return (row - 1) * size + col - 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
            return status[getIndex(row, col)];
    }

    // is the site (row, col) full? by comparing its root with the virtual top (the last second element)
    public boolean isFull(int row, int col) {
        return safeGrid.find(getIndex(row, col)) == safeGrid.find(topIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return backwashGrid.find(topIndex) == backwashGrid.find(btmIndex);
    }

    // test client (optional)
    public static void main(String[] args) { }
}
