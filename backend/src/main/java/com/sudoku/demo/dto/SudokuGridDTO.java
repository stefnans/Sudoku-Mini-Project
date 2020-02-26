package com.sudoku.demo.dto;

import lombok.Data;

@Data
public class SudokuGridDTO {
    private final int[][] initialGrid;
    private final int[][] grid;
}
