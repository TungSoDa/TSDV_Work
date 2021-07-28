import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgSelectConfig } from '@ng-select/ng-select';

import { PATH, TOPIC } from 'src/app/models/constant';
import { Exam, ExamImpl } from 'src/app/models/exam-model';
import { Question, QuestionImpl } from 'src/app/models/question-model';
import { ExamService } from 'src/app/services/exam/exam.service';
import { QuestionService } from 'src/app/services/question/question.service';
@Component({
  selector: 'app-exam-contributor',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContributorExamComponent implements OnInit {

  exam: ExamImpl = new ExamImpl();

  topicQuestion: Question [] = [];

  selected?: number;

  selectedQuestion: QuestionImpl = new QuestionImpl();

  topicList = TOPIC;

  constructor(public router:Router, private config: NgSelectConfig, private examService: ExamService, private questionService: QuestionService) {
    this.config.notFoundText = 'Không tìm thấy câu hỏi';
    this.config.appendTo = 'body';
    this.config.bindValue = 'questionID';
  }

  imagePath = PATH.questionImage;

  async ngOnInit() {
    if (this.router.url.includes('contributor/editExam')) {
      await this.examService.getExamByID(this.router.url.substring(22)).toPromise().then(async (exam) => (this.exam = exam));
      await this.questionService.getQuestionByTopic(this.exam.topic).toPromise().then(async (topicQuestion) => (this.topicQuestion = topicQuestion));
    }
  }

  async onInputTopicFilled() {
    if (this.router.url.includes('contributor/addExam')) {
      await this.questionService.getQuestionByTopic($('select#topic option:selected').val()).toPromise().then(async (topicQuestion) => (this.topicQuestion = topicQuestion));
    }
  }

  async getSelectedQuestion() {
    if (this.router.url.includes('contributor/addExam')) {
      await this.questionService.getQuestionByID(this.selected).toPromise().then(async (selectedQuestion) => (this.selectedQuestion = selectedQuestion));
    }
  }

  addNewQuestion() {
    console.log(this.selectedQuestion);
    this.exam.questionList.push(this.selectedQuestion);
  }
}