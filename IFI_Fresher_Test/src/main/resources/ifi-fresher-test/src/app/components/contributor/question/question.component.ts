import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PATH } from 'src/app/models/constant';
import { Question } from 'src/app/models/question-model';
import { EditQuestionModalComponent } from './edit-modal/edit-modal.component';
import { DeleteQuestionModalComponent } from './delete-modal/delete-modal.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-question-contributor',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContributorQuestionComponent implements OnInit {

  @Input() question?: Question;

  @Input() index?: number;

  imagePath = PATH.questionImage;

  constructor(public router: Router, private modalService: NgbModal) {}

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
