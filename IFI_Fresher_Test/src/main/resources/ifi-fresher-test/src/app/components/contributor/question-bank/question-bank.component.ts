import { Component, OnInit } from '@angular/core';
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

  async ngOnInit() {
    await this.questionService.getAllQuestion().toPromise().then(async (questionList) => (this.questionList = questionList));
  }

}
