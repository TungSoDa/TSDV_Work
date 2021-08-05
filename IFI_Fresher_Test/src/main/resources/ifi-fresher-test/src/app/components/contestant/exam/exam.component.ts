import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { MESSAGE_RESOURCE } from 'src/app/models/constant';

import { Exam } from 'src/app/models/exam-model';
import { ExamResult, ExamResultImpl } from 'src/app/models/exam-result-model';
import { ExamService } from 'src/app/services/exam/exam.service';
@Component({
  selector: 'app-exam-contestant',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContestantExamComponent implements OnInit {

  exam?: Exam;

  examResult: ExamResultImpl = new ExamResultImpl();

  // set time 60 second
  examTimeLeft: number = 180;

  selectedAnswer: string = "";

  inputUsername?: string;

  forgotInput?: string;

  forgotChooseAnswer?: string;

  errorMessage?: string;

  constructor(public router:Router ,private examService: ExamService) { }

  async ngOnInit() {
    await this.examService.getExamByID(this.router.url.substring(17)).toPromise().then(async (exam) => (this.exam = exam));

    // auto submit exam
    setInterval(() => {
      let timeLeft = $('countdown span').text();
      if(timeLeft === '00:00:00') {
        this.showExamResult();
      }
    }, 1000)
  }

  async showExamResult() {
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

    this.examResult!.examID = this.exam!.examID;
    this.examResult!.contestantUsername = this.inputUsername;
    this.examResult!.selectedAnswers = this.selectedAnswer;

    await this.examService.submitExam(this.examResult)
    .then(async (navigateToExamResult) => (
      await this.examService.getExamResultByContestantUsernameAndExamID(this.examResult.examID, this.examResult.contestantUsername).toPromise().then(
        async (examResult) => (
          this.examResult = examResult,
          this.router.navigate(['/contestant/exam/result/'+this.examResult?.examResultID])
        )
      )
    ))
    .catch(async (err) => (
      err.error.text === MESSAGE_RESOURCE.USER_ALREADY_TESTED_THIS_EXAM ? this.errorMessage = err.error.text : this.errorMessage =  undefined,
      $('.exam-check .btn-primary.check-quiz').addClass('hide') ,
      await this.examService.getExamResultByContestantUsernameAndExamID(this.examResult.examID, this.examResult.contestantUsername).toPromise().then(
        async (examResult) => (
          this.examResult = examResult
        )
      )
    ));
  }
}

