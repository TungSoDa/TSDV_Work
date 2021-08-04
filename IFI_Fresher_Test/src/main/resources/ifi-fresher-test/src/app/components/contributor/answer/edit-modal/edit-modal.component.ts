import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Answer, AnswerImpl } from 'src/app/models/answer-model';
import { MESSAGE_RESOURCE } from 'src/app/models/constant';
import { AnswerService } from 'src/app/services/answer/answer.service';

@Component({
  selector: 'app-edit-answer-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.css']
})
export class EditAnswerModalComponent implements OnInit {

  @Input() answerID!: number;

  answer: AnswerImpl = new AnswerImpl();

  inputContent!: string;

  questionID!: number;

  errorMessage?: string;

  constructor(private answerService: AnswerService, public activeModal: NgbActiveModal) {}

  async ngOnInit() {
    await this.answerService.getAnswerByID(this.answerID).toPromise().then( async (answer) => (this.answer = answer));

    this.inputContent = this.answer.content;

    if (this.answer.isCorrect == true) {
      $('.toggle-radio input#yes').attr('checked', 'checked')
    } else {
      $('.toggle-radio input#no').attr('checked', 'checked')
    }

    this.questionID = this.answer.questionID;
  }

  async onUpdateAnswer() {
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

    await this.answerService.updateAnswerOfQuestion(this.answer)
      .then(async (reloadAfterUpdateAnswer) => (
        window.location.reload()
      ))
      .catch(async (err) => (
        err.error.text === MESSAGE_RESOURCE.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION ? 
          this.errorMessage = err.error.text : 
          (
            err.error.text === MESSAGE_RESOURCE.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION + " " +  MESSAGE_RESOURCE.OR_NOT_CHANGE_CONTENT_QUESTION? 
            this.errorMessage = err.error.text : 
            (
              err.error.text === MESSAGE_RESOURCE.ANSWER + " " + MESSAGE_RESOURCE.ALREADY_EXISTS + " trong câu hỏi" ? 
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
