import { Component, Input, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer-model';
import { Question } from 'src/app/models/question-model';

@Component({
  selector: 'app-question-contributor',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContributorQuestionComponent implements OnInit {

  @Input() question?: Question;

  @Input() index?: number;

  constructor() { }

  ngOnInit(): void {
  }

}
