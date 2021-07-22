import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { EXAM_QUESTION_NUMBER } from 'src/app/models/constant';
import { Exam } from 'src/app/models/exam-model';
import { ExamResult, ExamResultImpl } from 'src/app/models/exam-result-model';
import { AnswerService } from 'src/app/services/answer/answer.service';
import { ExamService } from 'src/app/services/exam/exam.service';

@Component({
  selector: 'app-exam-result-contestant',
  templateUrl: './exam-result.component.html',
  styleUrls: ['./exam-result.component.css']
})
export class ContestantExamResultComponent implements OnInit {

  exam?: Exam;

  examResult: ExamResultImpl = new ExamResultImpl();

  totalQuestion?: number;

  corrected?: number;

  listOption: number[] = [];

  testedExam: string = "Bạn đã làm bài kiểm tra này, hãy làm bài kiểm tra khác";

  constructor(public router: Router, private examService: ExamService, private answerService: AnswerService) { }

  async ngOnInit() {
    await this.examService.getExamResultByID(this.router.url.substring(24)).toPromise().then(async (examResult) => (
      this.examResult = examResult,

      await this.examService.getExamByID(this.examResult?.examID).toPromise().then((exam) => (
        this.exam = exam,
        this.exam?.topic === "Synthesis" ? this.totalQuestion = EXAM_QUESTION_NUMBER.ALL_TOPIC : this.totalQuestion = EXAM_QUESTION_NUMBER.ONE_TOPIC,
        this.examResult?.testMark == 0.00 ? this.corrected = 0 : this.corrected = Math.round(this.examResult.testMark/(10/this.totalQuestion))
      ))
    ));

    const splitStr = this.examResult.selectedAnswers.split(',');
    for (let i = 0; i < splitStr.length; i++) {
      this.listOption.push(Number(splitStr[i]));
    }
  }


}
