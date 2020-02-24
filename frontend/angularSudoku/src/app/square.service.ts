import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Square } from './square';
import { SudokuElement } from './sudoku-element';

@Injectable({
  providedIn: 'root'
})
export class SquareService {

  private restUrl: string;

  constructor(private http: HttpClient) {
    this.restUrl = 'http://localhost:8080/'
   }

   public find(): Observable<Square> {
    return this.http.get<Square>(this.restUrl);
  }

  public postTurn(play: SudokuElement) {
    return this.http.post<SudokuElement>(this.restUrl + 'turns', play);
  }

  public reset(): Observable<Square> {
    return this.http.get<Square>(this.restUrl + 'reset');
  }
}
