package com.sudoku.demo.utils;

import com.sudoku.demo.dto.SquareDTO;
import com.sudoku.demo.model.Square;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DtoConverter {
    public Square toSquare(SquareDTO squareDTO) {
        return new Square(squareDTO.getSquare());
    }

    public SquareDTO toSquareDto(Square square) {
        return new SquareDTO(square.getSquare());
    }
}
