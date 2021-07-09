import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContestantQuestionComponent } from './question.component';

describe('QuestionComponent', () => {
  let component: ContestantQuestionComponent;
  let fixture: ComponentFixture<ContestantQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContestantQuestionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContestantQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
