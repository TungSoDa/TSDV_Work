import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HOSTNAME } from 'src/app/models/constant';
import { Question } from 'src/app/models/question-model';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http:HttpClient) { }

  private getAll = `${HOSTNAME.backend}/question/all`;
  getAllQuestion(): Observable<Question[]> {
    return this.http.get<Question[]>(this.getAll);
  }

  private getByID = `${HOSTNAME.backend}/question/`;
  getQuestionByID(questionID: any): Observable<Question> {
    return this.http.get<Question>(this.getByID + questionID);
  }

  private getByTopic = `${HOSTNAME.backend}/question/topic/`;
  getQuestionByTopic(topic: any): Observable<Question[]> {
    return this.http.get<Question[]>(this.getByTopic + topic.replace(' ', '_'));
  }

  private add = `${HOSTNAME.backend}/question/add/`;
  async addNewQuestion(question: Question) {
    return this.http.post<Question>(this.add, question).toPromise().then(async (result)=>(result));
  }

  private updateByID = `${HOSTNAME.backend}/question/update/`
  async updateQuestionByID(question: Question) {
    return this.http.put<Question>(this.updateByID + question.questionID, question).toPromise().then(async (result)=>(result));
  }

  private deleteByID  = `${HOSTNAME.backend}/question/delete/`;
  deleteQuestionByID(questionID: any) {
    return this.http.delete<Question>(this.deleteByID + questionID);
  }

  private undeleteByID  = `${HOSTNAME.backend}/question/undelete/`;
  undeleteQuestionByID(questionID: any) {
    return this.http.put<Question>(this.undeleteByID + questionID, {});
  }  

  private getDeleted  = `${HOSTNAME.backend}/question/deletedList`;
  getDeletedQuestionList(): Observable<Question[]>  {
    return this.http.get<Question[]>(this.getDeleted);
  }
}
