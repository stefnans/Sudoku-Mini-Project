package com.sudoku.demo.model;

import com.sudoku.demo.exceptions.DuplicateSudokuException;

import java.util.*;

/**
 * Class to represent the sudoku grid. To validate the user input, at any point in the game, any row, column or 3x3
 * square should not have a duplicate digit. For this purpose we use hash sets to verify in constant time if there is
 * duplication.
 *
 * Tradeoff between time and space. If we store the sets we use to validate the player input it will validate
 * the operation immediately but it will consume a lot of memory.
 * If we create them on the fly, we consume less memory space but the operations of creation take more time, and in a
 * rest api, we value time more than space, so we go with the first option.
 */
public class SudokuGrid {
    public final static int GRID_SIZE = 9;
    public final static int SQUARE_SIZE = 3;
    public final static int EMPTY = 0;

    // The grid at the start of the game. We need this to avoid having the user play on predetermined cells.
    private int[][] initialGrid;
    // The actual grid storing the position of the numbers
    private int[][] grid;

    // The sets representing the 3x3 squares of the grid.
    private final List<Set<Integer>> squareSets = new ArrayList<>();

    private final List<Set<Integer>> rowSets = new ArrayList<>();
    private final List<Set<Integer>> columnSets = new ArrayList<>();

    public SudokuGrid(int[][] grid) {
        if (grid.length != GRID_SIZE) {
            throw new IllegalArgumentException("Bad Sudoku table initialization: Should have 9 rows but instead got " +
                    grid.length + " rows");
        }

        for (int[] column: grid) {
            if (column.length != GRID_SIZE) {
                throw new IllegalArgumentException("Bad Sudoku table initialization: Bad size of table rows");
            }
        }

        // Initialize the sets used to check the validity of the inputs
        for (int i = 0; i < GRID_SIZE; i++) {
            rowSets.add(new HashSet<>());
            columnSets.add(new HashSet<>());
            squareSets.add(new HashSet<>());
        }

        // Populate the sets
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int elem = grid[i][j];
                if (elem < EMPTY || elem > 9) {
                    throw new IllegalArgumentException("Bad Sudoku game initialization: sudoku digits must be between 1 and 9");
                } else {
                    if (elem != EMPTY) {
                        // add the elements to their corresponding row and column sets
                        if (!rowSets.get(i).add(elem) || !columnSets.get(j).add(elem)) {
                            throw new IllegalArgumentException("Bad Sudoku game initialization");
                        }
                        // add the element to its corresponding square set
                        if (!squareSets.get(translateTo1DCoordinate(i, j)).add(elem)) {
                            throw new IllegalArgumentException("Bad Sudoku game initialization");
                        }
                    }
                }
            }
        }

        // Build the grid and store the initial grid
        // Shallow copy since we're dealing with primitive types
        this.initialGrid = grid.clone();
        this.grid = grid.clone();
        for (int i = 0; i < GRID_SIZE; i++) {
            this.initialGrid[i] = grid[i].clone();
            this.grid[i] = grid[i].clone();
        }
    }

    /**
     * Return the sudoku grid.
     * @return a copy of the grid.
     */
    public int[][] getGrid() {
        int[][] gridCopy = this.grid.clone();
        for (int i = 0; i < GRID_SIZE; i++) {
            gridCopy[i] = this.grid[i].clone();
        }
        return gridCopy;
    }

    public int getElement(int x, int y) {
        return this.grid[x][y];
    }

    public List<Set<Integer>> getSquareSets() {
        return squareSets;
    }

    public List<Set<Integer>> getColumnSets() {
        return columnSets;
    }

    public List<Set<Integer>> getRowSets() {
        return rowSets;
    }

    /**
     * Set the empty cell of the grid with a new element. Here we do all the necessary
     * checks the verify the move is valid per sudoku rules.
     * @param elem the new element to set in a grid cell
     * @param gridX index of the grid row between 0 and 8
     * @param gridY index of the grid column between 0 and 8
     */
    public void setCell(int elem, int gridX, int gridY) throws DuplicateSudokuException {
        // First check if not one of the cells initially set by the game
        if (initialGrid[gridX][gridY] == EMPTY) {
            // Check if not the replacing the same element,
            // otherwise the set would throw a duplicate exception when it shouldn't.
            if (!(this.grid[gridX][gridY] == elem)) {
                // First do the checks
                if (rowSets.get(gridX).contains(elem)) {
                    throw new DuplicateSudokuException("Bad move: Row " + (gridX + 1) + " has already " + elem);
                }

                if (columnSets.get(gridY).contains(elem)) {
                    throw new DuplicateSudokuException("Bad move: Column " + (gridY + 1) + " has already " + elem);
                }

                int squareIndex = translateTo1DCoordinate(gridX, gridY);
                if (squareSets.get(squareIndex).contains(elem)) {
                    throw new DuplicateSudokuException("Bad move: Square (" + (gridX+1) + "," + (gridY+1) + ") has already " + elem);
                }

                rowSets.get(gridX).add(elem);
                columnSets.get(gridY).add(elem);
                squareSets.get(squareIndex).add(elem);

                // remove the element we just replaced from the sets
                rowSets.get(gridX).remove(this.grid[gridX][gridY]);
                columnSets.get(gridY).remove(this.grid[gridX][gridY]);
                squareSets.get(squareIndex).remove(this.grid[gridX][gridY]);

                this.grid[gridX][gridY] = elem;
            }
        }
    }

    /**
     * Verify the user has filled all the cells which translates to all the rows, columns and squares
     * sets having the same maximum size of 9.
     * @return true if the game is over.
     */
    public boolean verify() {
        for (Set<Integer> row: rowSets) {
            if (row.size() != GRID_SIZE) {
                return false;
            }
        }

        for (Set<Integer> column: columnSets) {
            if (column.size() != GRID_SIZE) {
                return false;
            }
        }

        for (Set<Integer> square: squareSets) {
            if (square.size() != GRID_SIZE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Translate the given coordinates of an element (inside [0,8]) into the (x,y) coordinate of
     * the 3x3 square (inside [0,3]) and then translate the 2d coordinate into its index in 1D.
     * @param x x coordinate of the grid between 0 and 8
     * @param y y coordinate of the grid between 0 and 8
     * @return the index of the square containing the element (x,y) in the squares list
     */
    private int translateTo1DCoordinate(int x, int y) {
        // find the indices of the 3x3 square
        int squareX = x / SQUARE_SIZE;
        int squareY = y / SQUARE_SIZE;
        // Find the corresponding square in the list
        return squareX * SQUARE_SIZE + squareY;
    }
}
