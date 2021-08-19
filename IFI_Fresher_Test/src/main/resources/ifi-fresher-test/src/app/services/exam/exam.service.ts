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

  private getByID = `${HOSTNAME.backend}/exam/find/`
  getExamByID(examID: any): Observable<Exam> {
    return this.http.get<Exam>(this.getByID + examID);
  }

  private getResultByID= `${HOSTNAME.backend}/examResult/find/`
  getExamResultByID(examResultID: any): Observable<ExamResult> {
    return this.http.get<ExamResult>(this.getResultByID + examResultID);
  }

  private add = `${HOSTNAME.backend}/exam/add`;
  async addExam(exam: Exam) {
    await this.http.post<Response>(this.add, exam).toPromise().then(async (result) => (result));
  }

  private updateByID = `${HOSTNAME.backend}/exam/update/`;
  updateExamByID(examID: number, exam: Exam) {
    return this.http.put<Exam>(this.updateByID + examID, exam).toPromise().then(async (result) => (result));
  }

  private addResult = `${HOSTNAME.backend}/examResult/add`;
  async submitExam(examResult: ExamResult) {
    await this.http.post<Response>(this.addResult, examResult).toPromise().then(async (result) => (result));
  }

  private getResultByContestantUsernameExamID= `${HOSTNAME.backend}/examResult/result`
  getExamResultByContestantUsernameAndExamID(examID: any, contestantUsername: any): Observable<ExamResult> {
    this.examResult.examID = examID;
    this.examResult.contestantUsername = contestantUsername;
    return this.http.post<ExamResult>(this.getResultByContestantUsernameExamID, this.examResult);
  }
}
