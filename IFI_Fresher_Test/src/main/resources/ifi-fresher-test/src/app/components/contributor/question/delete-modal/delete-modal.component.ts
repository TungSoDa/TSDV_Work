import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { QuestionImpl } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';

@Component({
  selector: 'app-delete-question-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteQuestionModalComponent implements OnInit {

  @Input() questionID!: number;

  question: QuestionImpl = new QuestionImpl();

  constructor(public router: Router, private questionService: QuestionService, public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  async onDeleteQuestionByID(questionID: any) {
    await this.questionService.deleteQuestionByID(questionID).toPromise().then(async (question) => (this.question = question));
    window.location.reload();
  }

  async onUndeleteQuestionByID(questionID: any) {
    await this.questionService.undeleteQuestionByID(questionID).toPromise().then(async (question) => (this.question = question));
    window.location.reload();
  }
}
