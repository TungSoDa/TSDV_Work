import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { EXAM_QUESTION_NUMBER } from 'src/app/models/constant';
import { Exam } from 'src/app/models/exam-model';
import { ExamResult } from 'src/app/models/exam-result-model';
import { ExamService } from 'src/app/services/exam/exam.service';

@Component({
  selector: 'app-exam-result-contestant',
  templateUrl: './exam-result.component.html',
  styleUrls: ['./exam-result.component.css']
})
export class ContestantExamResultComponent implements OnInit {

  exam?: Exam;

  examResult?: ExamResult;

  totalQuestion?: number;

  constructor(public router: Router, private examService: ExamService) { }

  ngOnInit(): void {
    this.examService.getExamByID(this.router.url.substring(24)).subscribe((exam) => (this.exam = exam));
    if (this.exam?.topic === "Synthesis") {
      this.totalQuestion = EXAM_QUESTION_NUMBER.ALL_TOPIC;
    } else {
      this.totalQuestion = EXAM_QUESTION_NUMBER.ONE_TOPIC;
    }
  }

}
