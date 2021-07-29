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

  private getByID = `${HOSTNAME.backend}/answer/`
  getAnswerByID(answerID: any): Observable<Answer> {
    return this.http.get<Answer>(this.getByID + answerID);
  }

  private add = `${HOSTNAME.backend}/answer/add`
  async addAnswerToQuestion(answer: Answer) {
    await this.http.post<Response>(this.add ,answer).toPromise().then(async (result)=>(result));
  }

  private updateByID = `${HOSTNAME.backend}/answer/update/`
  async updateAnswerOfQuestion(answer: Answer) {
    return this.http.put<Answer>(this.updateByID + answer.answerID ,answer).toPromise().then(async (result)=>(result));
  }

  private deleteByID  = `${HOSTNAME.backend}/answer/delete/`;
  deleteAnswerByID(answerID: any) {
    return this.http.delete<Answer>(this.deleteByID + answerID);
  }
}
