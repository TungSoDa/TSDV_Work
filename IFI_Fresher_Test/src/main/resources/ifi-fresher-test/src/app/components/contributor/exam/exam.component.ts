import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgSelectConfig } from '@ng-select/ng-select';

import { EXAM_QUESTION_NUMBER, MESSAGE_RESOURCE, PATH, TOPIC } from 'src/app/models/constant';
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

  selectedTopic?: string;

  successMessage?: string;

  errorMessage?: string;

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
    await this.questionService.getQuestionByID(this.selected).toPromise().then(async (selectedQuestion) => (this.selectedQuestion = selectedQuestion));
  }

  async onAddExam(action: string) {
    this.exam.topic = $('select#topic option:selected').text();

    if (this.exam.topic === undefined || this.exam.topic === "Chọn chủ đề") {
      this.errorMessage = "Bạn chưa chọn chủ đề"
    }

    if (action === 'add') {
      this.exam.listQuestionID = this.questionToStringID(this.exam.questionList);
    }

    await this.examService.addExam(this.exam)
    .then(async (reloadAfterUpdateExam) => (
      setTimeout(function() { 
        window.location.reload(); 
      }, 3000),
      this.successMessage = "Đã thêm đề thi " + this.exam.topic +" mới thành công, xem ngay ở Danh sách đề thi",
      this.errorMessage =  undefined
    ))
    .catch(async (err) => (
      err.error === MESSAGE_RESOURCE.NO_QUESTIONS_WITH_THIS_TOPIC ?
        (this.errorMessage = err.error + this.exam.topic, this.successMessage = undefined) :
        (
          err.error === MESSAGE_RESOURCE.EXAM + " " + MESSAGE_RESOURCE.NOT_CREATED_YET + " " + MESSAGE_RESOURCE.OR_IS_DELETED ? 
            (this.errorMessage = err.error, this.successMessage = undefined) :
            (
              err.error === MESSAGE_RESOURCE.EXAM + " " + MESSAGE_RESOURCE.WITH_THE_SAME_NAME + " " + MESSAGE_RESOURCE.ALREADY_EXISTS ? 
                (this.errorMessage = err.error, this.successMessage = undefined) :
                (
                  err.error === MESSAGE_RESOURCE.EXAM + " " + MESSAGE_RESOURCE.WITH_QUESTION_LIST_IS_CREATED ? 
                    (this.errorMessage = err.error, this.successMessage = undefined) :
                    (
                      err.error === MESSAGE_RESOURCE.ALL_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + EXAM_QUESTION_NUMBER.ALL_TOPIC 
                                || MESSAGE_RESOURCE.ONE_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + EXAM_QUESTION_NUMBER.ONE_TOPIC ? 
                        (this.errorMessage = err.error, this.successMessage = undefined) :
                        (
                          err.error === MESSAGE_RESOURCE.INCORRECT_QUESTION_LIST_WITH_TOPIC ?
                          (this.errorMessage = err.error, this.successMessage = undefined) : 
                          this.errorMessage =  undefined
                        )
                    )
                )  
            )  
        )        
      )
    );
  }

  questionToStringID(questionList: Question[]): string {
    let listQuestionsID = "";
    for (let i = 0; i < questionList.length; i++) {
      if (i == questionList.length - 1) {
        listQuestionsID += questionList[i].questionID;
      } else {
        listQuestionsID += questionList[i].questionID + ",";
      }
    }
    return listQuestionsID;
  }

  async onUpdateExam() {
    this.exam.listQuestionID = this.questionToStringID(this.exam.questionList);
    await this.examService.updateExamByID(this.exam.examID, this.exam)
    .then(async (reloadAfterUpdateExam) => (
      window.location.reload()
    ))
    .catch(async (err) => (
      err.error === MESSAGE_RESOURCE.EXAM + " " + MESSAGE_RESOURCE.NOT_CREATED_YET + " " + MESSAGE_RESOURCE.OR_IS_DELETED ? 
        this.errorMessage = err.error : 
        (
          err.error === MESSAGE_RESOURCE.EXAM + " " + MESSAGE_RESOURCE.WITH_THE_SAME_NAME + " " + MESSAGE_RESOURCE.ALREADY_EXISTS ? 
            this.errorMessage = err.error : 
            (
              err.error === MESSAGE_RESOURCE.EXAM + " " + MESSAGE_RESOURCE.WITH_QUESTION_LIST_IS_CREATED ? 
                this.errorMessage = err.error :
                (
                  err.error === MESSAGE_RESOURCE.ALL_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + EXAM_QUESTION_NUMBER.ALL_TOPIC 
                                  || MESSAGE_RESOURCE.ONE_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + EXAM_QUESTION_NUMBER.ONE_TOPIC ? 
                    this.errorMessage = err.error : 
                    (
                      err.error === MESSAGE_RESOURCE.INCORRECT_QUESTION_LIST_WITH_TOPIC ?
                      this.errorMessage = err.error : 
                      this.errorMessage =  undefined
                    )
                )
            )  
        )         
      )
    );
  }

  async onAddNewQuestionToQuestionList() {
    let check = false;
    if (this.exam.questionList != undefined) {
      for (let i = 0; i < this.exam.questionList.length; i++) {
        if(this.exam.questionList[i].questionID == this.selectedQuestion.questionID) {
          check = true;
          break;
        }
      }  
    } else {
      this.exam.questionList = [];
    }

    if (check) {
      this.errorMessage = MESSAGE_RESOURCE.QUESTION_EXISTED_IN_EXAM;
    } else {
      this.errorMessage = undefined;
      this.exam.questionList.push(this.selectedQuestion);
    }
  }

  onDeleteQuestionFromQuestionList(question: Question) {
    const index = this.exam.questionList.indexOf(question);
    this.exam.questionList.splice(index, 1);
  }
}