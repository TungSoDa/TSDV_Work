import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Exam } from 'src/app/models/exam-model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  questions: Exam[]= [];

  private allExam = 'http://localhost:8080/exam/all'

  private topicExam = 'http://localhost:8080/exam/topic/';

  constructor(private http:HttpClient) { }

  getAllExam(): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.allExam)
  }

  getTopicExam(topic: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.topicExam + topic)
  }
}
