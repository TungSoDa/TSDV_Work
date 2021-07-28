import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Question } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';
import { AddQuestionModalComponent } from '../question/add-modal/add-modal.component';
@Component({
  selector: 'app-question-bank',
  templateUrl: './question-bank.component.html',
  styleUrls: ['./question-bank.component.css']
})
export class QuestionBankComponent implements OnInit {

  questionList?: Question[] = [];

  deletedList?: Question[] = [];

  searchText = '';

  constructor(public router: Router,private questionService: QuestionService, private modalService: NgbModal) { }

  async ngOnInit() {
    if (this.router.url.includes('/contributor/questionBank')){
      await this.questionService.getAllQuestion().toPromise().then(async (questionList) => (this.questionList = questionList));
    }
    if (this.router.url.includes('/contributor/deletedQuestion')){
      await this.questionService.getDeletedQuestionList().toPromise().then(async (deletedList) => (this.deletedList = deletedList));
    }
  }

  openAddModal() {
    const modalRef = this.modalService.open(AddQuestionModalComponent);
    modalRef.componentInstance.name = 'Add Modal';
  }

}
