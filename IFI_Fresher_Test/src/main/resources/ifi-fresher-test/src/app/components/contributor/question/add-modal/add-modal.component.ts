import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-question-modal',
  templateUrl: './add-modal.component.html',
  styleUrls: ['./add-modal.component.css']
})
export class AddQuestionModalComponent implements OnInit {

  @Input() name: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }
  
}
