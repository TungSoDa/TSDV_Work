import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PATH } from 'src/app/models/constant';
import { Question } from 'src/app/models/question-model';
import { AddQuestionModalComponent } from './add-modal/add-modal.component';
import { EditQuestionModalComponent } from './edit-modal/edit-modal.component';
import { DeleteQuestionModalComponent } from './delete-modal/delete-modal.component';

@Component({
  selector: 'app-question-contributor',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContributorQuestionComponent implements OnInit {

  @Input() question?: Question;

  @Input() index?: number;

  imagePath = PATH.questionImage;

  constructor(private modalService: NgbModal) {}

  openAddModal() {
    const modalRef = this.modalService.open(AddQuestionModalComponent);
    modalRef.componentInstance.name = 'Add Modal';
  }

  openEditModal() {
    const modalRef = this.modalService.open(EditQuestionModalComponent);
    modalRef.componentInstance.name = 'Edit Modal';
  }

  openDeleteModal() {
    const modalRef = this.modalService.open(DeleteQuestionModalComponent);
    modalRef.componentInstance.name = 'Delete Modal';
  }

  ngOnInit(): void {
  }

}
