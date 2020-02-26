package com.sudoku.demo.utils;

import com.sudoku.demo.dto.SudokuGridDTO;
import com.sudoku.demo.model.SudokuGrid;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DtoConverter {
    public SudokuGrid toSudokuGrid(SudokuGridDTO sudokuGridDTO) {
        return new SudokuGrid(sudokuGridDTO.getGrid());
    }

    public SudokuGridDTO toSudokuGridDTO(SudokuGrid sudokuGrid) {
        return new SudokuGridDTO(sudokuGrid.getInitialGrid(), sudokuGrid.getGrid());
    }
}
