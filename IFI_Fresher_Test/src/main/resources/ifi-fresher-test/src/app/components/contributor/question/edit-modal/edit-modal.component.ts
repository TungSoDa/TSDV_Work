import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { MESSAGE_RESOURCE, PATH, TOPIC } from 'src/app/models/constant';
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

  importImage!: File;

  topicList = TOPIC;

  errorMessage?: string;

  constructor(private questionService: QuestionService, public activeModal: NgbActiveModal) {}

  async ngOnInit() {
    await this.questionService.getQuestionByID(this.questionID).toPromise().then(async (editQuestion) => (this.editQuestion = editQuestion));
  }

  onUpdateQuestion() {
    this.questionService.updateQuestionByID(this.editQuestion)
    .then(async (reloadAfterUpdateQuestion) => (
      window.location.reload()
    ))
    .catch(async (err) => (
      err.error.text ===  MESSAGE_RESOURCE.QUESTION + " " + MESSAGE_RESOURCE.NOT_CREATED_YET + " " + MESSAGE_RESOURCE.OR_IS_DELETED ? 
        this.errorMessage = err.error.text : 
        (
          err.error.text ===  MESSAGE_RESOURCE.THIS_QUESTION_CONTENT + "  " + MESSAGE_RESOURCE.MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION ? 
            this.errorMessage = err.error.text : 
            this.errorMessage =  undefined
        )
      )
    )
  }
}
 