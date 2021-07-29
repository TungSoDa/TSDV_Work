import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PATH, TOPIC } from 'src/app/models/constant';
import { QuestionImpl } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';

@Component({
  selector: 'app-edit-question-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.css']
})
export class EditQuestionModalComponent implements OnInit {

  @Input() questionID!: number;

  editQuestion: QuestionImpl = new QuestionImpl();
  
  imagePath = PATH.questionImage;

  inputContent!: string;
  
  selectedTopic!: string;

  importImage!: string;

  topicList = TOPIC;

  constructor(private questionService: QuestionService,public activeModal: NgbActiveModal) {}

  async ngOnInit() {
    await this.questionService.getQuestionByID(this.questionID).toPromise().then(async (editQuestion) => (this.editQuestion = editQuestion));
    this.inputContent = this.editQuestion.content;
    this.selectedTopic = this.editQuestion.topic;
    this.importImage = this.editQuestion.image;
  }

}
