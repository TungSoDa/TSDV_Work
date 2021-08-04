import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

import { PATH } from 'src/app/models/constant';
import { Question, QuestionImpl } from 'src/app/models/question-model';
import { EditQuestionModalComponent } from './edit-modal/edit-modal.component';
import { DeleteQuestionModalComponent } from './delete-modal/delete-modal.component';
import { AddAnswerModalComponent } from '../answer/add-modal/add-modal.component';

@Component({
  selector: 'app-question-contributor',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContributorQuestionComponent implements OnInit {

  @Input() question?: QuestionImpl;

  @Input() index?: number;

  imagePath = PATH.questionImage;

  constructor(public router: Router, private modalService: NgbModal) {}

  openAddModal(questionID :any) {
    const modalRef = this.modalService.open(AddAnswerModalComponent);
    modalRef.componentInstance.questionID = questionID;
  }

  openEditModal(questionID :any) {
    const modalRef = this.modalService.open(EditQuestionModalComponent);
    modalRef.componentInstance.questionID = questionID;
  }

  openDeleteModal(questionID :any) {
    const modalRef = this.modalService.open(DeleteQuestionModalComponent);
    modalRef.componentInstance.questionID = questionID;
  }

  openUndeleteModal(questionID :any) {
    const modalRef = this.modalService.open(DeleteQuestionModalComponent);
    modalRef.componentInstance.questionID = questionID;
  }

  ngOnInit(): void {
  }

}
