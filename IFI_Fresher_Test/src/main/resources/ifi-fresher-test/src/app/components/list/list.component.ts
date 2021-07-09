import { Component, OnInit } from '@angular/core';
import { Exam } from 'src/app/models/exam-model';
import { ExamService } from 'src/app/services/exam/exam.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})

export class ListComponent implements OnInit {
  examList?: Exam[]= [];

  constructor(private examService: ExamService) { }

  ngOnInit(): void {
    this.examService.getAllExam().subscribe((examList) => (this.examList = examList));

  }
}


