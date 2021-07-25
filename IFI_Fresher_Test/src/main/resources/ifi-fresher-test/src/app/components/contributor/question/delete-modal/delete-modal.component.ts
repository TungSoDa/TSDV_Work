import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-delete-question-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteQuestionModalComponent implements OnInit {

  @Input() name: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

}
