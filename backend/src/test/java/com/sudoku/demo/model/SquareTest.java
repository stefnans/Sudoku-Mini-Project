package com.sudoku.demo.model;

import com.sudoku.demo.exceptions.DuplicateSudokuException;
import org.junit.jupiter.api.Test;

import static com.sudoku.demo.model.Square.EMPTY;
import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    private int[][] goodSquare = {{1, EMPTY, 3},{5, 4, 8},{EMPTY, 2, EMPTY}};
    private int[][] badSquare = {{1, EMPTY, 1}, {5, 4, 8},{EMPTY, 2, EMPTY}};

    @Test
    void initGridSquareWithValidLayoutShouldWork() {
        Square square = new Square(goodSquare);
        assertEquals(1, square.getElement(0, 0));
        assertEquals(5, square.getElement(1, 0));
        assertTrue(square.has(2));
        assertFalse(square.has(9));
    }

    @Test
    void initGridSquareWithInValidLayoutShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Square(badSquare));
    }

    @Test
    void setElementWorks() throws DuplicateSudokuException {
        Square square = new Square(goodSquare);
        square.setElement(9, 0, 2);
        assertEquals(9, square.getElement(0, 2));
        square.setElement(6,1,2);
        assertEquals(6, square.getElement(1, 2));
        assertTrue(square.has(6));
        assertFalse(square.has(8));
    }

    @Test
    void setDuplicatedElement() {
        Square square = new Square(goodSquare);
        assertThrows(DuplicateSudokuException.class, () -> square.setElement(1, 0, 1));
    }

    @Test
    void removeElement() {
        Square square = new Square(goodSquare);
        square.removeElement(0, 0);
        assertFalse(square.has(1));
        assertEquals(EMPTY, square.getElement(0, 0));
    }
}