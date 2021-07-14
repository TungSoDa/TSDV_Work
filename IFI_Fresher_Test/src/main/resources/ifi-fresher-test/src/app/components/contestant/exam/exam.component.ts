import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { Exam } from 'src/app/models/exam-model';
import { ExamService } from 'src/app/services/exam/exam.service';
@Component({
  selector: 'app-exam-contestant',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContestantExamComponent implements OnInit {

  exam?: Exam;

  constructor(public router:Router ,private examService: ExamService) { }

  ngOnInit(): void {
    this.examService.getExamByID(this.router.url.substring(17)).subscribe((exam) => (this.exam = exam));
  }

  showExamResult(){
    $('.check-quiz').addClass('hide');
    $('.exam-result').removeClass('hide').addClass('show');
  }
}
