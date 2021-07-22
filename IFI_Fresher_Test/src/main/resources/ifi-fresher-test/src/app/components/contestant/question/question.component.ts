import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question-model';
import { QuestionService } from 'src/app/services/question/question.service';
import { PATH } from '../../../models/constant';

@Component({
  selector: 'app-question-contestant',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class ContestantQuestionComponent implements OnInit {

  @Input() question?: Question;

  @Input() index?: number;

  @Input() listOption?: number[] = [];

  constructor(public router: Router, private questionService: QuestionService) { }

  imagePath = PATH.questionImage;

  ngOnInit(): void {
    $("input:radio").on('click', function() {
      var $box = $(this);
      if ($box.is(":checked")) {
        var group = "input:radio[name='" + $box.attr("name") + "']";
        $(group).prop("checked", false);
        $box.prop("checked", true);
      } else {
        $box.prop("checked", false);
      }
    });
  }
}
