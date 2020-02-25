import { SudokuElement } from './sudoku-element';

describe('SudokuElement', () => {
  it('should create an instance', () => {
    expect(new SudokuElement(1, 0, 0)).toBeTruthy();
  });
});
