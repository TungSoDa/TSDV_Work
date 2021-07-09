import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContestantExamComponent } from './exam.component';

describe('ExamComponent', () => {
  let component: ContestantExamComponent;
  let fixture: ComponentFixture<ContestantExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContestantExamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContestantExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
