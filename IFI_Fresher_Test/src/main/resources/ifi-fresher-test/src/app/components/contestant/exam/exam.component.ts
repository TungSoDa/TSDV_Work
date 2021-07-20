import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';

import { Exam } from 'src/app/models/exam-model';
import { ExamResult } from 'src/app/models/exam-result-model';
import { ExamService } from 'src/app/services/exam/exam.service';
@Component({
  selector: 'app-exam-contestant',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContestantExamComponent implements OnInit {

  exam?: Exam;

  examResult?: ExamResult;

  examTime: number = 1800;

  selected_answer: string = "";

  constructor(public router:Router ,private examService: ExamService) { }

  ngOnInit(): void {
    this.examService.getExamByID(this.router.url.substring(17)).subscribe((exam) => (this.exam = exam));
  }

  showExamResult() {
    for (let i = 0; i < this.exam!.questionList.length; i++) {
      if (i === this.exam!.questionList.length-1) {
        this.selected_answer += $('input:radio[name='+(i+1)+']:checked').attr('id');
      } else {
        this.selected_answer += $('input:radio[name='+(i+1)+']:checked').attr('id') + ",";
      }
    }
    
    this.examService.submitExam(this.examResult!)
  }
}
