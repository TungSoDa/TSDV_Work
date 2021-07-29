import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AnswerImpl } from 'src/app/models/answer-model';
import { MESSAGE_RESOURCE } from 'src/app/models/constant';
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

  errorMessage?: string;

  constructor(private answerService: AnswerService,public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  async onAddAnswer() {
    if(this.inputContent == null || this.inputContent == "") {
      this.errorMessage = "Nội dung câu hỏi không được để trống";
      return;
    } else {
      this.answer.content = this.inputContent;
      this.errorMessage = undefined
    }

    this.answer.questionID = this.questionID;

    if ($('.toggle-radio input#yes:checked').val() == 'on') {
      this.answer.isCorrect = true;
    }
    if ($('.toggle-radio input#no:checked').val() == 'on') {
      this.answer.isCorrect = false;
    }

    await this.answerService.addAnswerToQuestion(this.answer)
      .then(async (reloadAfterAddAnswer) => (
        window.location.reload()
      ))
      .catch(async (err) => (
        err.error.text === MESSAGE_RESOURCE.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION ? 
          this.errorMessage = err.error.text : 
          (
            err.error.text === MESSAGE_RESOURCE.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION ? 
            this.errorMessage = err.error.text : 
            (
              err.error.text === MESSAGE_RESOURCE.ANSWER + " " + MESSAGE_RESOURCE.ALREADY_EXISTS + " IN QUESTION" ? 
              this.errorMessage = err.error.text :
              (
                err.error.text === MESSAGE_RESOURCE.QUESTION + " " + MESSAGE_RESOURCE.NOT_CREATED_YET + " " + MESSAGE_RESOURCE.OR_IS_DELETED ? 
                this.errorMessage = err.error.text : 
                this.errorMessage =  undefined
              )
            )  
          )
        )
      );
  }
}
