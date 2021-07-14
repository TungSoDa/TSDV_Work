import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Exam } from 'src/app/models/exam-model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  questions: Exam[]= [];

  constructor(private http:HttpClient) { }

  private allExam = 'http://localhost:8080/exam/all'
  getAllExam(): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.allExam);
  }

  private topicExam = 'http://localhost:8080/exam/topic/';
  getExamByTopic(topic: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.topicExam + topic);
  }

  private searchExam = 'http://localhost:8080/exam/'
  getExamByID(examID: any): Observable<Exam> {
    return this.http.get<Exam>(this.searchExam + examID);
  }
}
