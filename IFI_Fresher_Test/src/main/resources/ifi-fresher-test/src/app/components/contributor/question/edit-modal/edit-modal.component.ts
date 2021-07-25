import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-edit-question-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.css']
})
export class EditQuestionModalComponent implements OnInit {

  @Input() name: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

}
