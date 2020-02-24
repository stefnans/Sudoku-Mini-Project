package com.sudoku.demo.model;

import com.sudoku.demo.exceptions.DuplicateSudokuException;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a single square of the nine squares of a sudoku grid.
 * Has its own method to validate the elements of the square are all distinct. For this matter
 * we use a set in addition of a 2-dimensional array to store the positions of the elements.
 */
// TODO: Save the predetermined by the game
// TODO: Transient?
public class Square {
    public final static int SIZE = 3;
    public final static int EMPTY = 0;

    private int[][] square;

    private final Set<Integer> squareElements = new HashSet<>();

    public Square(int[][] square) {
        if (square.length != SIZE) {
            throw new IllegalArgumentException("Bad Sudoku game initialization: Bad size of square");
        }

        for (int[] row: square) {
            if (row.length != SIZE) {
                throw new IllegalArgumentException("Bad Sudoku game initialization: Bad size of square row");
            }
        }

        // Shallow copy since we're dealing with primitive types
        this.square = square.clone();
        for (int i = 0; i < SIZE; i++) {
            this.square[i] = square[i].clone();
        }

        /*this.square = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(square[i], 0, this.square[i], 0, SIZE);
        }*/

        // Add the filled elements to the set
        for (int[] row : this.square) {
            for (int elem: row) {
                if (elem < EMPTY || elem > 9) {
                    throw new IllegalArgumentException("Bad Sudoku game initialization: sudoku numbers must be between 1 and 9");
                } else {
                    if (elem != EMPTY) {
                        if (!squareElements.add(elem)) {
                            throw new IllegalArgumentException("Bad Sudoku game initialization");
                        }
                    }
                }
            }
        }
    }

    public int[][] getSquare() {
        return square.clone();
    }

    public int getElement(int x, int y) {
        return this.square[x][y];
    }

    public void setElement(int elem, int x, int y) throws DuplicateSudokuException {
        // Check if not the replacing the same element,
        // otherwise the set would throw a duplicate exception when it shouldn't.
        if (!(this.square[x][y] == elem)) {
            if (!squareElements.add(elem)) {
                throw new DuplicateSudokuException("Bad move: Square has already " + elem);
            }
            // remove the element we just replaced from the set
            squareElements.remove(square[x][y]);
            this.square[x][y] = elem;
        }
    }

    public void removeElement(int x, int y) {
        if (square[x][y] != EMPTY) {
            squareElements.remove(square[x][y]);
            square[x][y] = EMPTY;
        }
    }

    public boolean has(int elem) {
        return this.squareElements.contains(elem);
    }
}
