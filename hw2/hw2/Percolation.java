package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private WeightedQuickUnionUF uf;
    private boolean[] openGrid;
    private int openSites;
    private int count;

    /**create N-by-N grid, with all sites initially blocked*/
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be positive");
        }
        this.N = N;
        this.count = N*N;
        uf = new WeightedQuickUnionUF(N*N + 2);
        openGrid = new boolean[N*N];
        for (int i = 0; i < openGrid.length; i++) {
            openGrid[i] = false;
        }
        openSites = 0;
    }

    public int getCount() {
        return count;
    }

    private void checkIndex(int row, int col) {
        if ((row < 0 || row >= N) || (col < 0 || col >= N)) {
            throw new IndexOutOfBoundsException("row and col should be between 0 and N-1");
        }
    }

    private int xyTo1D(int r, int col) {
        return r * N + col;
    }

    private void tryUnionAround(int row, int col) {
        checkIndex(row, col);
        int index = xyTo1D(row, col);
        if ((row-1 >= 0 && row-1 < N) && (col >= 0 && col < N) && isOpen(row-1, col)) {
            uf.union(xyTo1D(row-1, col), index);
        }
        if ((row+1 >= 0 && row+1 < N) && (col >= 0 && col < N) && isOpen(row+1, col)) {
            uf.union(xyTo1D(row+1, col), index);
        }
        if ((row >= 0 && row < N) && (col-1 >= 0 && col-1 < N) && isOpen(row, col-1)) {
            uf.union(xyTo1D(row, col-1), index);
        }
        if ((row >= 0 && row < N) && (col+1 >= 0 && col+1 < N) && isOpen(row, col+1)) {
            uf.union(xyTo1D(row, col+1), index);
        }
        if (row == 0) {
            uf.union(index, N*N);
        }
        if (row == N-1) {
            uf.union(index, N*N+1);
        }
    }

    /** open the site (row, col) if it is not open already*/
    public void open(int row, int col) {
        checkIndex(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int index = xyTo1D(row, col);
        openGrid[index] = true;
        openSites++;
        tryUnionAround(row, col);
    }

    /**is the site (row, col) open?*/
    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        int index = xyTo1D(row, col);
        return openGrid[index];
    }

    /**is the site (row, col) full?*/
    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        int index = xyTo1D(row, col);
        return uf.connected(index, N*N);
    }

    /**number of open sites*/
    public int numberOfOpenSites() {
        return openSites;
    }

    /**does the system percolate?*/
    public boolean percolates() {
        return uf.connected(N*N+1, N*N);
    }

    /**unit testing (not required)*/
    public static void main(String[] args) {
        System.out.println(-4 % 3);
        System.out.println(Math.abs(Integer.MAX_VALUE));
    }
}                       
