import { Component, Input, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer-model';

@Component({
  selector: 'app-answer-contestant',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class ContestantAnswerComponent implements OnInit {

  @Input() answer?: Answer;

  @Input() index?: number;

  @Input() listOption?: number[] = [];

  constructor() { }

  ngOnInit(): void {
    if (this.listOption?.includes(this.answer!.answerID)) {
      console.log(this.answer);
    }
  }
}
