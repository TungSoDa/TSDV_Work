import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { Exam } from 'src/app/models/exam-model';
import { HOSTNAME } from '../../models/constant';
import { ExamResult, ExamResultImpl } from 'src/app/models/exam-result-model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  httpClient!: HttpClient;

  examResult: ExamResultImpl = new ExamResultImpl();

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

  async submitExam(examResult: ExamResult) {
    let addExamResult = `${HOSTNAME.backend}/examResult/add`;
    await this.http.post<Response>(addExamResult,examResult).toPromise().then(async (result)=>(result));
  }

  private ContestantUsernameExamIDExamResult= `${HOSTNAME.backend}/examResult/result`
  getExamResultByContestantUsernameAndExamID(examID: any, contestantUsername: any): Observable<ExamResult> {
    this.examResult.examID = examID;
    this.examResult.contestantUsername = contestantUsername;
    return this.http.post<ExamResult>(this.ContestantUsernameExamIDExamResult, this.examResult);
  }
}
