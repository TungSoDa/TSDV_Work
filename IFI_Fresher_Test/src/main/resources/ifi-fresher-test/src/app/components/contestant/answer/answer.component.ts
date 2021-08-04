import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Answer, AnswerImpl } from 'src/app/models/answer-model';

@Component({
  selector: 'app-answer-contestant',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class ContestantAnswerComponent implements OnInit {

  @Input() answer?: AnswerImpl;

  @Input() index?: number;

  @Input() listOption?: number[] = [];

  textColor?: string;

  isSelected?: boolean;

  constructor(public router: Router) { }

   ngOnInit(): void {
    
  }
}
