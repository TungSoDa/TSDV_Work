import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { AnswerImpl } from 'src/app/models/answer-model';
import { AnswerService } from 'src/app/services/answer/answer.service';

@Component({
  selector: 'app-delete-answer-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteAnswerModalComponent implements OnInit {

  @Input() answerID!: number;

  answer: AnswerImpl = new AnswerImpl();

  constructor(private answerService: AnswerService, public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  async onDeleteAnswerByID(answerID: any) {
    await this.answerService.deleteAnswerByID(answerID).toPromise().then(async (answer) => (this.answer = answer));
    window.location.reload();
  }
}
