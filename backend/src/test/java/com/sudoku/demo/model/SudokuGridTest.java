package com.sudoku.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridTest {

    private int[][] grid = {{2, 6, 9, 0, 0, 0, 0, 0, 0},
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0, 9},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private int[][] badGridRows = {
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0, 9},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private int[][] badGridColumns = {{2, 6, 9, 0, 0, 0, 0, 0, 0},
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private int[][] gridWithBadSudokuDigits = {{2, 6, 10, 0, 0, 0, 0, 0, 0},
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 11, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 12},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private int[][] gridWithDuplicatesInRow = {{2, 6, 0, 0, 0, 0, 0, 6, 0},
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private int[][] gridWithDuplicatesInColumn = {{2, 6, 0, 0, 0, 0, 0, 0, 0},
            {2, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private int[][] gridWithDuplicatesInSquare = {{2, 6, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 8, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private SudokuGrid sudokuGrid = new SudokuGrid(grid);

    @Test
    void sudokuWithBadNumberOfRowsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuGrid(badGridRows));
    }

    @Test
    void sudokuWithBadNumberOfColumnThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuGrid(badGridColumns));
    }

    @Test
    void sudokuWithBadDigitsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuGrid(gridWithBadSudokuDigits));
    }

    @Test
    void sudokuWithDuplicatesInRowThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuGrid(gridWithDuplicatesInRow));
    }

    @Test
    void sudokuWithDuplicatesInColumnThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuGrid(gridWithDuplicatesInColumn));
    }

    @Test
    void sudokuWithDuplicatesInSquareThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuGrid(gridWithDuplicatesInSquare));
    }

    @Test
    void checkRowSetsAreValid() {

    }

    @Test
    void getGrid() {
    }

    @Test
    void getElement() {
    }

    @Test
    void setCell() {
    }

    @Test
    void verify() {
    }
}