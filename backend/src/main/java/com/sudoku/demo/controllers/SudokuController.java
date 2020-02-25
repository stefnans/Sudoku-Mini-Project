package com.sudoku.demo.controllers;

import com.sudoku.demo.dto.GridElementDTO;
import com.sudoku.demo.dto.SudokuGridDTO;
import com.sudoku.demo.exceptions.DuplicateSudokuException;
import com.sudoku.demo.model.SudokuGrid;
import com.sudoku.demo.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SudokuController {

    @Autowired
    DtoConverter dtoConverter;

    private int[][] startedGrid = {{2, 6, 9, 0, 0, 0, 0, 0, 0},
            {0, 8, 1, 7, 0, 3, 0, 0, 4},
            {4, 7, 0, 9, 2, 0, 1, 0, 5},
            {6, 9, 4, 0, 5, 0, 2, 0, 0},
            {0, 0, 2, 3, 9, 0, 5, 4, 0},
            {0, 5, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 2, 4, 0, 9},
            {9, 0, 6, 0, 0, 0, 0, 5, 2},
            {7, 0, 0, 5, 0, 9, 3, 0, 0}};

    private SudokuGrid sudokuGrid = new SudokuGrid(startedGrid);

    @GetMapping("/")
    public SudokuGridDTO grid() {
        return dtoConverter.toSudokuGridDTO(sudokuGrid);
    }

    @PostMapping("/turns")
    public void setElement(@Valid @RequestBody GridElementDTO gridElementDTO) throws DuplicateSudokuException {
        this.sudokuGrid.setCell(gridElementDTO.getElem(), gridElementDTO.getX(), gridElementDTO.getY());
    }

    @GetMapping("/reset")
    public SudokuGridDTO resetGame() {
        this.sudokuGrid = new SudokuGrid(startedGrid);
        return dtoConverter.toSudokuGridDTO(this.sudokuGrid);
    }
}
