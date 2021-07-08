import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
@Component({
  selector: 'app-exam-contestant',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ContestantExamComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
  }

  showExamResult(){
    console.log("run here");
    $('.check-quiz').addClass('hide');
    console.log("run here1");
    $('.exam-result').removeClass('hide').addClass('show');
    console.log("run here2");
  }
}
