import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Question } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';
@Component({
  selector: 'app-question-bank',
  templateUrl: './question-bank.component.html',
  styleUrls: ['./question-bank.component.css']
})
export class QuestionBankComponent implements OnInit {

  questionList?: Question[] = [];

  searchText = '';

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.questionService.getAllExam().subscribe((questionList) => (this.questionList = questionList));
  }

}
