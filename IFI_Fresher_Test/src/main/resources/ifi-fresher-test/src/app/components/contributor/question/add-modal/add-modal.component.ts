import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { HOSTNAME, MESSAGE_RESOURCE, TOPIC } from 'src/app/models/constant';
import { QuestionImpl } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';

@Component({
  selector: 'app-add-question-modal',
  templateUrl: './add-modal.component.html',
  styleUrls: ['./add-modal.component.css']
})
export class AddQuestionModalComponent implements OnInit {

  inputContent!: string;
  
  selectedTopic!: string;

  topicList = TOPIC;

  newQuestion: QuestionImpl = new QuestionImpl();

  errorMessage?: string;

  constructor(private questionService: QuestionService, public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  afuConfig = {
    multiple: false,
    formatsAllowed: ".jpg,.png",
    uploadAPI:  {
      url:`${HOSTNAME.backend}/question/uploadImage/` + this.newQuestion.image,
    },
    hideProgressBar: false,
    hideResetBtn: true,
    hideSelectBtn: false,
    fileNameIndex: false,
    replaceTexts: {
      selectFileBtn: 'Chọn file ảnh',
      uploadBtn: 'Tải ảnh',
      attachPinBtn: 'Tải ảnh lên...',
      afterUploadMsg_success: 'Đã tải hình ảnh!',
      afterUploadMsg_error: 'Không tải được hình ảnh !',
      sizeLimit: 'Kích thước file tối đa'
    }
  };

  // handleUpload(event: any): void {
  //   this.newQuestion.image = event.target.value;
  // }

  onAddQuestion() {
    this.newQuestion.content = this.inputContent;
    this.newQuestion.topic = this.selectedTopic;
    if(this.newQuestion.image == undefined) {
      this.newQuestion.image = 'null';
    }
    
    this.questionService.addNewQuestion(this.newQuestion)
    .then(async (reloadAfterAddQuestion) => (
      window.location.reload()
    ))
    .catch(async (err) => (
      err.error.text ===  MESSAGE_RESOURCE.THIS_QUESTION_CONTENT + " " + MESSAGE_RESOURCE.ALREADY_EXISTS ? 
        this.errorMessage = err.error.text : 
        this.errorMessage =  undefined
      )
    )
  }
}
