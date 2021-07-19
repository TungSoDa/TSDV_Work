import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Exam } from 'src/app/models/exam-model';
import { HOSTNAME } from '../../models/constant';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  questions: Exam[]= [];

  constructor(private http:HttpClient) { }

  private allExam = `${HOSTNAME.backend}/exam/all`
  getAllExam(): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.allExam);
  }

  private topicExam = `${HOSTNAME.backend}/exam/topic/`;
  getExamByTopic(topic: any): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.topicExam + topic);
  }

  private searchExam = `${HOSTNAME.backend}/exam/`
  getExamByID(examID: any): Observable<Exam> {
    return this.http.get<Exam>(this.searchExam + examID);
  }
}
