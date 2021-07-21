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

  selectedAnswer: string = "";

  inputUsername?: string;

  forgotInput?: string;

  forgotChooseAnswer?: string;

  constructor(public router:Router ,private examService: ExamService) { }

  ngOnInit(): void {
    this.examService.getExamByID(this.router.url.substring(17)).subscribe((exam) => (this.exam = exam));
  }

  showExamResult() {
    if (this.inputUsername == null || this.inputUsername.length <= 0) {
      this.forgotInput="Vui lòng nhập tên người dùng";
      return;
    }
    this.forgotInput = undefined;

    for (let i = 0; i < this.exam!.questionList.length; i++) {
      if($('input:radio[name='+(i+1)+']:checked').val() == undefined) {
        this.forgotChooseAnswer = "Bạn chưa chọn đáp án cho đâu hỏi này";
        $('.alert-danger#question'+i).removeClass('hide');
        return;
      }
      if($('input:radio[name='+(i+1)+']:checked').val() == 'on') {
        this.forgotChooseAnswer = undefined;
        $('.alert-danger#question'+i).addClass('hide');
      }
    }

    for (let i = 0; i < this.exam!.questionList.length; i++) {
      if (i === this.exam!.questionList.length-1) {
        this.selectedAnswer += $('input:radio[name='+(i+1)+']:checked').attr('id');
      } else {
        this.selectedAnswer += $('input:radio[name='+(i+1)+']:checked').attr('id') + ",";
      }
    }

    // assign value to post
    this.examResult!.examID = this.exam!.examID;
    this.examResult!.contestantUsername = this.inputUsername;
    this.examResult!.selectedAnswers = this.selectedAnswer;

    // this.examService.submitExam(this.examResult!)
    // this.router.navigate(['/contestant/exam/result/'+this.exam?.examID])
  }
}
