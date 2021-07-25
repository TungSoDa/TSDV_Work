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

  private allQuestion = `${HOSTNAME.backend}/question/all`
  getAllQuestion(): Observable<Question[]> {
    return this.http.get<Question[]>(this.allQuestion);
  }

  private idQuestion = `${HOSTNAME.backend}/question/`
  getQuestionByID(questionID: any): Observable<Question> {
    return this.http.get<Question>(this.idQuestion + questionID);
  }

  private topicQuestion = `${HOSTNAME.backend}/question/topic/`
  getQuestionByTopic(topic: any): Observable<Question[]> {
    return this.http.get<Question[]>(this.topicQuestion + topic.replace(' ', '_'));
  }
}
