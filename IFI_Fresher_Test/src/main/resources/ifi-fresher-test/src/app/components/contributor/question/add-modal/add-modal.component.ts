import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TOPIC } from 'src/app/models/constant';
import { QuestionImpl } from 'src/app/models/question-model';

@Component({
  selector: 'app-add-question-modal',
  templateUrl: './add-modal.component.html',
  styleUrls: ['./add-modal.component.css']
})
export class AddQuestionModalComponent implements OnInit {

  inputContent!: string;
  
  selectedTopic!: string;

  importImage!: string;

  topicList = TOPIC;

  newQuestion: QuestionImpl = new QuestionImpl();

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  onAddQuestion() {
    this.newQuestion.content = this.inputContent;
    this.newQuestion.topic = this.selectedTopic;
    if (this.importImage != null) {
      console.log(this.importImage)
    }
  }

}
