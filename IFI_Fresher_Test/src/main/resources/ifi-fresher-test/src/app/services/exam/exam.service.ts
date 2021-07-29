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

  private getAll = `${HOSTNAME.backend}/exam/all`
  getAllExam(): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.getAll);
  }

  private getByTopic = `${HOSTNAME.backend}/exam/topic/`;
  getExamByTopic(topic: any): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.getByTopic + topic);
  }

  private getByID = `${HOSTNAME.backend}/exam/`
  getExamByID(examID: any): Observable<Exam> {
    return this.http.get<Exam>(this.getByID + examID);
  }

  private getResultByID= `${HOSTNAME.backend}/examResult/`
  getExamResultByID(examResultID: any): Observable<ExamResult> {
    return this.http.get<ExamResult>(this.getResultByID + examResultID);
  }

  private addExamResult = `${HOSTNAME.backend}/examResult/add`;
  async submitExam(examResult: ExamResult) {
    await this.http.post<Response>(this.addExamResult,examResult).toPromise().then(async (result) => (result));
  }

  private getResultContestantUsernameExamID= `${HOSTNAME.backend}/examResult/result`
  getExamResultByContestantUsernameAndExamID(examID: any, contestantUsername: any): Observable<ExamResult> {
    this.examResult.examID = examID;
    this.examResult.contestantUsername = contestantUsername;
    return this.http.post<ExamResult>(this.getResultContestantUsernameExamID, this.examResult);
  }
}
