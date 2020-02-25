import { Component, OnInit, Input } from '@angular/core';
import { Square } from '../square';
import { SquareService } from '../square.service';
import { example } from '../example'
import { SudokuElement } from '../sudoku-element';

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.css']
})
export class SquareComponent implements OnInit {

  @Input() square: Square;
  input = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
  selectedCellX = -1;
  selectedCellY = -1;

  playerFeedback: String;

  constructor(private squareService: SquareService) { }

  ngOnInit(): void {
    this.squareService.find().subscribe(data => {
      console.log(data);
      this.square = data;
    })
  }

  cellClick(i, j) {
    this.selectedCellX = i;
    this.selectedCellY = j;
  }

  update(i) {
    // If there is a selected cell
    if (!(this.selectedCellX == -1 || this.selectedCellY == -1)) {
      this.squareService.postTurn(new SudokuElement(i, this.selectedCellX, this.selectedCellY))
        .subscribe(result => {
          this.playerFeedback = "Ok"
          this.square.square[this.selectedCellX][this.selectedCellY] = i;
        },
          err => this.playerFeedback = err.error.SudokuApiError.message
        )
    }
  }

  resetGame() {
    this.squareService.reset().subscribe(data => {
      this.square = data;
    })
  }
}
