import { Component, Input, OnInit } from '@angular/core';

import { Question } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';

@Component({
  selector: 'app-question-contestant',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContestantQuestionComponent implements OnInit {

  @Input() question?: Question;

  @Input() index?: number;

  constructor(private questionService: QuestionService) { }

  imagePath = "../../../assets/images/question/";

  ngOnInit(): void {

  }
}
