import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Exam } from 'src/app/models/exam-model';
import { ExamService } from 'src/app/services/exam/exam.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})

export class ListComponent implements OnInit {
  examList?: Exam[]= [];

  topic?: string;

  constructor(public router:Router ,private examService: ExamService) { }

  async ngOnInit() {
    if(this.router.url.includes('/contestant/list') || this.router.url.includes('/contributor/list')) {
      await this.examService.getAllExam().toPromise().then(async (examList) => (this.examList = examList));
    }
    if(this.router.url.includes('/contestant/topic/')) {
      this.topic = this.router.url.substring(18).replace('_', ' ');
      await this.examService.getExamByTopic(this.router.url.substring(18)).toPromise().then(async (examList) => (this.examList = examList));
    }
  }
}


