import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

import { Answer, AnswerImpl } from 'src/app/models/answer-model';
import { EditAnswerModalComponent } from './edit-modal/edit-modal.component';
import { DeleteAnswerModalComponent } from './delete-modal/delete-modal.component';

@Component({
  selector: 'app-answer-contributor',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class AnswerComponent implements OnInit {

  @Input() answer?: AnswerImpl;

  closeResult = '';

  constructor(public router: Router, private modalService: NgbModal) {}

  ngOnInit(): void {
  }

  openEditModal(answerID :any) {
    const modalRef = this.modalService.open(EditAnswerModalComponent);
    modalRef.componentInstance.answerID = answerID;
  }

  openDeleteModal(answerID :any) {
    const modalRef = this.modalService.open(DeleteAnswerModalComponent);
    modalRef.componentInstance.answerID = answerID;
  }

}
