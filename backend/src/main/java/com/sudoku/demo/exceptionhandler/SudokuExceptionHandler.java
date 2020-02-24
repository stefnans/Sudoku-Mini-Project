package com.sudoku.demo.exceptionhandler;

import com.sudoku.demo.exceptions.DuplicateSudokuException;
import com.sudoku.demo.utils.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class SudokuExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles Duplicate Sudoku Exception
     * @param ex duplicate sudoku exception
     * @return the response entity with BAD_REQUEST status code
     */
    @ExceptionHandler(DuplicateSudokuException.class)
    protected ResponseEntity<Object> handleNotFoundException(DuplicateSudokuException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
