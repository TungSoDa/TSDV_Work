import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAnswerModalComponent } from './add-modal.component';

describe('AddModalComponent', () => {
  let component: AddAnswerModalComponent;
  let fixture: ComponentFixture<AddAnswerModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAnswerModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAnswerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
