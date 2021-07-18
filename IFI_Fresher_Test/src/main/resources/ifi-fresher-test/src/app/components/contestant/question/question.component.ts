import { Component, Input, OnInit } from '@angular/core';
import * as $ from 'jquery';
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

  constructor(private questionService: QuestionService) { }

  imagePath = PATH.questionImage;

  ngOnInit(): void {
    $("input:checkbox").on('click', function() {
      var $box = $(this);
      if ($box.is(":checked")) {
        var group = "input:checkbox[name='" + $box.attr("name") + "']";
        $(group).prop("checked", false);
        $box.prop("checked", true);
      } else {
        $box.prop("checked", false);
      }
    });
  }
}
