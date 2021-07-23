import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from 'src/app/models/answer-model';
import { AnswerService } from 'src/app/services/answer/answer.service';

@Component({
  selector: 'app-answer-contestant',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class ContestantAnswerComponent implements OnInit {

  @Input() answer?: Answer;

  @Input() index?: number;

  @Input() listOption?: number[] = [];

  textColor?: string;

  isSelected?: boolean;

  constructor(public router: Router, private answerService: AnswerService) { }

  async ngOnInit() {
    if (this.answer!.isCorrect == true) {
      this.textColor = "text-success";
    }
    if (this.listOption?.includes(this.answer!.answerID)) {
      this.isSelected = true;
      if (this.answer!.isCorrect ==  false) {
        this.textColor = "text-danger";
      }
    }
  }
}
