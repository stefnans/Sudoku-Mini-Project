package com.sudoku.demo.model;

import com.sudoku.demo.exceptions.DuplicateSudokuException;

import static com.sudoku.demo.model.Square.SIZE;

/**
 * Tradeoff between time and space. If we store the sets we use to validate the player input it will validate
 * the operation immediately but it will consume a lot of memory.
 * If we create them on the fly, we consume less memory space but the operations of creation take more time, and in a
 * rest api, we value time more than space, so we go with the first option.
 */
public class SudokuGrid {
    private final static int GRID_SIZE = 9;
    private Square[][] grid = new Square[SIZE][SIZE];

    public SudokuGrid(int[][] grid) {

    }


    /**
     * Set the empty cell of the grid with a new element.
     * @param elem the new element to set in a grid cell
     * @param gridX index of the grid between 0 and 8
     * @param gridY index of the grid between 0 and 8
     */
    public void setCell(int elem, int gridX, int gridY) throws DuplicateSudokuException {

    }

    private int[] translateToSquareCoordinates(int x, int y) {
        return null;
    }
}
