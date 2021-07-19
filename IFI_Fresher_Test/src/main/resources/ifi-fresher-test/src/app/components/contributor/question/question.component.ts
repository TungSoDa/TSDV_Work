import { Component, Input, OnInit } from '@angular/core';
import { PATH } from 'src/app/models/constant';
import { Question } from 'src/app/models/question-model';

@Component({
  selector: 'app-question-contributor',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContributorQuestionComponent implements OnInit {

  @Input() question?: Question;

  @Input() index?: number;

  imagePath = PATH.questionImage;

  constructor() { }

  ngOnInit(): void {
  }

}
