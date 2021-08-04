import { Component, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Exam, ExamImpl } from 'src/app/models/exam-model';

@Component({
  selector: 'app-list-item',
  templateUrl: './list-item.component.html',
  styleUrls: ['./list-item.component.css']
})
export class ListItemComponent implements OnInit {
  @Input() exam?: ExamImpl;
  
  constructor(public router: Router) {}

  ngOnInit(): void {
  }

  imageTopicSource = "../../assets/images/topic/topic";
  imageTopicExtension = ".png";

  removeSpace(str: any) {
    return str.replace(' ', '');
  }
}

