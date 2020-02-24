package com.sudoku.demo.controllers;

import com.sudoku.demo.dto.SquareDTO;
import com.sudoku.demo.dto.SquareElementDTO;
import com.sudoku.demo.exceptions.DuplicateSudokuException;
import com.sudoku.demo.model.Square;
import com.sudoku.demo.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.sudoku.demo.model.Square.EMPTY;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SudokuController {

    @Autowired
    DtoConverter dtoConverter;

    private final int[][] goodSquareArray = {{1, EMPTY, 3},{5, 4, 8},{EMPTY, 2, EMPTY}};
    private Square square = new Square(goodSquareArray);

    @GetMapping("/")
    public SquareDTO square() {
        return dtoConverter.toSquareDto(square);
    }

    @PostMapping("/turns")
    public void setElement(@Valid @RequestBody SquareElementDTO squareElementDTO) throws DuplicateSudokuException {
        square.setElement(squareElementDTO.getElem(), squareElementDTO.getX(), squareElementDTO.getY());
    }

    @GetMapping("/reset")
    public SquareDTO resetGame() {
        this.square = new Square(goodSquareArray);
        return dtoConverter.toSquareDto(square);
    }
}
