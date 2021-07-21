import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { Exam } from 'src/app/models/exam-model';
import { HOSTNAME } from '../../models/constant';
import { ExamResult } from 'src/app/models/exam-result-model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  questions: Exam[]= [];
  httpClient!: HttpClient;

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

  private IDExamResult= `${HOSTNAME.backend}/examResult/`
  getExamResultByID(examResultID: any): Observable<ExamResult> {
    return this.http.get<ExamResult>(this.IDExamResult + examResultID);
  }

  submitExam(examResult: ExamResult) {
    let addExamResult = `${HOSTNAME.backend}/examResult/add`;
    this.httpClient.post<Response>(addExamResult,examResult).subscribe((result)=>(
      console.log(result)
      ));
  }
}
