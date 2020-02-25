package com.sudoku.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class GridElementDTO {
    private final int elem;
    private final int x;
    private final int y;
}