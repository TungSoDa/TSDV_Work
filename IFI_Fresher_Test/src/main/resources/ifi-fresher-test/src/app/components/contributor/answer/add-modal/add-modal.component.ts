import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AnswerImpl } from 'src/app/models/answer-model';
import { AnswerService } from 'src/app/services/answer/answer.service';

@Component({
  selector: 'app-add-answer-modal',
  templateUrl: './add-modal.component.html',
  styleUrls: ['./add-modal.component.css']
})
export class AddAnswerModalComponent implements OnInit {

  @Input() questionID!: number;

  inputContent!: string;

  answer: AnswerImpl = new AnswerImpl();

  constructor(private answerService: AnswerService,public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  async onAddAnswer() {
    this.answer.content = this.inputContent;
    this.answer.questionID = this.questionID;
    if ($('.toggle-radio input#yes:checked').val() == 'on') {
      this.answer.isCorrect = true;
    }
    if ($('.toggle-radio input#no:checked').val() == 'on') {
      this.answer.isCorrect = false;
    }
    await this.answerService.addAsnwerToQuestion(this.answer);
  }
}
