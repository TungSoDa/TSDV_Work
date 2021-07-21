import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PATH } from 'src/app/models/constant';
import { Exam } from 'src/app/models/exam-model';
import { Question } from 'src/app/models/question-model';
import { ExamService } from 'src/app/services/exam/exam.service';
import { QuestionService } from 'src/app/services/question/question.service';
@Component({
  selector: 'app-exam-contributor',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContributorExamComponent implements OnInit {

  exam?: Exam;

  topicQuestion: Question [] = [];

  selected = [];

  constructor(public router:Router ,private examService: ExamService, private questionService: QuestionService) { }

  imagePath = PATH.questionImage;

  ngOnInit(): void {
    this.examService.getExamByID(this.router.url.substring(22)).subscribe((exam) => (this.exam = exam));
  }

  onInputTopicFilled() :void {
    console.log($('select#topic option:selected').val());
    this.questionService.getQuestionByTopic($('select#topic option:selected').val()).subscribe((topicQuestion) => (this.topicQuestion = topicQuestion));
    console.log(this.topicQuestion);
  }

  addNewQuestion() {

  }
}
