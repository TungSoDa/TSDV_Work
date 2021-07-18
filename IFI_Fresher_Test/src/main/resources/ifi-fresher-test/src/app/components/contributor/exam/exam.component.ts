import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PATH } from 'src/app/models/constant';
import { Exam } from 'src/app/models/exam-model';
import { ExamService } from 'src/app/services/exam/exam.service';
@Component({
  selector: 'app-exam-contributor',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContributorExamComponent implements OnInit {

  exam?: Exam;

  constructor(public router:Router ,private examService: ExamService) { }

  imagePath = PATH.questionImage;

  ngOnInit(): void {
    this.examService.getExamByID(this.router.url.substring(22)).subscribe((exam) => (this.exam = exam));
  }

  addNewQuestion() {
    
  }
}
