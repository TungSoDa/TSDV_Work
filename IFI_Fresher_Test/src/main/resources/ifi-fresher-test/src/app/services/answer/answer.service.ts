import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Answer } from 'src/app/models/answer-model';
import { HOSTNAME } from 'src/app/models/constant';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  httpClient!: HttpClient;

  constructor(private http:HttpClient) { }

  private searchAnswer = `${HOSTNAME.backend}/answer/`
  getAnswerByID(answerID: any): Observable<Answer> {
    return this.http.get<Answer>(this.searchAnswer + answerID);
  }

  // private getCorrectAnswer = `${HOSTNAME.backend}/answer/correct/`
  // getCorrectAnswerOfQuestion(questionID: any): Observable<Answer> {
  //   return this.http.get<Answer>(this.getCorrectAnswer + questionID);
  // }
}
